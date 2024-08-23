/* 
 * 13.3 / Ejercicio 2
 */
class Operator {
    fun add(a: List<Int>): Int {
        val b = a.toMutableList()
        var sum = 0
        for (element in b) {
            sum += element
        }
        return sum
    }
    fun inc(a: List<Int>): List<Int> {
        val b = a.map{ it + 1 }
        return b
    }
}

fun sumarLista(lista: List<Int>, opr: (List<Int>) -> Int) = opr(lista)
fun operarLista(lista: List<Int>, opr: (List<Int>) -> List<Int>) = opr(lista)

fun main() {
    val items = mutableListOf(1, 2, 3, 4, 5)
    println(sumarLista(items, {item -> Operator().add(item)}))
    println(operarLista(items, {item -> Operator().inc(item)}))
}