import java.io.File;
import java.util.Scanner;

public class Ficheros {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Elige una de las opciones:\n" +
                "0. Salir\n" +
                "1. Obtener ruta absoluta directorio actual de trabajo\n" +
                "2. Obtener ficheros en directorio actual\n" +
                "3. Crear subdirectorio en directorio actual\n" +
                "4. Crear fichero\n" +
                "5. Copiar fichero\n" +
                "6. Eliminar fichero");
        int opc = teclado.nextInt();
        File directorio =  new File(".");
        String[] archivos = directorio.list();
        do {
            switch (opc) {
                case 1:
                    System.out.println("Ruta del directorio actual:" + directorio.getAbsolutePath());
                    break;
                case 2:
                    System.out.println("Ficheros de directorio actual:" + directorio.getAbsolutePath());
                    for (int i = 0; i < archivos.length; i++) {
                        System.out.println(archivos[i]);
                    }
                    break;
                case 3:
                    String nombre;
                    System.out.println("Introduce nombre de fichero");
                    nombre=teclado.nextLine();
                    String directorioBuscado = directorio.getAbsolutePath()+"\nombre";
                    for(int i = 0; i<archivos.length; i++){
                        if(archivos[i].equalsIgnoreCase(directorioBuscado)){
                            System.out.println("El directorio ya existe");
                        }else{
                            File subdirectorio = new File(nombre);

                        }
                    }
                    break;

            }
        }while(opc != 0);
    }
}
