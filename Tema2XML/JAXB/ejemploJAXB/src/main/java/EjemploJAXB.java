package org.example;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class EjemploJAXB {
    public static void main(String[] args) {
        Alumnos alumnos = new Alumnos();
        List <Alumnos> listaAlumnos=new ArrayList<Alumno>;
        Alumno alumno1=new Alumno("Ana", 28, "DAM2");
        Alumno alumno2=new Alumno("Pepe", 52, "DAM2");
        listaAlumnos.add(alumno1);
        alumnos.add(alumno2);
        alumnos.setListaAlumnos(listaAlumnos);
        Alumnos alumnos = new Alumnos();
        List <Alumnos> listaAlumnos=new ArrayList<Alumno>;
        alumnos.set
        Alumno alumno1=new Alumno("Ana", 28, "DAM2");
        Alumno alumno1=new Alumno("Pepe", 52, "DAM2");
        listaAlumnos.add
        if (alumnos != null)
        {
                   List<Alumno> listaAlumnos = alumnos.getListaAlumnos();
            for (Alumno a: listaAlumnos)
            {
                System.out.println("Nombre:" + a.getNombre());
                System.out.println("Edad:" + a.getEdad());

                System.out.println("Grupo" + a.getGrupo());


                System.out.println("-------------------------------");
            }
        }}

    private static Alumnos deserializar(File fXML)
    {

        Alumnos alumnos = null;
        try
        {
            JAXBContext contextAlumnos = JAXBContext.newInstance(Alumnos.class);
            Unmarshaller deserializador = contextAlumnos.createUnmarshaller();
            deserializador.setEventHandler(new GestorEventos());
            alumnos = (Alumnos) deserializador.unmarshal(fXML);
        }
        catch (JAXBException /*| SAXException */ex)
        {
            System.err.println(ex.getMessage());
        }
        return alumnos;
    }
}
