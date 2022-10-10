import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class DepartamentosHandler extends DefaultHandler {
    private List<Departamento> departamentos;
    private StringBuilder valorElemento;
    private Empleado empleado;
    boolean esEmpleado;


    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        this.departamentos = new ArrayList<Departamento>();
        System.out.println("Se ha iniciado la lectura del documento");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        switch (qName) {
            case "departamento":
                esEmpleado = false;
                System.out.println("Datos de Departamento" + (departamentos.size() + 1));
                departamentos.add(new Departamento());
                //aquí recojo los ATRIBUTOS
                System.out.print("Teléfono del Departamento: " + attributes.getValue("telefono") + "\n");
                System.out.print("Tipo de Departamento: " + attributes.getValue("tipo") + "\n");
                this.departamentos.get(departamentos.size() - 1).setTelefono(attributes.getValue("telefono"));
                this.departamentos.get(departamentos.size() - 1).setTipo((attributes.getValue("tipo")));
                break;
            case "empleado":
                esEmpleado = true;
                System.out.println("Datos de Empleado: " );
                empleado = new Empleado();
                //ATRIBUTO: lo cojo y hago un set
                System.out.print("Salario del empleado: " + attributes.getValue("salario") + "\n");
                empleado.setSalario(Integer.valueOf(attributes.getValue("salario")));
                break;
            //ETIQUETAS HIJAS
            case "codigo":
                this.valorElemento = new StringBuilder();
                break;
            case "nombre":
                this.valorElemento = new StringBuilder();
                break;
            case "puesto":
                this.valorElemento = new StringBuilder();
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        if (this.valorElemento == null)
            this.valorElemento = new StringBuilder();
        else
            this.valorElemento.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        switch (qName) {
            case "empleado":
                System.out.println("\t Empleado Nombre: " + this.valorElemento);
                //inserto el empleado en la lista
                this.departamentos.get(departamentos.size() - 1).insertarEmpleado(empleado);
                break;
            case "codigo":
                System.out.println("\tCodigo: " + this.valorElemento);
                //hago el set de código del departametno así
                this.departamentos.get(this.departamentos.size() - 1).setCodigo(this.valorElemento.toString());
                break;
            case "puesto":
                System.out.println("\tPuesto: " + this.valorElemento);
                //hago el set del puesto del empleado así
                empleado.setPuesto(this.valorElemento.toString());
                break;
            case "nombre":
                //como hay nombre en el departamento y en el empleado hay que diferenciarlos
                if (!esEmpleado) {
                    this.departamentos.get(this.departamentos.size() - 1).setNombre(this.valorElemento.toString());
                } else {
                    empleado.setNombre(this.valorElemento.toString());
                }
                break;
        }
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        System.out.println("Finalizada la lectura del documento");
    }

}
