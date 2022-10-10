fun main(args: Array<String>) {

    //ejercicio1()
    //ejercicio2()
    //ejercicio3()
    //ejercicio4()
    ejercicio5()

}

fun ejercicio5() {
    /*Crea un array de enteros de 6 posiciones. Para ello utiliza la clase IntArray. Tiene un constructor
que recibe el número de elementos del array: var numeros = IntArray(6)
Rellena el array con 6 números aleatorios distintos comprendidos entre 1 y 49 teniendo en cuenta
que no se pueden repetir los números.
Después imprime los números con el siguiente formato 1,2,3,4,5,6*/
    var numeros = IntArray(6)
    //rellenar el array con numeros aleatorios
    for (i in numeros.indices) {
        var aleatorio: Int
        do {//me busca un aleatorio que no esté en números
            aleatorio = (1..49).random()
        } while (aleatorio in numeros)
        numeros[i] = aleatorio

    }
    println(numeros.joinToString(","))
}

fun ejercicio4() {
    /*EJERCICIO 4
    Programa que nos pide un número entero positivo y luego nos permita realizar la opción que
    pulsemos en el siguiente menú:
    1. Contar cuantas cifras tiene
    2. Escriba sus 10 primeros múltiplos
    3. Decir si es perfecto
    4. Salir
    Nota: un número perfecto es un número entero positivo que es igual a la suma de sus divisores
    positivos.*/
    var opcion: Int = 0
    var numero: Int = 0;
    println("Introduce un número para operar")
    //aquí va un do while para comprobar que el número sea positivo, no un if chapuza mía
    numero = readln().toInt()
    if (numero > 0) {
        println(
            """Introduce una opción de menú
            1. Contar cuantas cifras tiene 
            2. Escriba sus 10 primeros múltiplos
            3. Decir si es perfecto
            4. Salir """
        )
        opcion = readln().toInt()

        when (opcion) {
            1 -> {
                println("El número de dígitos es ${numero.toString().length}")
            }
            2 -> {
                for (i in 1..10)
                    println("${numero * i}")
            }
            3 -> {
                var perfecto = 0
                for (i in 1..numero / 2) {
                    if (numero % i == 0) {
                        perfecto += i
                    }
                    println("El número" + if (perfecto != numero) "no es perfecto" else "es perfecto")//en kotlin se puede poner el if else en el println
                }
            }
        }
    } else
        println("Introduce un número entero mayor que cero")
}

fun ejercicio3() {
    /*EJERCICIO 3
Escribe un programa que calcule la media de un conjunto de números positivos introducidos por
teclado. A priori, el programa no sabe cuántos números se introducirán. El usuario indicará que ha
terminado de introducir los datos cuando meta un número negativo.*/
    var numero: Int = 0
    var cantidad: Int = 0
    var suma: Int = 0
    do {
        println("Introduce un número")
        numero = readln().toInt()
        suma += numero
        cantidad++
    } while (numero > 0)
    println("la media es ${suma / cantidad}")
}

fun ejercicio2() {
    /*EJERCICIO 2
        Programa que presente por pantalla una cuenta atrás empezando por 100 y finalizando en 0, al final
        escribirá el literal*/
    for (numero in 100 downTo 0)
        if (numero != 0) {
            println(numero)
        } else {
            println("Despegue!!")
        }
}

fun ejercicio1() {
    /*EJERCICIO 1
Programa que pide una serie de caracteres por teclado, la serie finaliza al teclear el carácter punto
‘.’.Al final nos dirá cuantos caracteres hemos tecleado.
*/
    var caracter: Char
    var numero: Int = 0
    do {
        println("Introduce una caracter")
        caracter = readln()[0]
        numero++
    } while (!caracter.equals("."))
    println("Has tecleado $numero caracteres")
}




