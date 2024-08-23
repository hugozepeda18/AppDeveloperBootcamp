/* 
 * 13.3 / Ejercicio 1 
 */
class Producto(var nombre: String, var precio: Int) 

fun promedioProductos(productos: Map<String, Int>): Int {
	var suma = 0
    for ( (key, pair) in productos) {
		suma+= pair
    }
    return (suma/productos.count())
}

fun main() {
    val producto1 = Producto("Computador", 10000)
   	val producto2 = Producto("Impresora", 5000)
    
    val emptyMutableMap = mutableMapOf<String, Int>()
	emptyMutableMap[producto1.nombre] = producto1.precio
    emptyMutableMap[producto2.nombre] = producto2.precio
   	print(promedioProductos(emptyMutableMap))
}