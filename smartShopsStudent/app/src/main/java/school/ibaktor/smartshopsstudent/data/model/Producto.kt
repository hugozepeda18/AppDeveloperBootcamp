package school.ibaktor.smartshopsstudent.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "producto")
data class Producto (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val image: String
)