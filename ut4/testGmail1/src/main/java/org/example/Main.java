package org.example;

import org.apache.commons.net.smtp.*;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import java.io.IOException;
import java.io.Writer;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.spec.InvalidKeySpecException;

public class Main {
    public static void main(String[] args) {
        AuthenticatingSMTPClient client = new AuthenticatingSMTPClient();

        String server = "smtp.gmail.com";
        String username = "mcharod03@gmail.com";
        String password = "jxbeelfulflpndpp";
        int puerto = 587;
        String remitente = "davidvilches818@gmail.com";

        try{
            int respuesta;
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(null, null);
            KeyManager km = kmf.getKeyManagers()[0];

            client.connect(server, puerto);
            System.out.println("1 - " + client.getReplyString());
            client.setKeyManager(km);

            respuesta = client.getReplyCode();

            if (!SMTPReply.isPositiveCompletion(respuesta)){
                client.disconnect();
                System.err.println("CONEXIÓN RECHAZADA");
                System.exit(1);
            }
            client.ehlo(server);
            System.out.println("2 - " + client.getReplyString());
            if (client.execTLS()){
                System.out.println("3 - " + client.getReplyString());
                if (client.auth(AuthenticatingSMTPClient.AUTH_METHOD.PLAIN, username, password)){
                    System.out.println("4 - " + client.getReplyString());
                    String destino1 = "mcharod03@gmail.com";
                    String asunto = "Prueba de SMTPClient con GMAIL";
                    String mensaje = "Hola. \nEnviando saludos. \nUsando GMAIL. \nChao.";
                    SimpleSMTPHeader cabecera = new SimpleSMTPHeader(remitente, destino1, asunto);
                    client.setSender(remitente);
                    client.addRecipient(destino1);
                    System.out.println("5 - " + client.getReplyString());
                    Writer writer = client.sendMessageData();
                    if (writer == null){
                        System.out.println("FALLO AL ENVIAR DATA.");
                        System.exit(1);
                    }
                    writer.write(cabecera.toString());
                    writer.write(mensaje);
                    writer.close();
                    System.out.println("6 - " + client.getReplyString());

                    boolean exito = client.completePendingCommand();
                    System.out.println("7 - " + client.getReplyString());
                    if (!exito){
                        System.out.println("FALLO AL FINALIZAR TRANSACCIÓN.");
                        System.exit(1);
                    } else {
                        System.out.println("MENSAJE ENVIADO CON ÉXITO......");
                    }
                } else
                    System.out.println("USUARIO NO AUTENTICADO.");
            } else
                System.out.println("FALLO AL EJECUTAR STARTTLS.");
        } catch(IOException | NoSuchAlgorithmException | InvalidKeyException | InvalidKeySpecException e){
            System.err.println("Could not connect to server.");
            e.printStackTrace();
            System.exit(1);
        } catch (UnrecoverableKeyException e) {
            throw new RuntimeException(e);
        } catch (KeyStoreException e) {
            throw new RuntimeException(e);
        }
        try{
            client.disconnect();
        } catch(IOException f){
            f.printStackTrace();
        }
        System.out.println("Fin de envío.");
        System.exit(0);
    }
}