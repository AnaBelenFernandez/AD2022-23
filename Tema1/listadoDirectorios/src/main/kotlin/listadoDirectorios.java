import java.io.File;

public class listadoDirectorios {
    public static void main(String[] args) {
        /*Crear un programa que escriba un listado del nombre de ficheros y subdirectorios que haya en el directorio actual de trabajo.
Para hacer el programa tienes que tener en cuenta:
El directorio actual de trabajo es “.”
Hay que instanciar un objeto File para manejar ese directorio actual
De ese objeto File se puede obtener la lista de los nombres de sus elementos(ficheros y subdirectorios)con el método list()o de objetos File con el método listFiles()
Habrá que recorrer la estructura de datos en que se almacena esa lista para obtener los nombres de esos elementos.*/

        //en primer lugar cargamos los nombres de fichero a un array de Strings
        File directorio = new File(".");
        System.out.println("Ficheros de directorio actual:" + directorio.getAbsolutePath());
        String[] archivos = directorio.list();
        for (int i = 0; i < archivos.length; i++) {
            System.out.println(archivos[i]);
        }
        //aqyí lo hacemos con array de Files en vez de Strings
        File directorio1 = new File(".");
        System.out.println("Ficheros de directorio actual:" + directorio1.getAbsolutePath());
        File[] listaFicheros = directorio1.listFiles();
        for (File fichero: listaFicheros)
        {
            System.out.println(fichero.getName());
        }
    }
}
