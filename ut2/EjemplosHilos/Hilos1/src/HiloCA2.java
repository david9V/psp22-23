import static java.lang.Thread.sleep;

public class HiloCA2 implements Runnable {
    private String nombre;

    HiloCA2(String nombre){
        this.nombre = nombre;
    }

    @Override
    public void run() {
        try {
            sleep(Thread.currentThread().getId());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("He esperado..." + Thread.currentThread().getId());
        System.out.println("Hola mundo: " + nombre);
    }
}
