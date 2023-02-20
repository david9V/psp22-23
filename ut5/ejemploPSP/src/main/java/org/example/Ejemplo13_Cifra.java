package org.example;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.security.Key;

public class Ejemplo13_Cifra {
    public static void main(String[] args) {
        try{
            ObjectInputStream oin = new ObjectInputStream(new FileInputStream("Clave.secreta"));
            Key claveSecreta = (Key) oin.readObject();
            oin.close();

            Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
            c.init(Cipher.ENCRYPT_MODE, claveSecreta);

            FileInputStream fileIn = new FileInputStream("FICHERO.pdf");

            CipherOutputStream out = new CipherOutputStream(new FileOutputStream("FicheroPDF.Cifrado"), c);
            int tambloque = c.getBlockSize();
            byte[] bytes = new byte[tambloque];

            int i = fileIn.read(bytes);
            while (i != -1){
                out.write(bytes,0 ,i);
                i = fileIn.read(bytes);
            }
            out.flush();
            out.close();
            fileIn.close();
            System.out.println("Fichero cifrado con clave secreta.");
        } catch (Exception e){
            System.err.println(e);
        }
    }
}
