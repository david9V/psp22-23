public class HiloTic extends Thread{
    HiloTic(){

    }

    @Override
    public void run() {
        while(true){
            System.out.println("TIC");
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
