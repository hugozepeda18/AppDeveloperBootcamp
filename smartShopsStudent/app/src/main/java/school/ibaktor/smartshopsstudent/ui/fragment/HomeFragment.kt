package school.ibaktor.smartshopsstudent.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import school.ibaktor.smartshopsstudent.R
import school.ibaktor.smartshopsstudent.services.apiFakeStore.RetrofitInstance
import school.ibaktor.smartshopsstudent.ui.adapters.CategoryAdapter

class HomeFragment : Fragment(), OnCategoryClickListener {

    private lateinit var rvCategoriesGrid: RecyclerView
    private lateinit var categoryAdapter: CategoryAdapter
    private var isLoadingPage = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewFragment = inflater.inflate(R.layout.fragment_home, container, false)

        rvCategoriesGrid = viewFragment.findViewById(R.id.rvCategoriesGrid)
        rvCategoriesGrid.layoutManager = LinearLayoutManager(context)

        setupAdapter()
        fetchCategories()

        return viewFragment
    }

    fun setupAdapter(){
        categoryAdapter = CategoryAdapter(mutableListOf(), this@HomeFragment)
        rvCategoriesGrid.adapter = categoryAdapter
    }

    fun fetchCategories(){
        isLoadingPage = true
        val apiService = RetrofitInstance.apiService
        apiService.getCategories().enqueue(object: Callback<MutableList<String>>{

            override fun onResponse(
                call: Call<MutableList<String>>,
                response: Response<MutableList<String>>
            ) {
                if (response.isSuccessful) {
                    val categoryList = response.body()
                    if (!categoryList.isNullOrEmpty()) {
                        categoryAdapter.addCategories(categoryList)
                    }
                } else {
                    Toast.makeText(context, "Error al obtener las categorias", Toast.LENGTH_SHORT).show()
                }
                isLoadingPage = false
            }

            override fun onFailure(call: Call<MutableList<String>>, t: Throwable) {
                Toast.makeText(context, "Error de red", Toast.LENGTH_SHORT).show()
                isLoadingPage = false
            }
        })
    }

    override fun onItemClick(category: String){
        Toast.makeText(context, "Categoría seleccionada ${category}", Toast.LENGTH_LONG).show()
    }
}

interface OnCategoryClickListener{
    fun onItemClick(category: String)
}