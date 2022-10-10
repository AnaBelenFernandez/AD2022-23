import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
try {
    //Declaramos e instanciamos un objeto factoría para poder crear un objeto
// parseador. SAXParserFactory es una clase abstracta y no tiene constructor
    SAXParserFactory factoria = SAXParserFactory.newInstance();
// Declaramos un parseador para SAX y lo construimos con factoria
// Genera la excepcion ParserConfigurationException
    SAXParser parser = factoria.newSAXParser();
//Ahora es cuando se procede a parsear el fichero XML. Al método que parsea
// hay que pasarle un File del fichero a parsear y un handler o manejador
// (este debe ser un objeto de una clase creada a partir de DefaultHandler
// Define como es el documento y como se tiene que interpretar
    AlumnosHandler manejador = new AlumnosHandler();
    File f = new File("Alumnos.xml");
//parseamos usando el handler. Genera la excepcion IOException
    parser.parse("Alumnos.xml", manejador);
    System.out.println("Contenido de la lista de alumnos");
    manejador.getListaAlumnos().forEach(x ->
            System.out.println(x.getNombre()+ "----- edad : "+x.getEdad()));

} catch (ParserConfigurationException e) {
    throw new RuntimeException(e);
} catch (IOException e) {
    throw new RuntimeException(e);
} catch (SAXException e) {
    throw new RuntimeException(e);
}
    }
}