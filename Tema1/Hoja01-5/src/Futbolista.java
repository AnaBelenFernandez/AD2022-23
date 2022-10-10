import java.io.Serializable;
import java.util.Scanner;

public class Futbolista implements Serializable {
    private int num;
    private String nombre;
    private String apellidos;
    private String alias;

    private int puesto;
    private float altura;
    private String fechaNac;
    private String codEq;

    public Futbolista() {
    }

    public String getCodEq() {
        return codEq;
    }

    public void setCodEq() {

    }


    public Futbolista(int num, String nombre, String apellidos, String alias, int puesto, float altura, String fechaNac, String codEq) {
        this.num = num;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.alias = alias;
        if(puesto>=1 && puesto <=4)
            this.puesto = puesto;
        else
            puesto=0;
        this.altura = altura;
        this.fechaNac = fechaNac;
        this.codEq=codEq;
    }

    public void setCodEq(String codEq) {
        this.codEq = codEq;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
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

    public int getPuesto() {
        return puesto;
    }

    public void setPuesto(int puesto) {
        this.puesto = puesto;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String obtenerPuestoString() {
        String puestoTexto = switch (puesto) {
            case 1 -> "portero";
            case 2 -> "defensa";
            case 3 -> "centrocampista";
            case 4 -> "delantero";
            default -> "sin_especificar";
        };
        return puestoTexto;
    }
    public static String obtenerPuestoString(int p) {
        return switch (p) {
            case 1 -> "portero";
            case 2 -> "defensa";
            case 3 -> "centrocampista";
            case 4 -> "delantero";
            default -> "sin_especificar";
        };

    }
    @Override
    public String toString() {
        return "Futbolista{" +
                "num=" + num +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", alias='" + alias + '\'' +
                ", puesto=" + puesto +
                ", altura=" + altura +
                ", fechaNac='" + fechaNac + '\'' +
                '}';
    }
    public String toCSV() {
        return num +"," + nombre + "," + apellidos + "," + alias +"," + puesto
                +"," + altura +"," + fechaNac ;
    }

    public void cargarPorTeclado() {
        Scanner tec=new Scanner(System.in);
        System.out.println("Num del futbolista");
        num=tec.nextInt();
        tec.nextLine();
        System.out.println("Nombre del futbolista");
        nombre=tec.nextLine();
        System.out.println("Apellidos del futbolista");
        apellidos=tec.nextLine();
        System.out.println("Introduce alias del futbolista");
        alias=tec.nextLine();
        System.out.println("Puesto del futbolista: "
                + "1-portero, 2-defensa, 3-centrocampista, 4-delantero");
        int p=tec.nextInt();
        if(p<1 || p>4)
            p=0;
        puesto=p;
        System.out.println("Altura del futbolista");
        altura=tec.nextFloat();
        tec.nextLine();
        System.out.println("Fecha de nacimiento del futbolista (formato aaaa-MM-dd)");
        fechaNac=tec.nextLine();
        System.out.println("Código del equipos");
        codEq=tec.nextLine();
    }
}
