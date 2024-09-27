package school.ibaktor.smartshopsstudent.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import school.ibaktor.smartshopsstudent.data.model.UsuarioPedido

@Dao
interface UsuarioPedidoDao {

    @Transaction
    @Query("SELECT * from usuario_pedido WHERE usuarioId = :usuarioId")
    fun getPedidosByUsuario( usuarioId: Int ): List<UsuarioPedido>

    @Insert
    fun insertUsuarioPedido(usuarioPedido: UsuarioPedido)
}