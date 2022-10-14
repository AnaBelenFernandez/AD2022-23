package org.example;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="alumno")
public class Alumno {
    @XmlElement(name = "nombre", required = true)
    private String nombre;
    @XmlElement(name = "edad", required = true)
    private int edad;

    @XmlAttribute(name = "grupo", required = true)
    private String grupo;

    public Alumno() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public Alumno(String nombre, int edad, String grupo) {
        this.nombre = nombre;
        this.edad = edad;
        this.grupo = grupo;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", grupo='" + grupo + '\'' +
                '}';
    }
}
