package org.example;

import java.io.FileInputStream;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;

public class Ejemplo9 {
    try{
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

}
