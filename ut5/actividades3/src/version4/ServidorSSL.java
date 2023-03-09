package version4;

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

        int port = 6000;
        FileInputStream ficAlmacen = new FileInputStream("/home/usuario/Desktop/git/psp_PERSONAL/ut5/actividades3/Almacensrv");

        String claveAlmacen = "1234567";

        KeyStore almacen = KeyStore.getInstance(KeyStore.getDefaultType());
        almacen.load(ficAlmacen, claveAlmacen.toCharArray());

        FileInputStream ficCerConf = new FileInputStream("/home/usuario/Desktop/git/psp_PERSONAL/ut5/actividades3/SrvCertConfianza");
        String claveCerConf = "cercli";

        KeyStore alamcenConf = KeyStore.getInstance(KeyStore.getDefaultType());
        alamcenConf.load(ficCerConf, claveCerConf.toCharArray());

        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmf.init(almacen, claveAlmacen.toCharArray());

        SSLContext contextoSSL = SSLContext.getInstance("TLS");
        contextoSSL.init(kmf.getKeyManagers(), null,null);

        SSLServerSocketFactory sfact = contextoSSL.getServerSocketFactory();
        SSLServerSocket seridorSSL = (SSLServerSocket) sfact.createServerSocket(port);

        SSLSocket clientConnected = null;
        DataInputStream inputStream = null;
        DataOutputStream outputStream = null;

        for (int i = 1; i < 5; i++) {
            System.out.println("Esperando al cliente " + i);
            clientConnected = (SSLSocket) seridorSSL.accept();

            inputStream = new DataInputStream(clientConnected.getInputStream());
            System.out.println("Recibiendo del CLIENTE: " + i + "\n\t" + inputStream.readUTF());

            outputStream = new DataOutputStream(clientConnected.getOutputStream());
            outputStream.writeUTF("Saludos al cliente del servidor");
        }

        inputStream.close();
        outputStream.close();
        clientConnected.close();
        seridorSSL.close();
    }
}
