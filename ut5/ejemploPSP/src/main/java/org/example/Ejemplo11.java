package org.example;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;

public class Ejemplo11 {
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(1024);
        KeyPair par = keyGen.generateKeyPair();
        PrivateKey clavePriv = par.getPrivate();
        PublicKey clavepub = par.getPublic();

        Cipher c = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        c.init(Cipher.ENCRYPT_MODE, clavepub);

        byte[] textoPlano = "Esto es un texto plano".getBytes();
        System.out.println(new String(textoPlano) + " | Antes de encriptar");
        byte[] textoCifrado = c.doFinal(textoPlano);
        System.out.println(new String(textoCifrado));

        c.init(Cipher.DECRYPT_MODE, clavePriv);
        byte[] desenciptado = c.doFinal(textoCifrado);
        System.out.println(new String(desenciptado) + " | Tras desencriptar");
    }
}
