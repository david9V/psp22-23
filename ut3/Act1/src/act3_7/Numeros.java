package act3_7;

import java.io.Serializable;

public class Numeros implements Serializable {
    int numero;
    long cuadrado;
    long cubo;

    Numeros(int numero, long cuadrado, long cubo){
        super();
        this.numero = numero;
        this. cuadrado = cuadrado;
        this.cubo = cubo;
    }

    Numeros(int numero){
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public long getCuadrado() {
        return cuadrado;
    }

    public void setCuadrado(long cuadrado) {
        this.cuadrado = cuadrado;
    }

    public long getCubo() {
        return cubo;
    }

    public void setCubo(long cubo) {
        this.cubo = cubo;
    }
}
