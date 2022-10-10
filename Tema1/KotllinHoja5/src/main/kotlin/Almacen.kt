class Almacen() {
    /*Crear otra clase llamada Almacén que tenga como una colección que no permita duplicados y
almacene objetos de la clase Artículo, un método llamado insertar que permita añadir, un método
buscar que recibe un código y nos devuelve un artículo y un método pedidos que nos devuelva
lista con los artículos que tienen existencias por debajo de 5 unidades ordenados ascendentemente. */
    val articulos = linkedSetOf<Articulo>()

    fun Insertar(articulo: Articulo): Boolean = articulos.add(articulo)
    fun Buscar(cod: Int): Articulo? {
        return articulos.filter { a -> a.codigo == cod }.firstOrNull()
    }

    fun Pedidos(): List<Articulo> {
        return articulos.filter { a -> a.existencias < 5 }.sortedBy { articulo -> articulo.existencias }
    }
}