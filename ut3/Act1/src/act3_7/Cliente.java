package act3_7;

import java.io.*;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String Host = "localhost";
        int Puerto = 6000;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("PROGRAMA CLIENTE INCIADO");
        Socket cliente = new Socket(Host, Puerto);

        int num = 1;
        ObjectOutputStream numSal = new ObjectOutputStream(cliente.getOutputStream());
        ObjectInputStream numEntr = new ObjectInputStream(cliente.getInputStream());

        while (num != 0){
            System.out.println("Número a enviar al grupo: ");
            num = Integer.valueOf(in.readLine());
            Numeros numeros = new Numeros(num);
            numSal.writeObject(numeros);
            System.out.println("Envío de número: " + num);
            numeros = (Numeros) numEntr.readObject();
            System.out.println("Cuadrado del número " + num + " : " +numeros.getCuadrado());
            System.out.println("Cubo del número " + num + " : " +numeros.cubo);
            if (numeros.getNumero() == 0){
                numEntr.close();
            }
        }
        System.out.println("CLiente cerrado");
        numSal.close();
        cliente.close();
    }
}
