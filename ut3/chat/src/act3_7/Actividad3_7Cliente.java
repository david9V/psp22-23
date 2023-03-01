package act3_7;

import java.io.*;
import java.net.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Actividad3_7Cliente {
	public static void main(String[] arg) throws IOException, ClassNotFoundException {
		String Host = "localhost";
		int Puerto = 6000;// puerto remoto

		Scanner sc = new Scanner(System.in);

		Socket cliente = null;
		try {
			cliente = new Socket(Host, Puerto);
			System.out.println("PROGRAMA CLIENTE INICIADO....");
		} catch (ConnectException ce) {
			System.out.println("ERROR AL ESTABLECER LA CONEXI�N CON EL SERVIDOR....");
			System.exit(0);
		}
		// Flujo de entrada para objetos
		ObjectInputStream perEnt = new ObjectInputStream(cliente.getInputStream());

		// FLUJO DE salida para objetos
		ObjectOutputStream perSal = new ObjectOutputStream(cliente.getOutputStream());

		int numero = 0;

		do {

			System.out.print("Introduce un n�mero: ");

			try {
				numero = sc.nextInt();
				sc.nextLine();
			} catch (InputMismatchException nn) {
				sc.nextLine();
				numero = 1;   // se asigna un valor mayor a 0 para poder pedir otro número
				System.out.print("\tN�mero incorrecto...\n");
				continue;
			}
			Numeros n = new Numeros();
			n.setNumero(numero);

			// Se env�a el objeto
			perSal.writeObject(n);

			// Se recibe un objeto
			if (numero > 0) {
				Numeros dato = (Numeros) perEnt.readObject();// recibo objeto

				System.out.println("\tCuadrado : " + dato.getCuadrado() + ", Cubo: * " + dato.getCubo());
			}

		} while (numero > 0);

		System.out.println("CLIENTE FINALIZADO....");

		// CERRAR STREAMS Y SOCKETS
		perEnt.close();
		perSal.close();
		cliente.close();
	}
}// ..
