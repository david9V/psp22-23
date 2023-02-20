package org.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

public class Ejemplo9 {
    public static void main(String[] args) {
        try {
            FileInputStream inpub = new FileInputStream("Clave.publica");
            byte[] bufferPub = new byte[inpub.available()];
            inpub.read(bufferPub);
            inpub.close();

            KeyFactory keyDSA = KeyFactory.getInstance("DSA");
            X509EncodedKeySpec clavePublicaSpec = new X509EncodedKeySpec(bufferPub);
            PublicKey clavePublica = keyDSA.generatePublic(clavePublicaSpec);

            FileInputStream firmafic = new FileInputStream("FICHERO.FIRMA");
            byte[] firma = new byte[firmafic.available()];
            firmafic.read(firma);
            firmafic.close();

            Signature dsa = Signature.getInstance("SHA256withDSA");
            dsa.initVerify(clavePublica);

            FileInputStream fichero = new FileInputStream("FICHERO.DAT");
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }

    }
}