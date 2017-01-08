package com.viraj.crypto.auth;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Viraj on 1/6/2017.
 */
public class Protection {

    public static byte[] makeDigest(String user, String password, long t1, double q1) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(user.getBytes());
        messageDigest.update(password.getBytes());
        messageDigest.update(makeByte(t1, q1));
        return messageDigest.digest();
    }

    public static byte[] makeByte(long t1, double q1) {
        try {
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            DataOutputStream dataOut = new DataOutputStream(byteOut);
            dataOut.writeLong(t1);
            dataOut.writeDouble(q1);
            return byteOut.toByteArray();
        } catch (IOException e) {
            return new byte[0];
        }
    }
}
