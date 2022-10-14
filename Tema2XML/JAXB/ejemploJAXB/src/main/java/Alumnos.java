import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Alumnos {
    public List<Alumno> listaAlumnos;

    public List<Alumno> getListaAlumnos() {
        return listaAlumnos;
    }

    public void setListaAlumnos(List<Alumno> listaAlumnos) {
        this.listaAlumnos = listaAlumnos;
    }
}
