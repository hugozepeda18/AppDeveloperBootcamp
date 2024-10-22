package school.ibaktor.smartshopsstudent.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categoria")
data class Category (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String
)