package com.viraj.crypto;

import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by vkulkarni3 on 11/24/16.
 */
public class Masher {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {

        MessageDigest messageDigest = MessageDigest.getInstance("MD5");

        FileInputStream fileInputStream = new FileInputStream(args[0]);

        byte[] bytes = new byte[1024];

        int length;

        while((length = fileInputStream.read(bytes))!= -1)
        {
            messageDigest.update(bytes, 0, length);
        }

        byte[] rawBytes = messageDigest.digest();

        BASE64Encoder encoder = new BASE64Encoder();

        String base64 = encoder.encode(rawBytes);

        System.out.println("Encoded - " + base64);

    }
}
