import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class leerArchiov {

    public static void main(String[] args) {
        // declarar el fichero y construir el objeto FileReader
        File fichero = new File("pom.xml");
        FileInputStream lector = null; //crea el flujo de entrada
        try {
            int i;
            lector = new FileInputStream(fichero);
            while ((i = lector.read()) != -1) {
                System.out.print((char) i);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            lector.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
