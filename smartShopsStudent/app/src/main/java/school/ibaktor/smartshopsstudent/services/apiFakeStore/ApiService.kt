package school.ibaktor.smartshopsstudent.services.apiFakeStore

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import school.ibaktor.smartshopsstudent.models.Product

interface ApiService {
    //https://fakestoreapi.com/products?limit=15
    @GET("products")
    fun getProducts(@Query("limit") limit: Int) : Call<MutableList<Product>>

    //https://fakestoreapi.com/products/categories
    @GET("products/categories")
    fun getCategories() : Call<MutableList<String>>

    //https://fakestoreapi.com/products/category/jewelery
    @GET("products/category/jewelery")
    fun getJewelery() : Call<MutableList<Product>>

}