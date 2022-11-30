import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

class Ejercicio4Hilo extends Thread{
    FileReader fic;
    public Ejercicio4Hilo(FileReader fic, String nombre) {
        this.fic = fic;
        setName(nombre);
    }

    public void run() {
        int c=0;
        long t_comienzo = System.currentTimeMillis();
        try {
            c=ContarPalabras(fic);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        long t_fin = System.currentTimeMillis();
        long tiempoTotal = t_fin - t_comienzo ;
        System.out.printf("Palabras de %s => %d %n "
                + "   El proceso ha tardado: %d milisegundos %n", getName(), c, tiempoTotal);
        try {
            fic.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } // cerrar fichero

    }

    int ContarPalabras(FileReader fic) throws IOException {
        int c = 0;
        BufferedReader lee = new BufferedReader(fic);
        String linea;

        while((linea = lee.readLine()) != null)
        {
				/*StringTokenizer st = new StringTokenizer (linea);
				while (st.hasMoreTokens())
				{
					String palabra = st.nextToken();
					c++;
				}*/

            String[] palabras=linea.split(" ");
            c+=palabras.length;
        }//while

        lee.close();
        return c;
    }//ContarPalabras


}//fin del hilo

public class CA4 {

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Faltan argumentos en main...");
            return;
        }

        for (int i = 0; i < args.length; i++) {
            File fichero = new File(args[i]); // declarar fichero
            if (fichero.exists()) {
                FileReader fic = new FileReader(fichero); // crear el flujo de entrada
                Ejercicio4Hilo hilo = new Ejercicio4Hilo(fic,args[i] );
                hilo.start();
            } else
                System.out.printf("El fichero [%s] no existe...%n", args[i]);
        }

    }//main


}

