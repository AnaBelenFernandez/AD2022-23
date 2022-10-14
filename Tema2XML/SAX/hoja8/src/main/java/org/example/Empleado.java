package org.example;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="empleado")
public class Empleado {
    @XmlAttribute(name = "salario")
    private String salario;
    @XmlElement(name = "puesto")
    private String puesto;
    @XmlElement(name = "nombre")
    private String nombre;

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "salario='" + salario + '\'' +
                ", puesto='" + puesto + '\'' +
                ", nombre='" + nombre + '\'' +"\n"
                ;
    }
}
