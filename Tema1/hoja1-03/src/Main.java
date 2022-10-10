import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ejercicio1();
        //ejercicio2();
    }

    private static void ejercicio2() {
        /*Un fichero de texto ALUMNOS.TXT tiene en cada línea el nombre y apellidos de un
alumno y la edad. La parte del nombre y apellidos del alumno está separada de su edad
por un carácter "-".
Realiza un programa que escribe en pantalla el nombre y la edad de todos los alumnos
que tienen más de 20 años.
El texto que se escribe para cada alumno debe escribirse en la forma:
"Ana Alonso Alonso tiene 21 años*/
        String linea;
        try {
            BufferedReader lector = new BufferedReader(new FileReader("alumnos.txt"));
            while ((linea = lector.readLine()) != null) {
                String[] datos = linea.split("-");
                System.out.println(datos[0]+"ha sacado un " +datos[1]);
            }

            lector.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void ejercicio1() {
        /*Realiza un programa que actúa sobre un fichero PERSONAS.TXT que tiene en cada
línea el nombre y apellidos en mayúsculas de una persona. Estos datos deben estar en el
formato APELLIDOS, NOMBRE
El programa presenta un menú como el siguiente:
1 Añadir persona
2 Buscar persona
3 Buscar nombre
4 Apellidos comienzan por
5 Eliminar persona
6 Ordenar fichero
0 Salir*/
        Scanner teclado = new Scanner(System.in);
        System.out.println("Teclea una opción " +
                "1 Añadir persona\n" +
                "2 Buscar persona\n" +
                "3 Buscar nombre\n" +
                "4 Apellidos comienzan por\n" +
                "5 Eliminar persona\n" +
                "6 Ordenar fichero\n" +
                "0 Salir");
        int opcion = teclado.nextInt();
        String apellidos = "";
        String nombre;
        String nombreCompleto;
        String linea;
        boolean encontrado = false;
        switch (opcion) {

            case 1:
                teclado.nextLine();
                /*Se recogen por teclado los apellidos y el nombre de la persona y se añaden
en una nueva línea de texto al fichero.*/
                System.out.println("Introduce apellidos");
                apellidos = teclado.nextLine();
                System.out.println("Introduce nombre");
                nombre = teclado.nextLine();
                nombreCompleto = apellidos.toUpperCase() + " , " + nombre.toUpperCase();
                try {
                    BufferedWriter escritor
                            = new BufferedWriter(new FileWriter("personas.txt", true));
                    escritor.newLine();
                    escritor.write(nombreCompleto);
                    escritor.close();
                    System.out.println("Persona insertada correctamente");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
                /*Se recoge por teclado los apellidos y el nombre de una persona y se indica si
se encuentra o no en el fichero. */
            case 2:

                teclado.nextLine();
                System.out.println("Introduce apellidos");
                apellidos = teclado.nextLine();
                System.out.println("Introduce nombre");
                nombre = teclado.nextLine();
                nombreCompleto = apellidos.toUpperCase() + ", " + nombre.toUpperCase();
                System.out.println(nombreCompleto);
                try {
                    BufferedReader lector = new BufferedReader(new FileReader("personas.txt"));
                    while ((linea = lector.readLine()) != null) {
                        if (linea.equalsIgnoreCase(nombreCompleto)) {
                            System.out.println("Esa persona está fichada " + nombreCompleto);
                            encontrado = true;
                        }
                    }
                    if (!encontrado) {
                        System.out.println("No hemos encontrado a nadie con ese nombre");
                    }
                    lector.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 3:
                /*: Se recoge por teclado un nombre de persona y se escriben en pantalla el
nombre y apellidos de todas las personas del fichero que tienen ese nombre.

*/
                teclado.nextLine();
                System.out.println("Introduce nombre");

                nombre = teclado.nextLine();
                nombreCompleto = apellidos.toUpperCase() + " , " + nombre.toUpperCase();
                try {
                    BufferedReader lector = new BufferedReader(new FileReader("personas.txt"));
                    while ((linea = lector.readLine()) != null) {
                        if (linea.contains(nombre.toUpperCase())) {
                            System.out.println("Esa persona está fichada " + linea);
                            encontrado = true;
                        }
                    }
                    if (!encontrado) {
                        System.out.println("No hay nadie con ese nombre en nuestra base de datos");
                    }
                    lector.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                break;
            case 4:
                /* Se introduce los primeros caracteres de apellidos de persona y se escriben los
nombres y apellidos de todas las personas cuyos apellidos comienzan por esos primeros
caracteres.*/
                teclado.nextLine();

                System.out.println("Introduce tres primeras letras de apellido");
                String primerasLetras = teclado.nextLine();
                try {
                    BufferedReader lector = new BufferedReader(new FileReader("personas.txt"));
                    while ((linea = lector.readLine()) != null) {
                        if (linea.contains(primerasLetras.toUpperCase())) {
                            System.out.println("Esa persona está fichada " + linea);
                            encontrado = true;
                        }
                       /* //esto hay que hacerlo con Substring
                        List<String> lista=leerFichero(fichero);
                        lista=lista.stream().filter(x->x.substring(0,num).equalsIgnoreCase(primerasLetras)).collect(Collectors.toList());
                        lista.forEach(System.out::println);*/

                    }
                    if (!encontrado) {
                        System.out.println("No hay nadie con ese apellido nuestra base de datos");
                    }
                    lector.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 5:
                /*: Se recogen por teclado el nombre y apellidos de una persona y, si se
encuentra en el fichero, se elimina del fichero. Para hacer esto se necesita un fichero
auxiliar en el que se van guardando todos los nombres que no se tengan que eliminar.*/
                Path ficheroOriginal = Paths.get("personas.txt");
                Path copiaFichero = Paths.get("personasActualizado.txt");
                teclado.nextLine();
                System.out.println("Introduce apellidos");
                apellidos = teclado.nextLine();
                System.out.println("Introduce nombre");
                nombre = teclado.nextLine();
                nombreCompleto = apellidos.toUpperCase() + ", " + nombre.toUpperCase();
                if (Files.exists(ficheroOriginal))
                    try {
                        List<String> todasLineas = Files.readAllLines(ficheroOriginal);
                        BufferedWriter bw = Files.newBufferedWriter(copiaFichero);
                        for (String l : todasLineas
                        ) {
                            if (!l.equalsIgnoreCase(nombreCompleto)) {
                                bw.write(l);
                                bw.newLine();
                            }
                        }
                        System.out.println("Ahora se cierra el flujo");
                        bw.close();


                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                File f1 = new File("personas.txt");
                File f2 = new File("personasActualizado.txt");
                f1.delete();
                boolean correcto = f2.renameTo(f1);
                System.out.println("usuario eliminado");
               /* //solución de Luis
                BufferedReader fichero=null;
                BufferedWriter ficheroDestino;
                fichero=new BufferedReader((new FileReader(fichero)));
                fichAux=new BufferedWriter(new FileWriter(ficheroDestino));
                //se recogen nombre y apellidos
                linea=fichero.readLine();
                while(linea!=null){
                    if(linea.equals(nombreCompleto)){
                        encontrado=true;}
                    else{
                        fichAux.Write(linea);
                        fichAuz.newLine();
                    }
                }
                //primero hay que eliminar el fi9chero de origen y después renombrarlo, sino no funcnona
                fichOrigen.delete();
                fichDestino.renameTo(fichOrigen);*/
                break;
            case 6:
                /*Ordena alfabéticamente el fichero.*/
                Path fich = Paths.get("personas.txt");
                Path copiaFich = Paths.get("personasOrdenado.txt");
                if (Files.exists(fich))
                    try {
                        List<String> todasLineas = Files.readAllLines(fich);
                        todasLineas.sort(null);
                        for (String lin : todasLineas
                        ) {
                            BufferedWriter bw = Files.newBufferedWriter(copiaFich);
                            for (String l : todasLineas
                            ) {
                                bw.write(l);
                                bw.newLine();
                            }
                            bw.close();
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
/*Solución de luis: con el método leer fichero lo carga a una lista de strings y hace lista.sort(null), después lo va copiando al archivo ordenado*/
                break;
        }
    }

    private static List<String> leerFichero(BufferedReader fichero) throws IOException {
        List<String> lista = new ArrayList<String>();
        String linea = fichero.readLine();
        while (linea != null) {
            lista.add(linea);
            linea = fichero.readLine();
        }
        return lista;
    }
}