package com.viraj.crypto;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created by vkulkarni3 on 11/24/16.
 */
public class SecretWriting {

    static String message = "Hello World";
    static String strKey = "boom";

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException, BadPaddingException, IllegalBlockSizeException {

        //Key Generation
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        keyGenerator.init(new SecureRandom(strKey.getBytes()));
        Key key = keyGenerator.generateKey();

        //Encrypt
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] raw = cipher.doFinal(message.getBytes("UTF-8"));

        //Print encypted value
        BASE64Encoder encoder = new BASE64Encoder();
        String base64 = encoder.encode(raw);
        System.out.println("Encypted - " + base64);

        //Its time to decypt encypted value
        cipher.init(Cipher.DECRYPT_MODE, key);
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] rawDecode = decoder.decodeBuffer(base64);
        byte[] decyptedMessage = cipher.doFinal(rawDecode);

        //Print decrypted
        String result = new String(decyptedMessage, "UTF-8");
        System.out.println("Decypted - " + result);
    }
}
