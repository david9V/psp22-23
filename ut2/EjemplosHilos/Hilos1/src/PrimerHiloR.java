public class PrimerHiloR implements Runnable{
    @Override
    public void run() {
        System.out.println("Hola desde el hilo! " + Thread.currentThread().getId());
    }
}
