package school.ibaktor.smartshopsstudent.data.dao

import androidx.core.view.WindowInsetsCompat.Type.InsetsType
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import school.ibaktor.smartshopsstudent.data.model.Category

@Dao
interface CategoryDao {
    @Query("SELECT * FROM categoria")
    fun getAllCategories(): List<Category>

    @Query("SELECT * FROM categoria WHERE id = :categoryId")
    fun getCategoryById(categoryId: Int): Category

    @Insert
    fun insertCategory(category: Category)

    @Update
    fun updateCategory(category: Category)

    @Delete
    fun deleteCategory(category: Category)
}