package school.ibaktor.smartshopsstudent.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.launch
import school.ibaktor.smartshopsstudent.R
import school.ibaktor.smartshopsstudent.data.dao.UsuarioDao
import school.ibaktor.smartshopsstudent.data.dao.UsuarioPedidoDao
import school.ibaktor.smartshopsstudent.data.database.AppDatabase
import school.ibaktor.smartshopsstudent.data.database.DatabaseClient
import school.ibaktor.smartshopsstudent.data.database.MIGRATION_1_TO_2
import school.ibaktor.smartshopsstudent.data.model.Usuario
import school.ibaktor.smartshopsstudent.data.model.UsuarioPedido
import school.ibaktor.smartshopsstudent.services.OrderService
import school.ibaktor.smartshopsstudent.services.UserService
import school.ibaktor.smartshopsstudent.ui.adapters.OrderAdapter

class AccountFragment : Fragment() {

    private lateinit var rvOrdersList : RecyclerView
    private lateinit var tvAccountName : TextView
    private lateinit var tvAccountDescription : TextView
    private lateinit var orderService: OrderService
    private lateinit var userService: UserService
    private lateinit var dataBase: AppDatabase
    private lateinit var usuarioDao: UsuarioDao
    private lateinit var usuarioPedidoDao: UsuarioPedidoDao

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewFragment = inflater.inflate(R.layout.fragment_account, container, false)

        tvAccountName = viewFragment.findViewById(R.id.tvAccountName)
        tvAccountDescription = viewFragment.findViewById(R.id.tvAccountDescription)

        rvOrdersList = viewFragment.findViewById(R.id.rvOrdersList)
        rvOrdersList.layoutManager = LinearLayoutManager(context)

        return viewFragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*dataBase = Room.databaseBuilder(
            requireContext().applicationContext,
            AppDatabase::class.java, "smartshop"
        ).addMigrations(MIGRATION_1_TO_2)
            .build()*/

        dataBase = DatabaseClient.getDatabase(requireContext().applicationContext)

        usuarioDao = dataBase.usuarioDato()
        userService = UserService(usuarioDao)

        /*val newUsuario = Usuario(
            id = 0,
            usuarioNombre = "Johan Fuentes",
            usuarioDescripcion = "Una descripción de prueba",
        )

        lifecycleScope.launch {
            userService.create( newUsuario )
        }*/

        lifecycleScope.launch {
            val usuario = userService.getUsuario(1)
            if (usuario != null) {
                tvAccountName.text = usuario.usuarioNombre
                tvAccountDescription.text = usuario.usuarioDescripcion
            }
        }

        /*val newUsuarioPedido = UsuarioPedido(
            pedidoId = 1,
            numero = "12345678",
            fecha = "13/07/2024",
            estadoPago = "Pagado",
            total = 200.0,
            usuarioId = 1
        )

        val newUsuarioPedido2 = UsuarioPedido(
            pedidoId = 2,
            numero = "12345612",
            fecha = "26/07/2024",
            estadoPago = "Pagado",
            total = 100.0,
            usuarioId = 1
        )*/

        usuarioPedidoDao = dataBase.usuarioPedidoDao()

        lifecycleScope.launch {
            orderService = OrderService( usuarioPedidoDao )
            //orderService.create( newUsuarioPedido )
            //orderService.create( newUsuarioPedido2 )
            rvOrdersList.adapter = OrderAdapter(orderService.getOrdersByUser(1))
        }

    }
}