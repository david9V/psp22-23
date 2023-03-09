package version4;

import javax.net.ssl.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class ClienteSSL {
    public static void main(String[] args) throws Exception {
        String host = "localhost";
        int port = 6000;

        System.out.println("PROGRAMA CLIENTE INICIADO...");

        FileInputStream ficCerConf = new FileInputStream("/home/usuario/Desktop/git/psp_PERSONAL/ut5/actividades3/CliCertConfianza");
        String claveCerConf = "1234567";

        KeyStore almacenConf = KeyStore.getInstance(KeyStore.getDefaultType());
        almacenConf.load(ficCerConf, claveCerConf.toCharArray());

        FileInputStream ficAlmacen = new FileInputStream("/home/usuario/Desktop/git/psp_PERSONAL/ut5/actividades3/AlmacenCli");
        String claveAlmacen = "clavecli";

        KeyStore almacen = KeyStore.getInstance(KeyStore.getDefaultType());
        almacen.load(ficAlmacen, claveAlmacen.toCharArray());

        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(almacenConf);

        SSLContext contextoSSL = SSLContext.getInstance("TLS");
        contextoSSL.init(null, tmf.getTrustManagers(), null);

        SSLSocketFactory sfact = contextoSSL.getSocketFactory();
        SSLSocket Cliente = (SSLSocket) sfact.createSocket(host, port);

        DataOutputStream outputStream = new DataOutputStream(Cliente.getOutputStream());

        outputStream.writeUTF("Saludos al SERVIDOR desde el CLIENTE");

        DataInputStream inputStream = new DataInputStream(Cliente.getInputStream());

        System.out.println("Recibiendo del SERVIDOR: \n\t" + inputStream.readUTF());

        SSLSession session = Cliente.getSession();
        System.out.println("Host: "+session.getPeerHost());
        System.out.println("Cifrado: "+session.getCipherSuite());
        System.out.println("Protocolo: "+session.getProtocol());
        System.out.println("IDenteificador: "+new BigInteger(session.getId()));
        System.out.println("Creación de la sesión: "+session.getCreationTime());

        X509Certificate certificate = (X509Certificate) session.getPeerCertificates()[0];
        System.out.println("Propietario: "+certificate.getSubjectUniqueID());
        System.out.println("Algoritmo: "+certificate.getSigAlgName());
        System.out.println("Tipo: "+certificate.getType());
        System.out.println("Emisor: "+certificate.getIssuerUniqueID());
        System.out.println("Numero: "+certificate.getSerialNumber());

        inputStream.close();
        outputStream.close();
        Cliente.close();
    }
}