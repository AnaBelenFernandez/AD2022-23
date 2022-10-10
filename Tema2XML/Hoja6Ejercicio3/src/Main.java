import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       /*Pide por teclado el código de un equipo y su localidad y graba, en un fichero cuyo nombre
se introduzca por teclado, los datos de los futbolistas del fichero futbolistas.xml. El
elemento raíz del fichero es <futbolistas> y tendrá los atributos codeq y localidad. En el atributo localidad se asignará el valor
de un nombre de localidad que se pedirá por teclado. Dentro de <futbolistas> habrá un elemento <futbolista> por cada futbolista del
equipo que contendrá elementos para el alias, la posición y la altura.*/
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduce código de Equipo");
        String codEquipo = teclado.nextLine();
        String localidad = "Madrid";
        Document docEquipo=leerXmlADoc(codEquipo, localidad);
        grabarDocAXml(docEquipo);


    }
    private static void grabarDocAXml(Document doc) {
        try {
            Source domSource = new DOMSource(doc);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            Result result = new StreamResult(Files.newBufferedWriter(Paths.get("futbolistasEquipo.xml")));
            transformer.transform(domSource, result);
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }
    private static Document leerXmlADoc(String codEquipo, String localidad) {
        DocumentBuilderFactory dBFactory = DocumentBuilderFactory.newInstance();
        Document doc;
        Document docEquipo;
        try {
            DocumentBuilder builder = dBFactory.newDocumentBuilder();
            doc = builder.parse("futbolistas.xml");
            docEquipo = builder.newDocument();
            Element raiz = doc.getDocumentElement();
            Element elementFutbolistasEquipo = docEquipo.createElement("futbolistas");
            docEquipo.appendChild(elementFutbolistasEquipo);
            Element raizEquipo = docEquipo.getDocumentElement();
            raizEquipo.setAttribute("codEq", codEquipo);
            raizEquipo.setAttribute("localidad", localidad);
            NodeList listaFutbolistas = raiz.getElementsByTagName("futbolista");
            for (int i = 0; i < listaFutbolistas.getLength(); i++) {
                Node nodoFutbolista = listaFutbolistas.item(i);
                Element element = (Element) nodoFutbolista;
                if (element.getElementsByTagName("codEq").item(0).getTextContent().equalsIgnoreCase(codEquipo)) {
                    Element elementFutbolista = docEquipo.createElement("futbolista");
                    elementFutbolistasEquipo.appendChild(elementFutbolista);

                    Element elementAlias = docEquipo.createElement("alias");
                    String alias = element.getElementsByTagName("alias").item(0).getTextContent();
                    elementAlias.appendChild(docEquipo.createTextNode(alias));
                    elementFutbolista.appendChild(elementAlias);

                    Element elementPosicion = docEquipo.createElement("puesto");
                    String puesto = element.getElementsByTagName("puesto").item(0).getTextContent();
                    elementPosicion.appendChild(docEquipo.createTextNode(puesto));
                    elementFutbolista.appendChild(elementPosicion);


                    Element elementAltura = docEquipo.createElement("altura");
                    String altura = element.getElementsByTagName("altura").item(0).getTextContent();
                    elementAltura.appendChild(docEquipo.createTextNode(altura));
                    elementFutbolista.appendChild(elementAltura);

                }
            }
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }

        return docEquipo;
    }
}