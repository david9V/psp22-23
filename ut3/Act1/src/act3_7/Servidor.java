package act3_7;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        int numeroPuerto = 6000;
        ServerSocket servidor = new ServerSocket(numeroPuerto);

        System.out.println("Esperando al cliente....");
        Socket cliente = servidor.accept();
        int n = 1;
        ObjectInputStream numEntr = new ObjectInputStream(cliente.getInputStream());
        ObjectOutputStream numSal = new ObjectOutputStream(cliente.getOutputStream());

        while (n != 0){
            Numeros numeros = (Numeros) numEntr.readObject();
            System.out.println("Recibo número " + numeros.getNumero());
            numeros.setCuadrado((long) numeros.getNumero() * numeros.getNumero());
            numeros.setCubo((long) numeros.getNumero() * numeros.getNumero() * numeros.getNumero());
            if (numeros.getNumero() != 0){
                numSal.writeObject(numeros);
                System.out.println("Envío finalizado");
                if (numeros.getNumero() == 0)
                    numSal.close();
                    numEntr.close();
            } else
                System.out.println("Servidor cerrado");
        }


    }
}
