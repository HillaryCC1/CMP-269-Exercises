package com.example

import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.http.*
import io.ktor.server.http.content.*
import kotlinx.serialization.Serializable

@Serializable
data class Stock(
    val symbol: String,
    val price: Double
)

fun Application.configureRouting() {
    routing {

        get("/") {
            call.respondText("Server is online at Lehman College.")
        }

        get("/greet") {
            call.respondText("Hello, Guest! Welcome to CMP 269.")
        }

        get("/greet/{name}") {
            val name = call.parameters["name"] ?: "Guest"
            call.respondText("Hello, $name! Welcome to CMP 269.")
        }

        val grades = mapOf("123" to 95, "456" to 82)

        get("/grade/{studentId}") {
            val id = call.parameters["studentId"] ?: ""
            val grade = grades[id]

            if (grade != null) {
                call.respondText("Grade: $grade")
            } else {
                call.respondText(
                    "Student not found",
                    status = HttpStatusCode.NotFound
                )
            }
        }

        staticResources("/static", "static")

        get("/api/stock/{symbol}") {
            val symbol = call.parameters["symbol"] ?: "UNKNOWN"
            val stockObject = Stock(symbol, 150.25)
            call.respond(stockObject)
        }
    }
}