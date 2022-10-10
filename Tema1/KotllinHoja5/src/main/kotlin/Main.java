public class Main {
    public static void main(String[] args) {
        Articulo articulo1 = new Articulo(01, "Chaqueta", 2);
        Articulo articulo2 = new Articulo(02, "Pantalón", 20);
        Articulo articulo3 = new Articulo(03, "Camiseta", 3);
        Articulo articulo4 = new Articulo(04, "Zapato", 1);
        Articulo articulo5 = new Articulo(05, "Bota", 2);
        Almacen almacen=new Almacen();
        almacen.Insertar(articulo1);
        almacen.Insertar(articulo2);
        almacen.Insertar(articulo3);
        almacen.Insertar(articulo4);
        almacen.Insertar(articulo5);

       System.out.println(almacen.Buscar(02).toString());
       System.out.println(almacen.Pedidos());
       System.out.println(almacen.getArticulos());
       almacen.articulos.forEach{println(it)};

    }
}
