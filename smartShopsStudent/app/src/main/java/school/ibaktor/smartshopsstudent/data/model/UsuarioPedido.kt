package school.ibaktor.smartshopsstudent.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Usuario::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("usuarioId"),
            onDelete = ForeignKey.CASCADE
        )],
    tableName = "usuario_pedido"
)
data class UsuarioPedido(
    @PrimaryKey val pedidoId: Int,
    val numero: String,
    val fecha: String,
    val estadoPago: String,
    val total: Double,
    val usuarioId: Int
)