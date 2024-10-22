package school.ibaktor.smartshopsstudent.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import school.ibaktor.smartshopsstudent.R
import school.ibaktor.smartshopsstudent.models.Product
import school.ibaktor.smartshopsstudent.services.apiFakeStore.RetrofitInstance
import school.ibaktor.smartshopsstudent.ui.adapters.ProductAdapter

class ProductsFragment : Fragment(), OnItemClickListener {

    private lateinit var rvProductsGrid: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private var isLoadingPage = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewFragment = inflater.inflate(R.layout.fragment_products, container, false)

        rvProductsGrid = viewFragment.findViewById(R.id.rvProductsGrid)
        rvProductsGrid.layoutManager = GridLayoutManager(context, 2)

        setupAdapter()
        setupListeners()
        fetchProducts()

        return viewFragment
    }

    fun setupAdapter(){
        productAdapter = ProductAdapter(mutableListOf<Product>(), this@ProductsFragment)
        rvProductsGrid.adapter = productAdapter
    }

    fun setupListeners(){
        rvProductsGrid.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as GridLayoutManager
                val totalItemCount = layoutManager.itemCount
                val lastVisivibleItem = layoutManager.findLastVisibleItemPosition()

                if (!isLoadingPage && lastVisivibleItem >= totalItemCount - 1) {
                    fetchProducts()
                }
            }
        })
    }

    fun fetchProducts(){
        isLoadingPage = true
        val apiService = RetrofitInstance.apiService
        //apiService.getProducts(16).enqueue(object : Callback<MutableList<Product>>{
        apiService.getJewelery().enqueue(object : Callback<MutableList<Product>>{

            override fun onResponse(call: Call<MutableList<Product>>, response: Response<MutableList<Product>>){
                if (response.isSuccessful) {
                    val productList = response.body()
                    //rvProductsGrid.adapter = productList?.let { ProductAdapter(it, this@ProductsFragment) }
                    if (!productList.isNullOrEmpty()) {
                        productAdapter.addProducts(productList)
                    }
                }else{
                    Toast.makeText(context, "Error al obtener los productos", Toast.LENGTH_SHORT).show()
                }
                isLoadingPage = false
            }

            override fun onFailure(call : Call<MutableList<Product>>, t: Throwable){
                Toast.makeText(context, "Error de red", Toast.LENGTH_SHORT).show()
                isLoadingPage = false
            }
        })
    }

    override fun onItemClick(product: Product){
        Toast.makeText(context, "Item seleccionado ${product.title}", Toast.LENGTH_LONG).show()
    }
}

interface OnItemClickListener{
    fun onItemClick(product: Product)
}