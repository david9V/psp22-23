import javax.net.ssl.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

public class ClienteSSL {
    public static void main(String[] args) throws IOException, CertificateException, NoSuchAlgorithmException, KeyStoreException, UnrecoverableKeyException, KeyManagementException {
        String Host = "localhost";
        int puerto = 6000;
        //java -Djavax.net.ssl.trustStore=CliCertConfianza -Djava.net.ssl.trustStorePassword=1234567 ClienteSSL        VERSION 1

        //System.setProperty("javax.net.ssl.trustStore", "CliCertConfianza");             VERSION 2
        //System.setProperty("javax.net.ssl.trustStorePassword", "1234567");

        System.out.println("PROGRAMA CLIENTE INICIADO");

        //VERSION 3

        //FileInputStream ficCerConf = new FileInputStream("E:/Mis Cosas/PSPhola/ut5/actividades3/CliCertConfianza");
        FileInputStream ficCerConf = new FileInputStream("/home/usuario/Desktop/git/psp_PERSONAL/ut5/actividades3/CliCertConfianza");
        String claveCerfConf = "1234567";
        KeyStore almacenConf = KeyStore.getInstance(KeyStore.getDefaultType());
        almacenConf.load(ficCerConf, claveCerfConf.toCharArray());
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(almacenConf);
        SSLContext contextoSSL = SSLContext.getInstance("TLS");
        contextoSSL.init(null, tmf.getTrustManagers(), null);
        SSLSocketFactory sfact = contextoSSL.getSocketFactory();
        SSLSocket Cliente = (SSLSocket) sfact.createSocket(Host, puerto);

        //SSLSocketFactory sfact = (SSLSocketFactory) SSLSocketFactory.getDefault(); Sustituido por versión 3
        //SSLSocket Cliente = (SSLSocket) sfact.createSocket(Host, puerto); Sustituido por versión 3

        DataOutputStream flujoSalida = new DataOutputStream(Cliente.getOutputStream());

        flujoSalida.writeUTF("Saludos al SERVIDOR DESDE EL CLIENTE");
        DataInputStream flujoentrada = new DataInputStream(Cliente.getInputStream());

        System.out.println("Recibiendo del SERVIDOR: \n\t" + flujoentrada.readUTF());
        flujoentrada.close();
        flujoSalida.close();
        Cliente.close();
    }
}
