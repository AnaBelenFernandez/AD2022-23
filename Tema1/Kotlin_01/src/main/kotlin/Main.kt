import java.util.*

fun main(args: Array<String>) {

    /*Crea un array llamado alumnos con tu nombre y el de dos de tus compañeros.
    Crea un menú con las siguientes opciones:
    1. Iniciales
    2. Posición
    3. Buscar letra
    4. Desplazar
    5. Salir
    Opción 1:
    Escribe por pantalla la primera letra seguida de punto (“.”) de todos los nombres
    Opción 2:
    Solicita al usuario una posición (utilizando el método readln) y escribe el nombre que está en esa
    posición. Se deberá comprobar que la posición sea una posición correcta del array
    Opción 3:
    Solicita al usuario una letra y escribe aquellos nombres que tengan esa letra (sin importar que esté
    en mayúsculas o minúsculas)
    Opción 4:
    Desplaza todos los nombres un lugar a la derecha de forma que el último pasa a ocupar la primera
    posición. Para comprobar que funciona correctamente, imprime todo el array*/

    val nombres = arrayOf<String>("Ana", "Andrea", "Aaron")
    var opc = 0
    do {
        println(
            "Elige una opcion \n" +
                    "1. Iniciales\n" +
                    "2. Posición\n" +
                    "3. Buscar letra\n" +
                    "4. Desplazar\n" +
                    "5. Salir"
        )
        opc = readln().toInt()
        when (opc) {
            1 -> {
                for (nombre in nombres) {
                    println(nombre[0])
                    print(".")
                }
                //nombres.forEach { s->print(s[0]) }
            }

            2 -> {
                println("introduce posicion")
                val posicion = readln().toInt()
                if (posicion > nombres.size && posicion >= 0) {
                    println(nombres[posicion])
                } else {
                    println("la posición no es correcta")
                }
            }

            3 -> {
                println("Escribe una letra")
                val letra = readln()[0].lowercase()
                nombres.filter { it.lowercase().contains(letra) }.forEach { (println(it)) }
            }

            4 -> {
                var ultimo = nombres.last()
                for (i in nombres.size - 1 downTo 1) {
                    nombres[i] = nombres[i - 1]
                }
                nombres[0] = ultimo
                nombres.forEach { println(it) }
            }
        }
    } while (opc != 5)
}