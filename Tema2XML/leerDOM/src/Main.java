import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        //leer un fichero xml
        Element elementNombre=null;
        DocumentBuilderFactory dBFactory = DocumentBuilderFactory.newInstance();
        try {dBFactory.setIgnoringComments(true);
            dBFactory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder builder = dBFactory.newDocumentBuilder();
            Document doc = builder.parse("Alumnos.xml");
            Element raiz=doc.getDocumentElement();
            System.out.println("Raiz :"+raiz.getNodeName());
            NodeList listaAlumnos=raiz.getElementsByTagName("Alumno");
            System.out.println("Hay cargados "+listaAlumnos.getLength()+ "alumnos");
            for (int i=0;i< listaAlumnos.getLength();i++){
                Element element=(Element) listaAlumnos.item(i);
                System.out.println(element.getTagName());
                String textAlumno =element.getElementsByTagName("Nombre").item(0).getTextContent();
//Dentro del nodo alumno, de una lista de elementos cuya etiqueta es edad,
//se extrae su elemento 0 (el único) y de él se saca el texto con el valor
                textAlumno=textAlumno+ ", " +element.getElementsByTagName("Edad").item(0).getTextContent();
                System.out.println(textAlumno);
                //Del element del alumno obtenemos una lista de los nodos con etiqueta modulos que tiene
                NodeList modulos=element.getElementsByTagName("Modulos");
//Como sabemos que para cada element hay solo un elemento modulos
//Obtenemos ese elemento (el elemento 0 de la lista)
                element=(Element)(modulos.item(0));
                elementNombre=(Element) raiz.getElementsByTagName("Nombre").item(0);
//A partir del elemento de la etiqueta modulos
//Obtenemos una lista de los elementos de etiqueta modulo que hay dentro
                NodeList listaModulos=element.getElementsByTagName("modulo");
    }elementNombre=(Element) raiz.getElementsByTagName("Nombre").item(0);
            cambiarNombre(doc,elementNombre);

} catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }}

    private static void cambiarNombre(Document doc, Element element) {
        String textAlumno= element.getTextContent();
        if(textAlumno.equalsIgnoreCase("ana alonso")){
            Text nuevoText=doc.createTextNode("Anne Alonso");
            Text text=(Text) element.getChildNodes().item(0);
            element.replaceChild(nuevoText,text);
        }
    }
}