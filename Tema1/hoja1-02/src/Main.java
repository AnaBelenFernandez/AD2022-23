import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //leerfichero();
        //escribirfichero();
        //ejercicio1byte();
        //ejercicio1caracter();
        //borrarFichero();
        //moverFichero();
        //ejercicio1byteCorreccion();
        //ejercicio2();
        //ejercicio3();
        ejercicio4();
    }

    private static void ejercicio4() {
        /*Realiza un programa que cuenta cuantas veces aparece un carácter introducido por
teclado en el fichero TEXTO.TXT y escribe en que posiciones aparece ese carácter. Si
el carácter es una letra, de deben controlar las apariciones de esa letra tanto en
mayúscula como en minúscula.*/
        Scanner teclado=new Scanner(System.in);
        System.out.println("Introduce letra a buscar");
        String letra=teclado.nextLine();
        char caracter=letra.charAt(0);
        File fichero= new File("loboCopiaCaracter.txt");
        FileReader lector;
        try{
            int apariciones=0;

            lector = new FileReader(fichero);
            int i;
            int contador=0;
            while ((i = lector.read()) != -1) {
                contador++;
                char letraLeida=(char)i;
                if(letraLeida==caracter){

                    apariciones++;
                    System.out.println("letra encontrada en la posición "+ contador );
                }            }
            lector.close();
            System.out.println("la letra "+caracter+" aparece "+apariciones+ " veces.");


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static void ejercicio3() {
        /*Realiza un programa que pide la ruta y nombre de un fichero por teclado y un texto que
queremos añadir a ese fichero (el fichero debe existir). Después de añadir el contenido
al fichero, se deberá mostrar en pantalla el texto completo del fichero.
El contenido del fichero se debe leer y modificar carácter a carácter.*/
        String linea;
        String texto="";
        Scanner teclado = new Scanner(System.in);
        try
        {
            BufferedWriter escritor
                    = new BufferedWriter(new FileWriter("loboCopiaCaracter.txt",true));

            System.out.println("Introduce contenido del fichero de texto (línea vacía para fin):");
            while (!(linea = teclado.nextLine()).isEmpty())
            {
                escritor.write(linea);
                escritor.newLine(); //graba salto de línea
                texto=texto+"/n"+linea;
            }
            //no lo he hecho caracter a caracter--> tendría que ser así
            /*  int i;
        while ((i = lector.read()) != -1) {
            System.out.print((char) i);
            escritor.write((char)i);
        }
        Luis lo ha hecho así para ir leyendo caracter a caracter del texto completo
        */
            for (int i=0;i<texto.length();i++){
                escritor.write(texto.charAt(i));
            }
            escritor.close();
        }
        catch (FileNotFoundException fn)
        {
            System.err.println("No se encuentra el fichero");
        }
        catch (IOException io)
        {
            System.err.println("Error de E/S");
        }
    }




    private static void ejercicio2() {
        /*Realiza programa que lee un fichero de texto, cuya ruta y nombre se da por teclado, y
muestra su contenido por pantalla sin los espacios y tabulaciones que tuviera. Por
ejemplo, si un fichero tiene el siguiente texto “Esto es una prueba”, deberá mostrar
“Estoesunaprueba”.
El contenido del fichero se debe leer carácter a carácter.*/
        File fichero = new File("lobo.txt");
        FileReader lector;
        try{
            lector = new FileReader(fichero);

            int i;
            while ((i = lector.read()) != -1) {
                if(i!=32)
                System.out.print((char) i);
            }
            lector.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void ejercicio1byteCorreccion() {
        File f=new File("lobo.txt");
        File f2=new File("loboCopiaLuis.txt");
        FileInputStream fInput=null;
        FileOutputStream fOut=null;
        try{
            fInput=new FileInputStream(f);
            fOut=new FileOutputStream(f2);
            int leido;
            while((leido=fInput.read())!=-1){
                System.out.println((char)(leido));
                fOut.write(leido);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void moverFichero() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduce ruta y nombre del fichero a mover");
        String ruta = teclado.nextLine();
        File fichero = new File(ruta);
        if (fichero.exists()) {
            System.out.println("Escibe la ruta del directorio al que se moverá el fichero");
            String nuevoDic = teclado.nextLine();
            File dirDestino = new File(nuevoDic);
            if (dirDestino.exists()) {
                if (dirDestino.isDirectory()) {
                    File newfile = new File(dirDestino, fichero.getName());
                    if (newfile.renameTo(newfile)) {
                        System.out.println("Se ha movido el fichero");
                    }
                }
            }
        }
    }

    private static void borrarFichero() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduce ruta y nombre del fichero");
        String ruta = teclado.nextLine();
        File fichero = new File(ruta);
        if (fichero.exists()) {
            System.out.println("¿Seguro que quieres eliminarlo?Escribe S para confirmar o N para cancelar");
            String respuesta = teclado.nextLine();
            if (respuesta.toUpperCase().equalsIgnoreCase("S")) {
                //if(fichero.delete()) copiar a partir de aquí lo de Andrea
            }
        }
    }

    private static void ejercicio1caracter() {
        /*Realiza un programa que va leyendo el contenido del fichero entrada.txt, lo escribe en
        pantalla y lo guarda en otro fichero denominado salida.txt. El programa se debe
        desarrollar leyendo carácter a carácter en el fichero de entrada:
        b) Usando un flujo de carácter*/
        File fichero = new File("lobo.txt");
        File ficheroCopia = new File("loboCopiaCaracter.txt");
        FileReader lector;
        FileWriter escritor;
        try{
        lector = new FileReader(fichero);
        escritor=new FileWriter(ficheroCopia);
        int i;
        while ((i = lector.read()) != -1) {
            System.out.print((char) i);
            escritor.write((char)i);
        }
        lector.close();
        escritor.close();
    } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void ejercicio1byte() {
     /*Realiza un programa que va leyendo el contenido del fichero entrada.txt, lo escribe en
pantalla y lo guarda en otro fichero denominado salida.txt. El programa se debe
desarrollar leyendo carácter a carácter en el fichero de entrada:
a) Usando un flujo de byte
*/
        FileInputStream f = null;
        FileOutputStream fo = null;
        try {
            f = new FileInputStream("lobo.txt");
            fo = new FileOutputStream("loboCopiaByte.txt", true);
            int c;
            while (f.available() > 0) {
                c = f.read();
                System.out.print((char) c);
                fo.write((char) c);
            }
            f.close();
            fo.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private static void escribirfichero() {
        Scanner teclado = new Scanner(System.in);
        try {
            BufferedWriter ficheroSalida = new BufferedWriter(new FileWriter("salida.txt"));
            System.out.println("mueva línea (vacía para terminar)");
            String linea = teclado.nextLine();
            while (!linea.isEmpty()) {
                //grabar la línea
                ficheroSalida.write(linea);//el salto de línea también se puede hacer aquí con un /n
                ficheroSalida.newLine();
                ;
                System.out.println("nueva linea(vacía para terminar)");
                linea = teclado.nextLine();
            }
            ficheroSalida.close();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static void leerfichero() {
        try {
            BufferedReader fich = new BufferedReader(new FileReader("lobo.txt"));
            String linea;
            while ((linea = fich.readLine()) != null) {
                System.out.println(linea);
            }
            fich.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
