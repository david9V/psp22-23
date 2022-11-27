public class HiloCA1 extends Thread{
    private int x;
    HiloCA1(int x){
        this.x = x;
    }

    @Override
    public void run() {
        System.out.println("HOLA MUNDO: " +  x);
    }
}
