import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CA3 {

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Faltan argumentos en main...");
            return;
        }

        // iniciamos el contador de tiempo
        long t_comienzo = System.currentTimeMillis();

        for (int i = 0; i < args.length; i++) {
            File fichero = new File(args[i]); // declarar fichero
            if (fichero.exists()) {
                FileReader fic = new FileReader(fichero); // crear el flujo de entrada

                //Lectura tiempo de lectura en cada fichero
                long t_comienzo_fichero = System.currentTimeMillis();

                int c=ContarCaracteres(fic);

                // Lectura tiempo de finalización de lectura en cada fichero
                long t_fin_fichero = System.currentTimeMillis();
                long tiempoTotal_fichero = t_fin_fichero - t_comienzo_fichero;
                System.out.printf(" \n El fichero %s ha tardado: %d milisegundos %n", args[i], tiempoTotal_fichero);

                fic.close(); // cerrar fichero
            } else
                System.out.printf("El fichero [%s] no existe...%n", args[i]);
        }
        long t_fin = System.currentTimeMillis();
        long tiempoTotal_Proceso = t_fin - t_comienzo ;
        System.out.printf(" \n El proceso ha tardado: %d milisegundos %n", tiempoTotal_Proceso);

    }//main

    private static int ContarCaracteres(FileReader fic) throws IOException {
        int i;
        int c = 0;
        while ((i = fic.read()) != -1) // se va leyendo un car�cter
            c++;
        return c;

    }
}
