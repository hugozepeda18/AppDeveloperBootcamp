package school.ibaktor.smartshopsstudent.models

data class Order(
    val numero: String,
    val fecha: String,
    val estadoPago: String,
    val total: Double
)