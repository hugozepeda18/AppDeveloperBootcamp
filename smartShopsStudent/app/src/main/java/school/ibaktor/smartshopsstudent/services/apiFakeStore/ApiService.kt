package school.ibaktor.smartshopsstudent.services.apiFakeStore

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import school.ibaktor.smartshopsstudent.models.Product

interface ApiService {
    //https://fakestoreapi.com/products?limit=15
    @GET("products")
    fun getProducts(@Query("limit") limit: Int) : Call<MutableList<Product>>
}