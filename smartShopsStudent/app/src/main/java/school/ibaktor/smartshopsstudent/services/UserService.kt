package school.ibaktor.smartshopsstudent.services

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import school.ibaktor.smartshopsstudent.data.dao.UsuarioDao
import school.ibaktor.smartshopsstudent.data.model.Usuario

class UserService(private val usuarioDao: UsuarioDao){

    suspend fun getUsuario(id: Int) : Usuario{
        return withContext(Dispatchers.IO){
            usuarioDao.getUsuarioById(id)
        }
    }

    suspend fun create(usuario: Usuario){
        withContext(Dispatchers.IO){
            usuarioDao.insertUsuario(usuario)
        }
    }

}