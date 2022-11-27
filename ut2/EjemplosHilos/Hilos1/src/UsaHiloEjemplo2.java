public class UsaHiloEjemplo2 {
    public static void main(String[] args) {
        Thread.currentThread().setName("Principal");
        System.out.println(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().toString());

        HiloEjemplo2 h = null;

        for(int i = 0; i < 3; i++){
            h = new HiloEjemplo2();
            h.setName("HILO" + i);
            h.setPriority(i + 1);
            h.start();
            System.out.println(
                    "Información del " + h.getName() + ": " + h.toString()
            );
        }
    }
}
