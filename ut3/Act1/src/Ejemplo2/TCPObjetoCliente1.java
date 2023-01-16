package Ejemplo2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class TCPObjetoCliente1 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String Host = "localhost";
        int Puerto = 6000;

        System.out.println("PROGRAMA CLIENTE INCIADO");
        Socket cliente = new Socket(Host, Puerto);

        ObjectInputStream perent = new ObjectInputStream(cliente.getInputStream());

        Persona dato = (Persona) perent.readObject();
        System.out.println("Recibo: " +dato.getNombre() + "*" + dato.getEdad());

        dato.setNombre("Juan Ramos");
        dato.setEdad(22);

        ObjectOutputStream perSal = new ObjectOutputStream(cliente.getOutputStream());

        perSal.writeObject(dato);
        System.out.println("Envío: " + dato.getNombre() + "*" + dato.getEdad());

        perent.close();
        perSal.close();
        cliente.close();
    }
}
