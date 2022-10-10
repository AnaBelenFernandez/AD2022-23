import java.io.*;

public class Main {
    public static void main(String[] args) {
        escribirDatos();
        leerDatos();
    }

    private static void leerDatos() {
        File fichero = new File("datos.dat");
        FileInputStream fis = null;
        DataInputStream lector = null;
        String nombre;
        int edad;
        try {
            fis=new FileInputStream(fichero);
            lector=new DataInputStream(fis);
            nombre = lector.readUTF();
            while (true) {
                edad = lector.readInt();
                if (edad >= 20 && edad <= 30)
                    System.out.printf("Nombre: %-7s | Edad: %d |%n", nombre, edad);
                nombre = lector.readUTF();
            }
        } catch (EOFException e) {
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                lector.close();
                fis.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }


    private static void escribirDatos() {
        String[] nombres = new String[]{"Ana", "Lorenzo", "Maribel"};
        int[] edades = new int[]{34, 45, 25};
        File fichero = new File("datos.dat");
        FileOutputStream fileout = null;
        try {
            fileout = new FileOutputStream(fichero);
            DataOutputStream escritor = new DataOutputStream(fileout);
            for (int i = 0; i < nombres.length; i++) {
                escritor.writeUTF(nombres[i]);
                escritor.writeInt(edades[i]);
            }
            escritor.close();
            fileout.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}