package org.example;

import org.example.Empleado;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="departamento")
public class Departamento {
    @XmlAttribute(name = "telefono")
    private String telefono;
    @XmlAttribute(name = "tipo")
    private String tipo;
    @XmlElement(name = "codigo")
    private String codigo;
    @XmlElement(name = "nombre")
    private String nombre;
    @XmlElement(name = "empleado")
    private List<Empleado> empleados=new ArrayList<Empleado>();

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        String Textoempleados="";
        for (Empleado e:empleados) {
            Textoempleados+=e.toString();

        }
        return "Departamento{" +
                "telefono='" + telefono + '\'' +
                ", tipo='" + tipo + '\'' +
                ", codigo='" + codigo + "\'" +
                ", nombre='" + nombre + '\'' +
                ", empleados=" + empleados +
                '}';
    }
}
