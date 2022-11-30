package ch.makery.address;

public class MyHilo extends Thread{
    private SolicitaSuspender suspender = new SolicitaSuspender();

    private int contador = 0;

    public void Suspende(){
        suspender.set(true);
    }

    public void Reanuda(){
        suspender.set(false);
    }

    public void run(){
        try{
            while(true){
                suspender.esperandoParaReanudar();
            }
        } catch(InterruptedException exception){}
    }
}
