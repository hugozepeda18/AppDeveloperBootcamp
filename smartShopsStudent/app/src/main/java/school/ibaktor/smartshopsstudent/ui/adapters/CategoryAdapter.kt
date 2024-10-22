package school.ibaktor.smartshopsstudent.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import school.ibaktor.smartshopsstudent.R
import school.ibaktor.smartshopsstudent.ui.fragment.OnCategoryClickListener

class CategoryAdapter(
    private val categoryList: MutableList<String>,
    private val listener: OnCategoryClickListener
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvCategoryName: TextView = itemView.findViewById(R.id.tvCategoryTitle)

        init{
            itemView.setOnClickListener{
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(categoryList[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categoryList[position]
        holder.tvCategoryName.text = category
    }

    override fun getItemCount() = categoryList.size

    fun addCategories(newCategories : MutableList<String>){
        val startPosition = categoryList.size
        categoryList.addAll(newCategories)
        notifyItemRangeInserted(startPosition, newCategories.size)
    }

}