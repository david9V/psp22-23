import javax.net.ssl.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

public class ServidorSSL {
    public static void main(String[] args) throws IOException, UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException, CertificateException, KeyManagementException {
        int puerto = 6000;
        //java -Djavax.net.ssl.keyStore=Almacensrv -Djavax.net.ssl.keyStorePassword=1234567 ServidorSSL        VERSION 1

        //System.setProperty("javax.net.ssl.keyStore", "Almacensrv");             VERSION 2
        //System.setProperty("javax.net.ssl.keyStorePassword", "1234567");

        //SSLServerSocketFactory sfact = (SSLServerSocketFactory)  SSLServerSocketFactory.getDefault(); Sustituido por versión 3
        //SSLServerSocket servidorSSL = (SSLServerSocket) sfact.createServerSocket(puerto); Sustituido por version 3

        //VERSION3
        FileInputStream ficAlmacen = new FileInputStream("E:/Mis Cosas/PSPhola/ut5/actividades3/Almacensrv");
        String claveAlmacen = "1234567";
        KeyStore almacen = KeyStore.getInstance(KeyStore.getDefaultType());
        almacen.load(ficAlmacen, claveAlmacen.toCharArray());

        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmf.init(almacen, claveAlmacen.toCharArray());
        SSLContext contextoSSL = SSLContext.getInstance("TLS");
        contextoSSL.init(kmf.getKeyManagers(), null, null);
        SSLServerSocketFactory sfact = contextoSSL.getServerSocketFactory();
        SSLServerSocket servidorSSL = (SSLServerSocket) sfact.createServerSocket(puerto);

        SSLSocket clienteConectado = null;
        DataInputStream flujoEntrada = null;
        DataOutputStream flujoSalida = null;

        for (int i = 1; i < 5; i++) {
            System.out.println("Esperando al cliente " + i);
            clienteConectado = (SSLSocket) servidorSSL.accept();
            flujoEntrada = new DataInputStream(clienteConectado.getInputStream());

            System.out.println("Recibiendo del CLIENTE: " + i + "\n\t" + flujoEntrada.readUTF());
            flujoSalida = new DataOutputStream(clienteConectado.getOutputStream());
            flujoSalida.writeUTF("Saludos al cliente del servidor");
        }

        flujoEntrada.close();
        flujoSalida.close();
        clienteConectado.close();
        servidorSSL.close();
    }
}
