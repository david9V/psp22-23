public class CA1 {
    public static void main(String[] args) {
        HiloCA1 c = null;
        for(int i = 0; i < 5; i++){
            c = new HiloCA1(i);
            c.start();
        }
    }
}
