package version1;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
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
        //java -Djavax.net.ssl.trustStore=CliCertConfianza -Djava.net.ssl.trustStorePassword=1234567 version3.ClienteSSL.java

        System.out.println("PROGRAMA CLIENTE INICIADO");

        SSLSocketFactory sfact = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket Cliente = (SSLSocket) sfact.createSocket(Host, puerto);

        DataOutputStream flujoSalida = new DataOutputStream(Cliente.getOutputStream());

        flujoSalida.writeUTF("Saludos al SERVIDOR DESDE EL CLIENTE");
        DataInputStream flujoentrada = new DataInputStream(Cliente.getInputStream());

        System.out.println("Recibiendo del SERVIDOR: \n\t" + flujoentrada.readUTF());
        flujoentrada.close();
        flujoSalida.close();
        Cliente.close();
    }
}
