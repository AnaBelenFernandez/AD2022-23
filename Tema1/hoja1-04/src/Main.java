import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.time.LocalDate.now;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduce una opción");
        System.out.println("1 Listado de futbolistas\n" +
                "2 Listado de futbolistas de equipo\n" +
                "3 Añadir futbolista\n" +
                "4 Modificar puesto de futbolista\n" +
                "5 Eliminar futbolista\n" +
                "0 Salir");
        int opcion = teclado.nextInt();
        boolean encontrado;
        BufferedReader lector = null;
        BufferedWriter escritor = null;
        List<Futbolista> listaFutbolistas = new ArrayList<Futbolista>();
        Futbolista fut = null;
        switch (opcion) {
            case 1:
                listaFutbolistas = leerFichero();
                break;
            case 2:

                teclado.nextLine();
                System.out.println("Introduzca código de equipo");
                String cod = teclado.nextLine();
                boolean existe = false;
                BufferedReader fich = null;
                try {
                    fich = new BufferedReader(new FileReader("futbolistas.csv"));
                    String linea = fich.readLine();
                    while (linea != null) {
                        String[] datos = linea.split(",");
                        if (datos[7].equalsIgnoreCase(cod.toUpperCase())) {
                            existe = true;
                            String texto = String.format("%-5s%-15s %-25s%-20s%-15s%-8s%-12s",
                                    datos[0], datos[1], datos[2], datos[3], datos[4], datos[5], datos[6]);
                            System.out.println(texto);
                        }
                        linea = fich.readLine();
                    }
                    if (!existe) {
                        System.out.println("No existe el futbolista en el fichero0");
                    }
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                break;

            case 3:
                fut = new Futbolista(299, "Rubén", "Pérez Álvarez", "Rubi", "centrocampista", 1.77, "2007-09-07", "BAR");
                String texto = fut.Id + "," + fut.nombre + "," + fut.apellidos + "," + fut.alias + "," + fut.altura.toString() + "," + fut.FechaNac + "," + fut.codEquipo;
                try {
                     escritor
                            = new BufferedWriter(new FileWriter("futbolistas.csv", true));
                    escritor.write(texto);
                    escritor.close();
                    System.out.println("Futbolista insertado correctamente");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 4:
                System.out.println("introduzca id de futbolista");
                int id = teclado.nextInt();
                encontrado = false;

                try {
                    lector = new BufferedReader(new FileReader("futbolistas.csv"));
                    String linea = lector.readLine();
                    while (linea != null) {
                        String[] datos = linea.split(",");
                        if (datos[0].equals(id)) {
                            encontrado = true;
                            texto = String.format("%-5s%-15s %-25s%-20s%-15s%-8s%-12s",
                                    datos[0], datos[1], datos[2], datos[3], datos[4], datos[5], datos[6]);
                            System.out.println(texto);
                            System.out.println("Nuevo puesto");
                            String nuevopuesto = teclado.nextLine();
                        }
                        linea = lector.readLine();
                    }

                    if (!encontrado) {
                        System.out.println("Futbolista no encontrado");
                    }

                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 5:
                teclado.nextLine();
                System.out.println("introduzca id de futbolista");
                String ide = teclado.nextLine();
                encontrado = false;
                try {
                    lector = new BufferedReader(new FileReader("futbolistas.csv"));
                    escritor = new BufferedWriter(new FileWriter("futaux.csv"));
                    String linea = lector.readLine();
                    while (linea != null) {
                        String[] datos = linea.split(",");
                        if (datos[0].equals(ide)) {//no busca bien
                            encontrado = true;
                        } else {
                            escritor.write(linea);
                            escritor.newLine();
                        }
                        linea = lector.readLine();
                    }
                    lector.close();
                    escritor.close();
                    if (!encontrado) {
                        System.out.println("Futbolista no encontrado");
                    }
Path copia=Paths.get("futaux.csv");
                    Path viejo=Paths.get("futbolistas.csv");
                    Files.copy(viejo, copia, StandardCopyOption.REPLACE_EXISTING);
                    Files.delete(viejo);
                    File archivo=new File("futbolistas.csv");
                    File archivoExistente=new File("futaux.csv");
                    archivoExistente.renameTo(archivo);
                    //corrección de luis
                    File f=new File("futbolistas.csv");
                    File faux=new File("futaux.csv");
                    f.delete();
                    faux.renameTo(f);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
        }

    }

    private static List<Futbolista> leerFichero() {
        String linea;
        List<Futbolista> futbolistas = new ArrayList<Futbolista>();
        try {
            BufferedReader lector = new BufferedReader(new FileReader("futbolistas.csv"));
            while ((linea = lector.readLine()) != null) {
                String[] campos = linea.split(",");
                Futbolista futbolista = new Futbolista(Integer.valueOf(campos[0]), campos[1], campos[2], campos[3], campos[4], Double.valueOf(campos[5]), (campos[6]), campos[7]);
                futbolistas.add(futbolista);
                futbolistas.forEach(System.out::println);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return futbolistas;
    }


}