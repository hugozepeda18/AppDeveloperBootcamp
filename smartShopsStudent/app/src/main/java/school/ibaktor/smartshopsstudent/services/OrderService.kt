package school.ibaktor.smartshopsstudent.services

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import school.ibaktor.smartshopsstudent.data.dao.UsuarioPedidoDao
import school.ibaktor.smartshopsstudent.data.model.UsuarioPedido
import school.ibaktor.smartshopsstudent.models.Order

class OrderService(private val usuarioPedidoDao: UsuarioPedidoDao) {

    suspend fun getOrdersByUser(usuarioId: Int): List<UsuarioPedido>{
        return withContext(Dispatchers.IO){
            usuarioPedidoDao.getPedidosByUsuario(usuarioId)
        }
        /*return listOf(
            Order("123123876", "13/07/2024", "Pagado", 200.0),
            Order("123123977", "15/07/2024", "Pagado", 20.0),
            Order("123124798", "16/07/2024", "Pendiente", 100.0),
            Order("123123876", "13/07/2024", "Pagado", 200.0),
            Order("123123977", "15/07/2024", "Pagado", 20.0),
            Order("123124798", "16/07/2024", "Pendiente", 100.0),
            Order("123124798", "16/07/2024", "Pendiente", 100.0),
        )*/
    }

    fun getOrder(numero: String){

    }

    suspend fun create(usuarioPedido: UsuarioPedido){
        withContext( Dispatchers.IO ){
            usuarioPedidoDao.insertUsuarioPedido( usuarioPedido )
        }
    }

    fun update(order: Order){

    }

    fun upsert(order: Order){

    }
}