public class UsaPrimerHiloR {
    public static void main(String[] args) {
        PrimerHiloR r1 = new PrimerHiloR();
        new Thread(r1).start();

        PrimerHiloR r2 = new PrimerHiloR();
        Thread h = new Thread(r2);
        h.start();

        new Thread(new PrimerHiloR()).start();
    }
}
