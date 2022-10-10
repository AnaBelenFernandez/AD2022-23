import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class DepartamentosHandler extends DefaultHandler {
    private List<Departamento> departamentos;
    private StringBuilder valorElemento;

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        this.departamentos = new ArrayList();
        System.out.println("Se ha iniciado la lectura del documento");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        //Voy a evaluar todas las posible etiquetas de inicio
        switch (qName) {
            case "departamentos":

                //Obtengo el valor del atributo grupo a partir del parámetro
                System.out.print("Departamentos: " + attributes.getValue("departamento") + "\n");
                break;
            case "departamento":

                System.out.println("Datos de departamento " + (departamentos.size() + 1));
                //Creo un objeto departamento, donde almacenaré sus hijos
                departamentos.add(new Departamento());
                break;
            case "numero":
                this.valorElemento = new StringBuilder();
                break;
            case "nombre":
                this.valorElemento = new StringBuilder();
                break;
            case "localidad":
                this.valorElemento = new StringBuilder();
                break;
            case "empleados":
                this.valorElemento = new StringBuilder();
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        //Esto puede ser un texto de un valor de una marca
        //o incluso un espacio o un ENTER que haya entre dos marcas.
        //Solo se obtiene el texto. No puede saberse a que corresponde

        //Usamos StringBuffer para extraerlos como String del array chars
        if (this.valorElemento == null)
            this.valorElemento = new StringBuilder();
        else
            this.valorElemento.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        //Evaluamos las marcas de fin que necesitemos
        switch (qName) {
            case "numero":
                System.out.println("\tNumero: " + this.valorElemento);
                this.departamentos.get(this.departamentos.size() - 1).setNumero(Integer.valueOf(this.valorElemento.toString()));
            break;
            case "nombre":
                System.out.println("\tNombre: " + this.valorElemento);
                this.departamentos.get(this.departamentos.size() - 1).setNombre(this.valorElemento.toString());
            break;
            case "localidad":
                System.out.println("\tLocalidad: " + this.valorElemento);
                //aquí añadimos el atributo al objeto
                this.departamentos.get(this.departamentos.size() - 1).setLocalidad(this.valorElemento.toString());
            break;
            case "empleados":
                System.out.println("\templeados: " + this.valorElemento);
                this.departamentos.get(this.departamentos.size() - 1).setEmpleados(Integer.valueOf(this.valorElemento.toString()));
            break;
        }
    }

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        System.out.println("Finalizada la lectura del documento");
    }

}
