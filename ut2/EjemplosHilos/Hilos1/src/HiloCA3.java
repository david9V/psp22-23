public class HiloCA3 implements Runnable{


    private String fichero;

    HiloCA3(String fichero){
        this.fichero = fichero;
    }

    @Override
    public void run() {
        long t_comienzo, t_fin;
        t_comienzo = System.currentTimeMillis();
        leer (fichero);
        t_fin = System.currentTimeMillis();

        long tiempoTotal = t_fin - t_comienzo;
        System.out.println("");
        System.out.println("El proceso ha tardado " + tiempoTotal + "milisegundos");
    }
}
