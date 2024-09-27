package school.ibaktor.smartshopsstudent.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuario")
data class Usuario(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val usuarioNombre: String,
    val usuarioDescripcion: String
)