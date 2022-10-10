import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class Hoja101 {
    public static void main(String[] args) {
//ejercicio1(".");
//ejercicio2();
        //ejercicio3();
        try {
            ejercicio4();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        renombrarFichero();
    }

    private static void renombrarFichero() {
        Scanner teclado=new Scanner(System.in);
        System.out.println("Introduce ruta y nombre del fichero a rennonmbrar");
        String ruta=teclado.nextLine();
        File fichero=new File(ruta);
        if(fichero.exists()){
            System.out.println("Escribe el nuevo nombre del fichero");
            String nuevoNombre=teclado.nextLine();
            File newFile=new File(fichero.getParent(), nuevoNombre);
            if(fichero.renameTo(newFile)){
                System.out.println("se ha renombrado el fichero");
            }

        }
    }


    private static void ejercicio4() throws IOException {
        /*Crea un programa con un menú como el siguiente:
1.- Comprobar si es directorio
2.- Obtener ficheros en directorio
3.- Obtener tamaño de fichero
4.- Obtener ruta del directorio actual
5.- Eliminar fichero
6.- Mover fichero
7.- Renombrar fichero
0.- Salir*/
        Scanner teclado = new Scanner(System.in);
        int opcion = -1;
        String ruta;
        do {
            System.out.println("1.- Comprobar si es directorio\n" +
                    "2.- Obtener ficheros en directorio\n" +
                    "3.- Obtener tamaño de fichero\n" +
                    "4.- Obtener ruta del directorio actual\n" +
                    "5.- Eliminar fichero\n" +
                    "6.- Mover fichero\n" +
                    "7.- Renombrar fichero\n" +
                    "0.- Salir*/");
            opcion = teclado.nextInt();
            File directorio = null;
            switch (opcion) {
                case 1:
                    /* Se pide por teclado la ruta de un directorio y se responde si existe y si es directorio.  */
                    //System.out.println("introduce una ruta");
                    //teclado.next();
                    //String ruta = teclado.nextLine();
                    directorio
                            = new File("pom.xml");
                    if (directorio.exists()) {
                        System.out.println("La ruta existe");
                        if (directorio.isDirectory()) {
                            System.out.println("Es un directorio");
                        } else {
                            System.out.println("No es un directorio. Es un archivo");
                        }
                    } else {
                        System.out.println("El directorio no existe");
                    }
                    break;
                case 2:
                    /*Se pide por teclado la ruta del directorio y se listan en pantalla los ficheros y
subdirectorios que contiene. Después de cada nombre se escribe D o F según sea
subdirectorio o fichero.*/
//System.out.println("introduce una ruta");
                    // teclado.next();
                    ruta = ".";
                    directorio = new File(ruta);
                    File[] listaFicheros = directorio.listFiles();
                    for (File fichero : listaFicheros) {
                        System.out.print(fichero.getName());
                        if (fichero.isDirectory()) {
                            System.out.println("--> D");
                        } else {
                            System.out.println("   --> F");
                        }
                    }
                    break;
                case 3:
                    /*Se pide por teclado la ruta de un directorio y el nombre de un fichero de ese
directorio y, si existe, se escribe su tamaño en bytes.*/
                    ruta = ".";
                    String nombre = "pom.xml";
                    directorio = new File(ruta, nombre);
                    if (directorio.exists()) {
                        System.out.println(directorio.length() + " bytes");
                    } else {
                        System.out.println("El directorio no existe");
                    }
                    break;
                case 4:
                    /*: Se debe escribir en pantalla la ruta absoluta del directorio actual de trabajo.
                     */
                    directorio = new File(".");
                    System.out.println(directorio.getAbsolutePath());
                    break;
                case 5:
                   /* Se pide por teclado la ruta de un fichero junto con su nombre y si existe se
                    elimina. Si no existe, se da un mensaje en pantalla.*/
                    ruta = "D:\\Usuarios\\DAM212\\Documents\\AD\\Tema1\\Hoja1-01\\NUEVODIR";
                    nombre = "fichero1";
                    directorio = new File(ruta, nombre);
                    if (directorio.exists()) {
                        directorio.delete();
                    } else {
                        System.out.println("El directorio no existe");
                    }
                    break;
                case 6:
                    /*: Se pide por teclado la ruta de directorio y el nombre de un fichero de ese
directorio y, si existe, se mueve el fichero al directorio que se indique por teclado. En el
directorio destino tendrá el mismo nombre que en el origen.
*/
                    ruta = "D:\\Usuarios\\DAM212\\Documents\\AD\\Tema1\\Hoja1-01\\NUEVODIR";
                    nombre = "fichero1";
                    String rutaNueva = "D:\\Usuarios\\DAM212\\Documents\\AD\\Tema1\\Hoja1-01\\carpetaCreada";
                    directorio = new File(ruta, nombre);
                    File nuevoDirectorio = new File(rutaNueva, nombre);
                    //con la NIO
                    Path archivo = Paths.get(ruta, nombre);
                    Path copia = Paths.get("Copia.txt");
                    Files.copy(archivo, copia, StandardCopyOption.REPLACE_EXISTING);
//Lo movemos a otro directorio
                    copia = Files.move(copia, Paths.get(rutaNueva, "fichero1.txt"), StandardCopyOption.REPLACE_EXISTING);
                    break;
                case 7:
                    /*: Se pide por teclado la ruta de directorio y el nombre de un fichero de ese
directorio y, si existe, se renombra el fichero con el nombre que se dé por teclado.*/
                    ruta = "D:\\Usuarios\\DAM212\\Documents\\AD\\Tema1\\Hoja1-01\\NUEVODIR";
                    nombre = "fichero1";
                    directorio = new File(ruta, nombre);
                    File renombrar = new File(ruta, "archivorenombrado");
                    directorio.renameTo(renombrar);
                    break;
            }
        } while (opcion != 0);
    }

    private static void ejercicio3() {
        /*Realiza un programa en Java que crea un directorio (NUEVODIR) en el directorio
actual. Después crea dos ficheros vacíos en dicho directorio y renombra uno de ellos.*/
        File directorio = new File(".");
        File nuevoDirectorio = new File("NUEVODIR");
        if(!nuevoDirectorio.exists()) {
            nuevoDirectorio.mkdir();
            File fichero1 = new File(nuevoDirectorio, "fichero1");
            File fichero2 = new File(nuevoDirectorio, "fichero2");
            try {
                if(fichero1.createNewFile()){//devuelve booleano
                    System.out.println("fichero creado correctamente");
                }
                else{
                    System.out.println("no se ha podido crear fichero 1");
                }
                if(fichero2.createNewFile()){//devuelve booleano
                    System.out.println("fichero creado correctamente");
                }
                else{
                    System.out.println("no se ha podido crear fichero 2");
                }
            } catch (IOException e) {
                System.out.println("Error en creación de ficheros");
                System.out.println(e.getMessage());
            }
            fichero2.renameTo(new File(nuevoDirectorio, "Fichero Renombrado"));//se puede instanciar en el argumento
        }else{
            System.out.println("El directorio ya existe");
        }
    }

    private static void ejercicio2() {
        /*Realiza un programa en Java que muestre la siguiente información de un fichero (uno
que tengas en el directorio actual):
• Nombre
• Ruta y ruta absoluta
• Si se puede escribir
• Si se puede leer*/
        File fichero = new File("pom.xml");
        System.out.println(fichero.getPath());
        System.out.println(fichero.getAbsolutePath());
        if (fichero.canRead()) {
            System.out.println(fichero.canRead());
        } else {
            System.out.println("El fichero no se puede leer");
        }
        if (fichero.canWrite()) {
            System.out.println(fichero.canWrite());
        } else {
            System.out.println("El fichero no admite escritura");
        }
        System.out.println(fichero.getName());
    }

    private static void ejercicio1(String ruta) {
        /*EJERCICIO 1
Realiza un programa en Java que muestre los nombres de los ficheros de un directorio y
su tamañoen bytes.
El nombre del directorio se pasará al programa desde la línea de comandos al ejecutarlo*/
        //Scanner teclado = new Scanner(System.in);
        //System.out.println("Escribe un nombre de directorio");
        //String ruta = teclado.nextLine();
        File directorio = new File(ruta);
        if (directorio.exists()) {
            System.out.println("Ficheros del directorio " + ruta);
            File[] listaFicheros = directorio.listFiles();
            for (File fichero : listaFicheros) {
                System.out.println(fichero.getName());
                System.out.println(fichero.length() + " bytes");
            }
        } else {
            System.err.println("ERROR. No existe el directorio " + ruta);
        }
    }
}
