data class WebResponse(
    val statusCode: Int,
    val statusMessage: String,
    val body: String?
)

fun describeStatus(code: Int): String {
    return when (code) {
        in 200..299 -> "Success: The request was fulfilled."
        in 400..499 -> "Client Error: Check your URL or parameters."
        in 500..599 -> "Server Error: The Lehman Server is having trouble."
        else -> "Unknown status code."
    }
}

fun routeRequest(path: String, user: String?): String {
    return when (path) {
        "/home" -> "Welcome to the Lehman Homepage, ${user ?: "Guest"}!"
        "/grades" -> {
            if (user == null) {
                "Error: Unauthorized access to grades."
            } else {
                "Loading grades for $user..."
            }
        }
        else -> "404: Path $path not found."
    }
}

fun main() {
    val success = WebResponse(200, "OK", "Data loaded successfully")
    val notFound = WebResponse(404, "Not Found", null)

    println(success)
    println(notFound)

    println(describeStatus(201))
    println(describeStatus(404))
    println(describeStatus(503))

    println(routeRequest("/home", "Hillary"))
    println(routeRequest("/home", null))
    println(routeRequest("/grades", "Hillary"))
    println(routeRequest("/grades", null))
    println(routeRequest("/random", "Hillary"))
}