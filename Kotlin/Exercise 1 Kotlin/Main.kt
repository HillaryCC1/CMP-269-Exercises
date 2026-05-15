// Exercise 1: Nullable Student Profile
fun exercise1() {
    val studentName: String = "Hillary"
    val middleName: String? = null

    println("Welcome, $studentName ${middleName ?: "No Middle Name"}!")
}

// Exercise 2: Data Classes & Extension Functions
data class Laptop(val brand: String, val ramGB: Int)

// Extension function for Int
fun Int.toLehmanGigabytes(): String {
    return "$this GB (Lehman Standard)"
}

fun exercise2() {
    val laptop1 = Laptop("Apple", 16)
    val laptop2 = Laptop("Dell", 8)

    println("${laptop1.brand} RAM: ${laptop1.ramGB.toLehmanGigabytes()}")
    println("${laptop2.brand} RAM: ${laptop2.ramGB.toLehmanGigabytes()}")
}

// Exercise 3: Sealed Classes & When Expressions
sealed class EnrollmentStatus {
    data class Success(val courseCode: String) : EnrollmentStatus()
    data class Error(val message: String) : EnrollmentStatus()
    object Loading : EnrollmentStatus()
}

fun printStatus(status: EnrollmentStatus) {
    when (status) {
        is EnrollmentStatus.Success -> println("Enrolled successfully in ${status.courseCode}")
        is EnrollmentStatus.Error -> println("Enrollment failed: ${status.message}")
        EnrollmentStatus.Loading -> println("Enrollment is loading...")
    }
}

fun exercise3() {
    val successStatus = EnrollmentStatus.Success("CMP 168")
    val errorStatus = EnrollmentStatus.Error("Course is full")
    val loadingStatus = EnrollmentStatus.Loading

    printStatus(successStatus)
    printStatus(errorStatus)
    printStatus(loadingStatus)
}

fun main() {
    exercise1()
    println()

    exercise2()
    println()

    exercise3()
}