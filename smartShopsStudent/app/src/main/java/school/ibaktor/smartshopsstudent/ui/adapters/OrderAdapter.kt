package school.ibaktor.smartshopsstudent.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import school.ibaktor.smartshopsstudent.R
import school.ibaktor.smartshopsstudent.data.model.UsuarioPedido
import school.ibaktor.smartshopsstudent.models.Order

class OrderAdapter(private val orderList: List<UsuarioPedido>) : RecyclerView.Adapter<OrderAdapter.OrderViewHoder>() {

    class OrderViewHoder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvOrderNumber: TextView = itemView.findViewById(R.id.tvOrderNumber)
        val tvOrderDate: TextView = itemView.findViewById(R.id.tvOrderDate)
        val tvOrderTotal: TextView = itemView.findViewById(R.id.tvOrderTotal)
        val tvOrderPayState: TextView = itemView.findViewById(R.id.tvOrderPayState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHoder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false)
        return OrderViewHoder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHoder, position: Int) {
        val order = orderList[position]
        holder.tvOrderNumber.text = order.numero
        holder.tvOrderDate.text = order.fecha
        holder.tvOrderTotal.text = order.total.toString()
        holder.tvOrderPayState.text = order.estadoPago
    }

    override fun getItemCount() = orderList.size
}