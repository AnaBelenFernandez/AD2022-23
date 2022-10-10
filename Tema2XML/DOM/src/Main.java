import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        // FACTORY Crear una instancia de DocumentBuilderFactory que permitirá construir parsers
        DocumentBuilderFactory dBFactory = DocumentBuilderFactory.newInstance();
// Dentro de un try catch, construir el parser
        try {
            //BUILDER
            DocumentBuilder builder = dBFactory.newDocumentBuilder();
            /*-----------------------------DOCUMENTO-----------------------------------------------------------------*/
            // Crear un documento vacío
// La clase DOCUMENT pertenece al paquete org.w3c.dom: es un objeto de tipo árbol que representa en memoria lo que
            //después se va a reproducir en el xml
            Document doc = builder.newDocument();
// Asignamos cual va a ser la version de nuestro XML
            doc.setXmlVersion("1.0");

            /*-----------------------------ELEMENTO RAÍZ.......................................-------------...............*/
            //Crear el ELEMENTO dentro del Document doc
            Element elementAlumnos = doc.createElement("alumnos");
//Añadir el elemento al documento (se convierte en raíz):sólo puede tener un hijo, daría excepción sino
            //appenchild sirve para añadir elementos y contenido
//El elemento queda unido a la raíz de doc
            doc.appendChild(elementAlumnos);
            /*-----------------------------ATRIBUTO-------------------------------------------------------------*/
            //Se pueden establecer valores de ATRIBUTOS de un elemento
//Poner un atributo grupo de valor DAM2 al elemento alumnos
            elementAlumnos.setAttribute("grupo", "DAM2");
//Poner un atributo aula de valor IF03 al elemento alumnos
//Se pueden asignar atributos en cualquier momento
            elementAlumnos.setAttribute("aula", "IF03");
            /*----------------------------------SUBELEMENTO---------------------------------------------------------------------*/
            //Creamos un nuevo elemento alumno en el documento
//El elemento no está enganchado en el árbol
            Element elementAlumno = doc.createElement("alumno");
//Añadir el elemento alumno dentro del elemento alumnos
//En este momento alumno queda enganchado en el árbol a alumnos
            elementAlumnos.appendChild(elementAlumno);
            /*--------------------------------------------DATOS:TEXT NODE-----------------------------------------------------------------*/
            //el contenido de texto(textNode) es hijo de las etiquetas
            //Creamos un nodo para la etiqueta nombre (siempre se crean en el documento)
            Element elementNombre=doc.createElement("nombre");
//Le asignamos un nodo de tipo TextNode creado en el doc
            elementNombre.appendChild(doc.createTextNode("Ana Alonso"));
// Y lo añadimos al nodo alumno
            elementAlumno.appendChild(elementNombre);
// Añadimos otro nodo con valor para la edad del alumno
            Element elementEdad=doc.createElement("edad");
            Text textEdad=doc.createTextNode("20");
            elementEdad.appendChild(textEdad);
            elementAlumno.appendChild(elementEdad);
            /*------------------------------TRANSFORMACIÓN A FICHERO-----------------------------------------------------------------*/
            /*sacarlo a un méetodo que recibe el doc y el fichero*/
            //Ahora tenemos creado el documento (en memoria) vamos a crear el fuente XML a partir del document
            /*necesitamos un trasnformer, un source y un result*/
            Source source=new DOMSource(doc);
//A partir del fuente se construye un objeto para almacenar el resultado en el archivo alumnos.xml
            Result result = new StreamResult(Files.newBufferedWriter(Paths.get("alumnos.xml")));
// Ahora hay que crear un objeto TransformerFactory para hacer La transformación y convertir el documento en fichero
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT,"yes");
            transformer.transform(source, result);
            /*---------------------------------COMPROBACIÓN DEL FICHERO----------------------------------------------------------------*/
            //con api stream
            Files.readAllLines(Paths.get("alumnos.xml"))
                    .stream()
                    .forEach(System.out::println);
            //con BufferedReader
            BufferedReader br=new BufferedReader(new FileReader("alumnos.xml"));
            String linea=br.readLine();
            while(linea!=null){
                System.out.println(linea);
                linea=br.readLine();
            }
            br.close();




        } catch (ParserConfigurationException e) {
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }
}