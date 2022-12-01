package ch.makery.address;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;

public class MyHilo_2 extends Thread {
    private SolicitaSuspender suspender = new SolicitaSuspender();

    private IntegerProperty contador = new SimpleIntegerProperty(0);

    public boolean fin = false;


    public void Suspende() {
        suspender.set(true);
    }

    public void Reanuda() {
        suspender.set(false);
    }

    public void run() {
        while (!fin){
            System.out.println(contador);
            try {
                sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            try {
                suspender.esperandoParaReanudar();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            this.contador.setValue(this.contador.getValue() + 1);

        }
    }

    public int getContador() {
        return contador.get();
    }

    public IntegerProperty contadorProperty() {
        return contador;
    }
}

