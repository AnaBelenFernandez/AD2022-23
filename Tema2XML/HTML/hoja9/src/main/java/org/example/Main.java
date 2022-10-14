package org.example;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Main {
    public static void main(String[] args) {
        // Declarar como Source el archivo fuente XSL
       try{ Source estilos=new StreamSource("estilos.xsl");
// Declarar como Source el archivo fuente de datos XML
        Source datos=new StreamSource("departamentos.xml");
        //Archivo HTML donde se guardará el resultado de la conversión
        FileOutputStream out=new FileOutputStream("salida.html");
        // Construir un objeto result que grabará contenido en out
        Result result=new StreamResult(out);
//Construimos un conversor que aplique los estilos
        Transformer transformer = TransformerFactory.newInstance().newTransformer(estilos);
//Hacemos la conversion de alumnos.xml y obtenemos el
// resultado en result que se aplicará a out (a salida.html)
        transformer.transform(datos, result);} catch (TransformerConfigurationException e) {
           throw new RuntimeException(e);
       } catch (FileNotFoundException e) {
           throw new RuntimeException(e);
       } catch (TransformerException e) {
           throw new RuntimeException(e);
       }


    }
}