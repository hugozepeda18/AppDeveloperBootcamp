package school.ibaktor.smartshopsstudent.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import school.ibaktor.smartshopsstudent.R
import school.ibaktor.smartshopsstudent.models.Product
import school.ibaktor.smartshopsstudent.ui.fragment.OnItemClickListener

class ProductAdapter(
        private val productList: MutableList<Product>,
        private val listener: OnItemClickListener
    ) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvProductTitle: TextView = itemView.findViewById(R.id.tvProductTitle)
        val tvProductPrice: TextView = itemView.findViewById(R.id.tvProductPrice)
        val ivProductImage: ImageView = itemView.findViewById(R.id.ivProductImage)

        init{
            itemView.setOnClickListener{
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(productList[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product_grid, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductAdapter.ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.tvProductTitle.text = product.title
        holder.tvProductPrice.text = "$${product.price.toString()}"
        Glide.with(holder.itemView.context)
            .load(product.image)
            .into(holder.ivProductImage)
    }

    override fun getItemCount() = productList.size

    fun addProducts(newProducts : MutableList<Product>){
        val startPosition = productList.size
        productList.addAll(newProducts)
        notifyItemRangeInserted(startPosition, newProducts.size)
    }
}