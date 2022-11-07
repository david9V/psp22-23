package org.iesvi.ws;

public class CalculadoraConsumer {
    public static void main(String[] args) {
        CalculadoraImplService calculadoraservice = new CalculadoraImplService();
        Calculadora consumer = calculadoraservice.getCalculadoraImplPort();
        System.out.println("Suma");
        System.out.println(consumer.operacion(1,6,4));
    }
}
