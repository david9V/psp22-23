package org.example;

import sun.reflect.annotation.ExceptionProxy;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;

public class Ejemplo8 {

    public static void main(String[] args) {
        try{
/*
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("DSA");
            kpg.initialize(2048);
            KeyPair kp = kpg.generateKeyPair();
            Key pvt = kp.getPrivate();

            String outFile = "Clave";
            FileOutputStream out = new FileOutputStream(outFile + ".privada");
            out.write(pvt.getEncoded());
            out.close();

            Key pub = kp.getPublic();

            FileOutputStream out2 = new FileOutputStream(outFile + ".publica");
            out2.write(pvt.getEncoded());
            out2.close();
*/
            FileInputStream inpriv = new FileInputStream("Clave.privada");
            byte[] bufferPriv = new byte[inpriv.available()];
            inpriv.read(bufferPriv);
            inpriv.close();

            PKCS8EncodedKeySpec clavePrivadaSpec = new PKCS8EncodedKeySpec(bufferPriv);
            KeyFactory keyDSA = KeyFactory.getInstance("DSA");
            PrivateKey clavePrivada = keyDSA.generatePrivate(clavePrivadaSpec);

            Signature dsa = Signature.getInstance("SHA256withDSA");
            dsa.initSign(clavePrivada);

            FileInputStream fichero = new FileInputStream("FICHERO.DAT");
            BufferedInputStream bis = new BufferedInputStream(fichero);
            byte[] buffer = new byte[bis.available()];
            int len;

            while ((len = bis.read(buffer)) >= 0)
                dsa.update(buffer, 0 , len);
            bis.close();

            byte[] firma = dsa.sign();

            FileOutputStream fos = new FileOutputStream("FICHERO.FIRMA");
            fos.write(firma);
            fos.close();
        } catch(Exception e){
            System.out.println(e);
            //main
        }
    }
}


//..................................................................................................................................................................................