package school.ibaktor.smartshopsstudent.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import school.ibaktor.smartshopsstudent.data.model.Producto

@Dao
interface ProductoDao {

    @Query("SELECT * FROM producto")
    fun getAllProductos(): List<Producto>

    @Query("SELECT * FROM producto WHERE id = :productoId")
    fun getProductoById(productoId: Int): Producto

    @Insert
    fun insertProducto(producto: Producto)

    @Update
    fun updateProducto(producto: Producto)

    @Delete
    fun deleteProducto(producto: Producto)
}