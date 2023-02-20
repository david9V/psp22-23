package org.example;

import sun.reflect.annotation.EnumConstantNotPresentExceptionProxy;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Ejemplo10 {

    public static void main(String[] args) {
        try {
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init(128);
            SecretKey claveSecreta = kg.generateKey();

            Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
            c.init(Cipher.ENCRYPT_MODE, claveSecreta);
            byte textoPlano[] = "Esto es un texto plano y acabo de ser desencriptado ;)".getBytes();
            byte textoCifrado[] = c.doFinal(textoPlano);
            System.out.println("Encriptado: " + new String(textoCifrado));

            c.init(Cipher.DECRYPT_MODE, claveSecreta);
            byte desencriptado[] = c.doFinal(textoCifrado);
            System.out.println("Desencriptado: " + new String(desencriptado));
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
