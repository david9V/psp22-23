package ch.makery.address;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyHilo extends Thread {
    private SolicitaSuspender suspender = new SolicitaSuspender();

    private int contador = 0;


    public void Suspende() {
        suspender.set(true);
    }

    public void Reanuda() {
        suspender.set(false);
    }

    public void run() {
        while (contador < 5){
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println(contador);
            try {
                sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            try {
                suspender.esperandoParaReanudar();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            contador++;
            try {
                String sus = reader.readLine();
                if (sus.compareTo("*") == 0){
                    interrupt();
                }
                if (sus.compareTo("S") == 0){
                    Suspende();
                    sus = reader.readLine();
                    if (sus.compareTo("R") == 0){
                        Reanuda();
                    }
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public int getContador(){
        return this.contador;
    }

}

