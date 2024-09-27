package school.ibaktor.smartshopsstudent.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import school.ibaktor.smartshopsstudent.data.dao.UsuarioDao
import school.ibaktor.smartshopsstudent.data.dao.UsuarioPedidoDao
import school.ibaktor.smartshopsstudent.data.model.Usuario
import school.ibaktor.smartshopsstudent.data.model.UsuarioPedido

@Database(entities = [Usuario::class, UsuarioPedido::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usuarioDato(): UsuarioDao
    abstract fun usuarioPedidoDao() : UsuarioPedidoDao
}