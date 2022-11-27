public class UsaHiloEjemplo1_V2 {

    public static void main(String[] args) {
        HiloEjemplo1_V2 h1 = new HiloEjemplo1_V2("david");
        HiloEjemplo1_V2 h2 = new HiloEjemplo1_V2("pepe");
        HiloEjemplo1_V2 h3 = new HiloEjemplo1_V2("mel");

        h1.start();
        h2.start();
        h3.start();

        System.out.println("3 Hilos iniciados");
    }
}
