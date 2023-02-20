package org.example;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.security.Key;

public class Ejemplo13_Descifra {
    public static void main(String[] args) {
        try{
            ObjectInputStream oin = new ObjectInputStream(new FileInputStream("Clave.secreta"));
            Key claveSecreta = (Key) oin.readObject();
            oin.close();

            Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
            c.init(Cipher.DECRYPT_MODE, claveSecreta);

            CipherInputStream in = new CipherInputStream(new FileInputStream("FicheroPDF.Cifrado"), c);

            int tambloque = c.getBlockSize();
            byte[] bytes = new byte[tambloque];

            FileOutputStream fileOut = new FileOutputStream("FICHEROdescifrado.pdf");

            int i = in.read(bytes);
            while (i != -1){
                fileOut.write(bytes, 0, i);
                i = in.read(bytes);
            }
            fileOut.close();
            in.close();
            System.out.println("Fichero descifrado con clave secreta");
        } catch(Exception e){
            System.err.println(e);
        }
    }
}
