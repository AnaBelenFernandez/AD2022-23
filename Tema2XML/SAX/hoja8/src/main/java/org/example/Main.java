package org.example;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        File xmluniversidad=new File("universidad.xml");
        Universidad universidad=deserializar(xmluniversidad);
        List<Departamento> listaDepartamentos=universidad.getListaDepartamentos();
        for (Departamento d:listaDepartamentos
             ) {
            System.out.println(d.toString());

        }
        List<Departamento>listaFiltrada=universidad.getListaDepartamentos()
                .stream()
                .filter(dep->dep.getCodigo().equalsIgnoreCase("MAT1"))
                .collect(Collectors.toList());
        if(listaFiltrada.size()==0){
            System.out.println("No hay departamento con ese código");
        }
        else{
            Departamento dep=listaFiltrada.get(0);
            System.out.println(" código: "+dep.getCodigo()+
                    " teléfono : "+dep.getTelefono()+"  tipo: "+dep.getTipo());
            System.out.println("lista de empleados");
            dep.getEmpleados()
                    .stream()
                    .forEach(empleado -> System.out.println(empleado.getNombre()+ " puesto: "+empleado.getPuesto()+ " salario: "+empleado.getSalario()));
        }
    }

    private static Universidad deserializar(File archivo) {
        //Objeto de la clase binding en el que se deserializará el XML
        Universidad universidad = null;
        try
        {
            //Contexto para class Clientes
            JAXBContext contextUniversidad = JAXBContext.newInstance(Universidad.class);
            //Obtener un deserializador para contexto
            Unmarshaller deserializador = contextUniversidad.createUnmarshaller();
            //Asignar la clase gestora de eventos al deserializador
            //No es obligatorio usarlo, aunque si recomendable
            deserializador.setEventHandler(new GestorEventos());
            //Si quisiéramos usar un fichero esquema XSD que hubiéramos recogido por teclado,
            //después de obtener el deserializador, le tendríamos que haber asignado el esquema.
//            deserializador.setSchema(SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
//                .newSchema(new File("clientes.xsd")));
            //Deserializar en objeto clientes
            universidad = (Universidad) deserializador.unmarshal(archivo);
        }
        catch (JAXBException /*| SAXException */ex)
        {
            System.err.println(ex.getMessage());
        }
        return universidad;
    }
}