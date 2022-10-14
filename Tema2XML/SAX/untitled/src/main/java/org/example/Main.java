package org.example;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Alumnos alumnos = new Alumnos();
        List<Alumno> listaAlumnos = new ArrayList<Alumno>();
        Alumno alumno1 = new Alumno("Ana", 28, "DAM2");
        Alumno alumno2 = new Alumno("Pepe", 52, "DAM2");
        listaAlumnos.add(alumno1);
        listaAlumnos.add(alumno2);
        alumnos.setListaAlumnos(listaAlumnos);
        if (alumnos != null) {
            listaAlumnos = alumnos.getListaAlumnos();
            for (Alumno a : listaAlumnos) {
                System.out.println("Nombre:" + a.getNombre());
                System.out.println("Edad:" + a.getEdad());
                System.out.println("Grupo" + a.getGrupo());
                System.out.println("-------------------------------");
            }
            serializar(alumnos);
        }
    }

    private static void serializar(Alumnos alumnos) {
        try {
            //Contexto para class Alumnos
            JAXBContext contextoAlumnos = JAXBContext.newInstance(Alumnos.class);
            //Obtener el serializador
            Marshaller serializador = contextoAlumnos.createMarshaller();
            //Que haya indent en el XML
            serializador.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //Serializar alumnos en el fichero
            serializador.marshal(alumnos, new File("alumnosSerializado.xml"));
            System.out.println("Creado el fichero XML");
        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
    }
}
