package school.ibaktor.smartshopsstudent.models

/*
*             [
                {
                    id:1,
                    title:'...',
                    price:'...',
                    category:'...',
                    description:'...',
                    image:'...'
                }
            ]
            {
            *   "id":1,
            *   "title":"Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
            *   "price":109.95,
            *   "description":"",
            *   "category":"men's clothing",
            *   "image":"https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
            *   "rating":{"rate":3.9,"count":120}
            * }
* */

data class Product (
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val image: String
)