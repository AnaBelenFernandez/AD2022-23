import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Alumno {
    private String nombre;
    private int edad;
    private String grupo;

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
