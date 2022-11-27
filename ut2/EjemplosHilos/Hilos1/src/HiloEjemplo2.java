public class HiloEjemplo2 extends Thread{

    public void run(){
        System.out.println(
                "Dentro del Hilo: " + Thread.currentThread().getName() +
                        "\nPrioridad: " + Thread.currentThread().getPriority()
        );
    }
}
