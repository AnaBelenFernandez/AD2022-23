import java.time.LocalDate;

public class Futbolista {
    public int Id;
    public String nombre;
    public String apellidos;
    public String alias;
    public String puesto;
    public Double altura;
    public String FechaNac;
    public String codEquipo;

    public Futbolista() {

    }

    public Futbolista(int id, String nombre, String apellidos, String alias, String puesto, Double altura, String fechaNac, String codEquipo) {
        Id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.alias = alias;
        this.puesto = puesto;
        this.altura = altura;
        this.FechaNac = fechaNac;
        this.codEquipo = codEquipo;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public String getFechaNac() {
        return FechaNac;
    }

    public void setFechaNac(String fechaNac) {
        FechaNac = fechaNac;
    }

    public String getCodEquipo() {
        return codEquipo;
    }

    public void setCodEquipo(String codEquipo) {
        this.codEquipo = codEquipo;
    }

    @Override
    public String toString() {

        return "Futbolista{" +
                "Id=" + Id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", alias='" + alias + '\'' +
                ", puesto=" + puesto +
                ", altura=" + altura +
                ", FechaNac=" + FechaNac +
                ", codEquipo='" + codEquipo + '\'' +
                '}';
    }
}
