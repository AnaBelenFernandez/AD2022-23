import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Futbolista> futbolistas = new ArrayList<>();
        futbolistas = leerficheroDat();
        //leerficherodeotramanera();
        int opcion;
        Document doc = construirFichero(futbolistas);
        do {
            Scanner teclado = new Scanner(System.in);
            System.out.println("Elige una opción");
            System.out.println("1.- Listar futbolistas\n" +
                    "2.- Añadir futbolista\n" +
                    "3.- Consultar futbolista por número\n" +
                    "4.- Modificar equipo de futbolista\n" +
                    "5.- Modificar altura de futbolista\n" +
                    "6.- Eliminar futbolista\n" +
                    "7.- Grabar en fichero");
            opcion = teclado.nextInt();

            switch (opcion) {
                case 1:
                    /*Opción 1: Realiza un listado encolumnado con los datos de todos los futbolistas.*/
                    listarFutbolistas(doc);
                    break;
                case 2:
                    Futbolista futbolista = new Futbolista();
                    futbolista.setNum(4);
                    futbolista.setAlias("Ronaldo");
                    futbolista.setCodEq("BAR");
                    futbolista.setPuesto(2);
                    futbolista.setAltura(1.80f);
                    añadirFutbolista(futbolista, doc);
                    break;
                case 3:
                    consultarPorNumero(5, doc);
                    break;
                case 4:
                    modificarEquipo(5, doc);
                    break;
                case 5:
                            /*Pide que se introduzca por teclado un número de futbolista y, si existe, escribe
sus datos en pantalla, pide por teclado la altura del futbolista y, si tiene ya una altura
asignada, la modifica. Si no tiene elemento <altura> lo añade al futbolista.*/
                    cambiarAltura(5, doc);
                    break;
                case 6:
                            /*Pide que se introduzca por teclado un número de futbolista y, si existe, escribe
sus datos en pantalla y lo elimina del fichero.*/
                    borrarFutbolista(5, doc);
                    break;
                case 7:
                            /*7: Pide por teclado la ruta y nombre de un fichero XML y graba en ese fichero el
contenido actual del Document usado en el programa.*/
                    grabarDocAXml(doc);
                    break;
            }
        } while (opcion != 0);
    }

    private static void listarFutbolistas(Document doc) {
        Element raiz = doc.getDocumentElement();
        NodeList listaFutbolistas = raiz.getElementsByTagName("futbolista");
        for (int i = 0; i < listaFutbolistas.getLength(); i++) {
            Node nodoFutbolista = listaFutbolistas.item(i);
            Element element = (Element) nodoFutbolista;
            String textFutbolista = String.format(Locale.US, " %-10s |  %-15s |  %-3s |  %-5s | %-5s",
                    String.valueOf(element.getElementsByTagName("num").item(0).getTextContent()),
                    String.valueOf(element.getElementsByTagName("alias").item(0).getTextContent()),
                    String.valueOf(element.getElementsByTagName("codEq").item(0).getTextContent()),
                    String.valueOf(element.getElementsByTagName("puesto").item(0).getTextContent()),
                    String.valueOf(element.getElementsByTagName("altura").item(0).getTextContent()));
            System.out.println(textFutbolista);
        }
    }

    private static void grabarDocAXml(Document doc) {
        try {
            Source domSource = new DOMSource(doc);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            Result result = new StreamResult(Files.newBufferedWriter(Paths.get("futbolistasCase7.xml")));
            transformer.transform(domSource, result);
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    private static void borrarFutbolista(int num, Document doc) {
        Scanner teclado = new Scanner(System.in);
        boolean encontrado=false;
        Element raiz = doc.getDocumentElement();
        NodeList listaFutbolistas = raiz.getElementsByTagName("futbolista");
        for (int i = 0; i < listaFutbolistas.getLength(); i++) {
            Element element = (Element) listaFutbolistas.item(i);
            String numFutbolista = element.getElementsByTagName("num").item(0).getTextContent();
            if (numFutbolista.equalsIgnoreCase(String.valueOf(num))) {
                encontrado=true;
                raiz.removeChild(listaFutbolistas.item(i));
                System.out.println("futbolista borrado");
            }
        }
        if(!encontrado){
            System.out.println("Futbolista no encontrado");
        }
    }

    private static void cambiarAltura(int num, Document doc) {
        Scanner teclado = new Scanner(System.in);
        Element raiz = doc.getDocumentElement();
        boolean encontrado=false;
        NodeList listaFutbolistas = raiz.getElementsByTagName("futbolista");
        for (int i = 0; i < listaFutbolistas.getLength(); i++) {
            Element element = (Element) listaFutbolistas.item(i);
            String numFutbolista = element.getElementsByTagName("num").item(0).getTextContent();
            if (numFutbolista.equalsIgnoreCase(String.valueOf(num))) {
                encontrado=true;
                System.out.println("Introduce altura del jugador");
                String altura = teclado.nextLine();
                Element elAltura = (Element) element.getElementsByTagName("altura").item(0);
                Text texto = (Text) elAltura.getChildNodes().item(0);
                elAltura.removeChild(texto);
                Text nuevoTexto = doc.createTextNode(altura);
                elAltura.appendChild(nuevoTexto);
            }
        }
        if(!encontrado){
            System.out.println("Futbolista no encontrado");
        }
    }

    private static void modificarEquipo(int num, Document doc) {
        Scanner teclado = new Scanner(System.in);
        Element raiz = doc.getDocumentElement();
        boolean encontrado=false;
        NodeList listaFutbolistas = raiz.getElementsByTagName("futbolista");
        for (int i = 0; i < listaFutbolistas.getLength(); i++) {
            Element element = (Element) listaFutbolistas.item(i);
            String numFutbolista = String.valueOf(element.getElementsByTagName("num").item(0).getTextContent());
            System.out.println(numFutbolista);
            if (numFutbolista.equalsIgnoreCase(String.valueOf(num))) {
                encontrado=true;
                System.out.println(listaFutbolistas.item(i).toString());
                System.out.println("Introduce nuevo código de equipo");
                String codEquipo = teclado.nextLine();
                Element codigo = (Element) element.getElementsByTagName("codEq").item(0);
                Text texto = (Text) codigo.getChildNodes().item(0);
                codigo.removeChild(texto);
                Text nuevoTexto = doc.createTextNode(codEquipo);
                codigo.appendChild(nuevoTexto);
            }}
            if(!encontrado){
                System.out.println("futbolista no encontrado");
            }

        }

    private static void consultarPorNumero(int num, Document doc) {
        Element raiz = doc.getDocumentElement();
        NodeList listaFutbolistas = raiz.getElementsByTagName("futbolista");
        boolean encontrado=false;
        for (int i = 0; i < listaFutbolistas.getLength(); i++) {
            Element element = (Element) listaFutbolistas.item(i);
            String numFutbolista = element.getElementsByTagName("num").item(0).getTextContent();
            if (numFutbolista.equalsIgnoreCase(String.valueOf(num))) {
                Element f=(Element)listaFutbolistas.item(i);
                Futbolista fut=new Futbolista();
                fut.setNum(Integer.valueOf(f.getElementsByTagName("num").item(0).getTextContent()));
                fut.setAltura(Float.valueOf(f.getElementsByTagName("altura").item(0).getTextContent()));
                //fut.setPuesto(Integer.valueOf(f.getElementsByTagName("puesto").item(0).getTextContent()));
                fut.setCodEq(String.valueOf(f.getElementsByTagName("codEq").item(0).getTextContent()));
                fut.setAlias(String.valueOf(f.getElementsByTagName("alias").item(0).getTextContent()));
                System.out.println(fut.toString());
                encontrado=true;
            }
            if(encontrado=false){
                System.out.println("Futbolista no encontrado");
            }
        }
    }

    private static void añadirFutbolista(Futbolista futbolista, Document doc) {
        Element raiz = doc.getDocumentElement();
        String num = String.valueOf(futbolista.getNum());
        String alias = futbolista.getAlias();
        String codEq = String.valueOf(futbolista.getCodEq());
        String puesto = String.valueOf(futbolista.getPuesto());
        String altura = String.valueOf(futbolista.getAltura());
        //añado el nodo
        Element elementFutbolista = doc.createElement("futbolista");
        raiz.appendChild(elementFutbolista);
        //le meto el contenido
        Element elementNum = doc.createElement("num");
        elementNum.appendChild(doc.createTextNode(num));
        elementFutbolista.appendChild(elementNum);

        Element elementAlias = doc.createElement("alias");
        elementAlias.appendChild(doc.createTextNode(alias));//
        elementFutbolista.appendChild(elementAlias);


        Element elementCodEq = doc.createElement("codEq");
        elementCodEq.appendChild(doc.createTextNode(codEq));//
        elementFutbolista.appendChild(elementCodEq);

        Element elementPuesto = doc.createElement("puesto");
        elementPuesto.appendChild(doc.createTextNode(puesto));//
        elementFutbolista.appendChild(elementPuesto);

        Element elementAltura = doc.createElement("altura");
        elementAltura.appendChild(doc.createTextNode(altura));//
        elementFutbolista.appendChild(elementAltura);

    }

    private static void leerficherodeotramanera() {
        DocumentBuilderFactory dBFactory = DocumentBuilderFactory.newInstance();
        try {
            dBFactory.setIgnoringComments(true);
            dBFactory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder builder = dBFactory.newDocumentBuilder();
            Document doc = builder.parse("futbolistas.xml");
            Element raiz = doc.getDocumentElement();
            System.out.println("RAiz :" + raiz.getNodeName());
            NodeList listaAlumnos = raiz.getElementsByTagName("futbolista");
            System.out.println("Hay cargados " + listaAlumnos + "alumnos");
            for (int i = 0; i < listaAlumnos.getLength(); i++) {
                Element element = (Element) listaAlumnos.item(i);
                System.out.println(element.getTagName());

            }
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }

    private static Futbolista leerSig(DataInputStream lector) {
        Futbolista f = null;
        int num;
        String alias;
        String codEq;
        String apellidos;
        int puesto;
        float altura;
        try {
            if (lector.available() > 0) {
                f = new Futbolista();
                num = lector.readInt();
                alias = lector.readUTF();
                codEq = lector.readUTF();
                puesto = lector.readInt();
                altura = lector.readFloat();
                f.setNum(num);
                f.setAlias(alias);
                f.setCodEq(codEq);
                f.setPuesto(puesto);
                f.setAltura(altura);
            }
        } catch (IOException ex) {

        }
        return f;
    }

    private static List<Futbolista> leerficheroDat() {
        List<Futbolista> futbolistas = new ArrayList<>();
        File fichero = new File("futbolistas.dat");
        DataInputStream lector = null;
        try {
            boolean tienefutbolistas = false;
            lector = new DataInputStream(new FileInputStream(fichero));
            Futbolista f = leerSig(lector);
            while (f != null) {
                tienefutbolistas = true;
                System.out.println(String.format(Locale.US, " %-10d |  %-15s |  %-3s |  %-5d | %-5.2f", f.getNum(), f.getAlias(), f.getCodEq(), f.getPuesto(), f.getAltura()));
                futbolistas.add(f);
                f = leerSig(lector);
            }
            if (!tienefutbolistas) {
                System.out.println("El equipo no tiene cargados futbolistas");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            if (lector != null)
                lector.close();
        } catch (IOException e) {
            throw new RuntimeException();
        }
        return futbolistas;
    }

    private static Document construirFichero(List<Futbolista> futbolistas) {
        Document doc;
        DocumentBuilderFactory dBFactory = DocumentBuilderFactory.newInstance();
        try {
            dBFactory.setIgnoringComments(true);
            dBFactory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder builder = dBFactory.newDocumentBuilder();
            doc = builder.newDocument();
            doc.setXmlVersion("1.0");
            Element elementFutbolistas = doc.createElement("futbolistas");
            doc.appendChild(elementFutbolistas);
            for (Futbolista futbolista : futbolistas
            ) {
                Element elementFutbolista = doc.createElement("futbolista");
                elementFutbolistas.appendChild(elementFutbolista);
                //añadir el id al futbolista
                Element elementId = doc.createElement("num");
                elementId.appendChild(doc.createTextNode(String.valueOf(futbolista.getNum())));
                elementFutbolista.appendChild(elementId);
                //añadir el alias al futbolista
                Element elementAlias = doc.createElement("alias");
                elementAlias.appendChild(doc.createTextNode(futbolista.getAlias()));
                elementFutbolista.appendChild(elementAlias);
                //añadir el codigo de Equipo
                Element elementCodEq = doc.createElement("codEq");
                elementCodEq.appendChild(doc.createTextNode(futbolista.getCodEq()));
                elementFutbolista.appendChild(elementCodEq);
                //puesto
                Element elementPuesto = doc.createElement("puesto");
                String textPuesto = futbolista.obtenerPuestoString();
                elementPuesto.appendChild(doc.createTextNode(textPuesto));
                elementFutbolista.appendChild(elementPuesto);
                //altura
                Element elementAltura = doc.createElement("altura");
                elementAltura.appendChild(doc.createTextNode(String.valueOf(futbolista.getAltura())));
                elementFutbolista.appendChild(elementAltura);
            }
//TRANSFORMACIÓN
            Source source = new DOMSource(doc);
            Result result = new StreamResult(Files.newBufferedWriter(Paths.get("futbolistas.xml")));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);

            //COMPROBACIÓN
            //BufferedReader br = new BufferedReader(new FileReader("futbolistas.xml"));
            //String linea = br.readLine();
            //while (linea != null) {
            //  System.out.println(linea);
            //linea = br.readLine();
            //}
            //br.close();
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
        return doc;
    }
}

