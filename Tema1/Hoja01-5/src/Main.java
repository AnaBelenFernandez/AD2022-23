import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduce una opción");
        System.out.println("1 Añadir futbolista\n" +
                "2 Listar futbolistas\n" +
                "3 Listar futbolistas de equipo\n" +
                "4 Buscar futbolista\n" +
                "5 Modificar equipo de futbolista\n" +
                "6 Modificar datos de futbolista\n" +
                "7 Eliminar futbolista\n" +
                "8 Crear FUTBOLISTAS.DAT\n" +
                "0 Salir");
        int opcion = teclado.nextInt();
        boolean encontrado;
        List<Futbolista> listaFutbolistas = new ArrayList<Futbolista>();
        Futbolista fut = null;
        File fichero = new File("futbolistas.dat");
        Futbolista futbolista = new Futbolista();
        futbolista.setAlias("Rubi");
        futbolista.setAltura(1.70f);
        futbolista.setNum(3);
        futbolista.setPuesto(2);
        futbolista.setCodEq("BMT");
        switch (opcion) {
            case 1:
                /*: Pide por teclado los datos de un futbolista y los añade al fichero. No hay que
comprobar si el identificador ya existe.*/
                escribirFutbolista(futbolista);
                break;
            case 2:
                leerfichero();
                break;
            case 3:
                /*listar futbolistas de equipo*/
                futbolistasEquipo();
                break;
            case 4:
                updateFutbolista();
                break;
            case 5:
                futbolistasEquipo();
                break;

        }
    }
private static void deleteFutbolista(){
        Scanner teclado=new Scanner(System.in);
        boolean encontrado=false;
        DataInputStream fichDat;
        DataOutputStream fichAux;
        try{
            fichDat=new DataInputStream(new FileInputStream("futbolistas.dat"));
            fixAux=new DataOutputStream(new FileOutputStream("futAux.dat"));
            System.out.println("Número id del futbolista a eliminar");
            int num=teclado.nextInt();
            teclado.nextLine();
            Futbolista f=leerSig(fichDat);
            while(f!=null&&!encontrado){
                if(f.getNum()==num){
                    encontrado=true;
                }
                else{
                    grabarFut(f, fichAux)
                }
                f=leerSig((fichDat));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
}

    private static void grabarFut(Futbolista f, DataOutputStream fichAux) {
    }

    private static void futbolistasEquipo() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduzca código de equipo");
        File fichero = new File("futbolistas.dat");
        String codigoBuscado = teclado.nextLine();
        DataInputStream lector = null;
        try {
            boolean tienefutbolistas = false;
            lector = new DataInputStream(new FileInputStream(fichero));
            Futbolista f = leerSig(lector);
            while (f != null) {
                tienefutbolistas = true;
                if (f.getCodEq().equalsIgnoreCase(codigoBuscado)) {
                    System.out.println(String.format(Locale.US, " %-10d |  %-10s |  %-3s |  %-5d | %-5.2f", f.getNum(), f.getAlias(),  f.obtenerPuestoString(),f.getAltura()));
                }
                f = leerSig(lector);
            }
            if (!tienefutbolistas) {
                System.out.println("El equipo no tiene cargados futbolistas");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            if (lector != null)
                lector.close();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private static void updateFutbolista() {
        Scanner teclado = new Scanner(System.in);
        boolean encontrado = false, hecho = false;
        DataInputStream fichDat;
        DataOutputStream fichAux;
        try {
            fichDat = new DataInputStream(new FileInputStream("futbolistas.dat"));
            fichAux = new DataOutputStream(new FileOutputStream("futAux.dat"));
            System.out.println("Número id del futbolista");
            int num = teclado.nextInt();
            teclado.nextLine();
            Futbolista f = leerSig(fichDat);
            while (f != null) {
                if (f.getNum() == num) {
                    encontrado = true;
                    System.out.println("Alias " + f.getAlias());
                    System.out.println("Equipo " + f.getCodEq());
                    System.out.println("Puesto: " + f.getPuesto());
                    System.out.println("Altura" + f.getAltura());
                    System.out.println("Nuevo alias:ENTER mantiene el actual");
                    String txt = teclado.nextLine();
                    if (txt.length() > 0) {
                        f.setAlias(txt);
                    }
                    System.out.println("Nuevo equipo. EMTER MANTIENE EL ACTUAL");
                    txt = teclado.nextLine();
                    if (txt.length() > 0) {
                        f.setCodEq(txt.toUpperCase());
                    }
                    System.out.println("nuevo puesto,nçumero");
                    txt = teclado.nextLine();
                    if (txt.length() > 0) {
                        f.setPuesto(Integer.parseInt(txt));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void leerfichero() {
        File fichero = new File("futbolistas.dat");
        DataInputStream lector = null;
        try {
            boolean tienefutbolistas = false;
            lector = new DataInputStream(new FileInputStream(fichero));
            Futbolista f = leerSig(lector);
            while (f != null) {
                tienefutbolistas = true;
                System.out.println(String.format(Locale.US, " %-10d |  %-15s |  %-3s |  %-5d | %-5.2f", f.getNum(), f.getAlias(), f.getCodEq(), f.getPuesto(), f.getAltura()));
                f = leerSig(lector);
            }
            if (!tienefutbolistas) {
                System.out.println("El equipo no tiene cargados futbolistas");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            if (lector != null)
                lector.close();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private static Futbolista leerSig(DataInputStream lector) {
        Futbolista f = null;
        int num;
        String alias;
        String codEq;
        String apellidos;
        int puesto;
        float altura;
        try {
            if (lector.available() > 0) {
                f = new Futbolista();
                num = lector.readInt();
                alias = lector.readUTF();
                codEq = lector.readUTF();
                puesto = lector.readInt();
                altura = lector.readFloat();
                f.setNum(num);
                f.setAlias(alias);
                f.setCodEq(codEq);
                f.setPuesto(puesto);
                f.setAltura(altura);
            }
        } catch (IOException ex) {

        }
        return f;
    }


    private static void escribirFutbolista(Futbolista futbolista) {
        try {
            DataOutputStream escritor = new DataOutputStream(new FileOutputStream("futbolistas.dat", true));
            escritor.writeInt(futbolista.getNum());
            escritor.writeUTF(futbolista.getAlias());
            escritor.writeUTF(futbolista.getCodEq());
            escritor.writeInt(futbolista.getPuesto());
            escritor.writeFloat(futbolista.getAltura());
            escritor.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}