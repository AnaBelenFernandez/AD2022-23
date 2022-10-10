fun main(args: Array<String>) {
    val articulo1 = Articulo(1, "Chaqueta", 2)
    val articulo2 = Articulo(2, "Pantalón", 20)
    val articulo3 = Articulo(3, "Camiseta", 3)
    val articulo4 = Articulo(4, "Zapato", 1)
    val articulo5 = Articulo(5, "Bota", 2)
    val almacen = Almacen()
    almacen.Insertar(articulo1)
    almacen.Insertar(articulo2)
    almacen.Insertar(articulo3)
    almacen.Insertar(articulo4)
    almacen.Insertar(articulo5)

    println(almacen.Buscar(2).toString())
    println(almacen.Pedidos())
    println(almacen.articulos)
    almacen.articulos.forEach {
        println(it)
    }
}