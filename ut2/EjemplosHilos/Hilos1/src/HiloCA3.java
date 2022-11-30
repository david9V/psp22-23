import java.io.FileReader;
import java.io.IOException;
    class HiloCA3 extends Thread{

        FileReader fic;
        public HiloCA3(FileReader fic, String nombre) {
            this.fic = fic;
            setName(nombre);
        }

        public void run() {
            int c=0;
            long t_comienzo = System.currentTimeMillis();
            try {
                c=ContarCaracteres(fic);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            long t_fin = System.currentTimeMillis();
            long tiempoTotal = t_fin - t_comienzo ;
            System.out.printf("Caracteres de %s => %d %n "
                    + "   El hilo ha tardado: %d milisegundos %n", getName(), c, tiempoTotal);
            try {
                fic.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } // cerrar fichero

        }
        int ContarCaracteres(FileReader fic) throws IOException {
            int i;
            int c = 0;
            while ((i = fic.read()) != -1) // se va leyendo un car�cter
                c++;
            return c;
        }


    }//fin del hilo

