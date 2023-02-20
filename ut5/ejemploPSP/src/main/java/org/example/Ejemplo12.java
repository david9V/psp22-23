package org.example;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.*;

public class Ejemplo12 {

    public static void main(String[] args) {

        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(1024);
            KeyPair par = keyGen.generateKeyPair();
            PrivateKey clavepriv = par.getPrivate();
            PublicKey clavepub = par.getPublic();

            KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init(128);
            SecretKey clavesecreta = kg.generateKey();

            Cipher c = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            c.init(Cipher.WRAP_MODE, clavepub);
            byte[] claveenvuelta = c.wrap(clavesecreta);

            c = Cipher.getInstance("AES/ECB/PKCS5Padding");
            c.init(Cipher.ENCRYPT_MODE, clavesecreta);
            byte[] textoPlano = "Esto es un Texto Plano".getBytes();
            byte[] textoCifrado = c.doFinal(textoPlano);
            System.out.println("Encriptado: "+new String(textoCifrado));

            Cipher c2 = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            c2.init(Cipher.UNWRAP_MODE, clavepriv);
            Key clavedesenvuelta = c2.unwrap(claveenvuelta, "AES", Cipher.SECRET_KEY);

            c2 = Cipher.getInstance("AES/ECB/PKCS5Padding");
            c2.init(Cipher.DECRYPT_MODE, clavedesenvuelta);
            byte[] desencriptado = c2.doFinal(textoCifrado);
            System.out.println("Desencriptado: "+new String(desencriptado));
        }catch(Exception e) {
            e.printStackTrace();
        }

    }

}