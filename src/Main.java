import java.io.*;
import java.util.Scanner;

public class Main {
    //Deberia crear un objeto que sea el fichero y que este vaya teniendo sus atributos dentro?
    //Seria mas comodo??? TIENES QUE INTENTARLO TIO!!

    /** Funcion para RENOMBRAR/MOVER un archivo */

    public static boolean modArchivo(File f, int i, File padre) {
        String ext1 = "avi";
        String ext2 = "mp4";
        String ext3 = "mkv";
        boolean res = false;

        String fich = f.getName();
        String fichExt = fich.substring(fich.length()-3, fich.length()); //La extension la coge bien. 'avi'

        if (fichExt.equals(ext1) || fichExt.equals(ext2) ||fichExt.equals(ext3)) {// LOS STRING SE COMPARAN CON EQUALS!!!
            // Renombremos!!
            i++;
            String fichero2 = f.getParentFile().getName() + i + "." + fichExt;
            File f2 = new File(padre, fichero2); // Creamos el objeto para el nuevo nombre
            res = f.renameTo(f2); // renombramos el fichero y lo dejamos en la directorio superior
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner( System.in );
        boolean salida = false;
        File dirPadre;
        int modoMenu;
        String directorio = "";
        String rutaPadre = "";

        do {
            System.out.println("Que modo quieres?: ");
            System.out.println(" 1.- Dejar los archivos en la carpeta superior (Modo 1)\n");
            System.out.println(" 2.- Dejar los archivos en la misma carpeta (Modo 2)\n");
            System.out.println(" 3.- Dejar los archivos en otra capeta (Modo 3)\n");
            System.out.println(" 4.- Salir (Modo 4\n");
            modoMenu = sc.nextInt();

            switch (modoMenu) {
                case 1: System.out.println("Especifica la ruta de la carpeta que contiene los ficheros:\n");
                    directorio = sc.next(); salida = true; break;
                case 2: System.out.println("Especifica la ruta de la carpeta que contiene los ficheros:\n");
                    directorio = sc.next(); salida = true; break;
                case 3: System.out.println("Especifica la ruta de la carpeta que contiene los ficheros:\n");
                    directorio = sc.next();
                    System.out.println("Especifica donde los quieres dejar:\n");
                    rutaPadre = sc.next();
                    salida = true; break;
                case 4: salida = true; break;
                default: salida = true;
            }
        } while(!salida);

        File dir = new File (directorio);
        File[] archivos = dir.listFiles();

        if (archivos.length == 0) {
            System.out.println ("Aqui no hay nada... o esta lleno de unicornios.");
        } else {
            for (int i = 0; i < archivos.length; i++) {
                switch (modoMenu) {
                    case 1:
                        dirPadre = archivos[i].getParentFile().getParentFile();
                        break;
                    case 2:
                        dirPadre = archivos[i].getParentFile();
                        break;
                    case 3: dirPadre = new File(rutaPadre); break;
                    default: dirPadre = archivos[i].getParentFile().getParentFile();

                }
                modArchivo(archivos[i], i, dirPadre);
            }
            System.out.println("Todos los archivos movidos y renombrados.");
        }
    }
}
