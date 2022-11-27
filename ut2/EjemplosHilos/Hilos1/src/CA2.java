public class CA2 {
    public static void main(String[] args) {
        HiloCA2 c = null;
        for(int i = 0; i < 5; i++){
            c = new HiloCA2(String.valueOf(i));
            new Thread(c).start();
        }
    }
}
