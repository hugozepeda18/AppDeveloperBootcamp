package school.ibaktor.smartshopsstudent.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import school.ibaktor.smartshopsstudent.data.model.Usuario

@Dao
interface UsuarioDao {
    @Query("SELECT * FROM usuario")
    fun getAllUsuarios(): List<Usuario>

    @Query("SELECT * FROM usuario WHERE id = :usuarioId")
    fun getUsuarioById(usuarioId: Int): Usuario

    @Insert
    fun insertUsuario(usuario: Usuario)

    @Update
    fun updateUsuario(usuario: Usuario)

    @Delete
    fun deleteUsuario(usuario: Usuario)
}