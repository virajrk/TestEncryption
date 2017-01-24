package com.viraj.crypto.auth;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Date;

import static java.security.KeyStore.getInstance;

/**
 * Created by Viraj on 1/23/2017.
 */
public class StrongClient {
    public static void main(String args[]) throws IOException, NoSuchAlgorithmException, KeyStoreException, CertificateException,
            UnrecoverableEntryException, InvalidKeyException, SignatureException {
        String host = "localhost";
        String keyStoreFile = "c:\\windows\\.keystore";
        String storePass = "password";
        String alias = "viraj_auth_test";
        String keyPass = "password";

        int port = 7999;

        Socket socket = new Socket(host, port);

        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(new FileInputStream(keyStoreFile), storePass.toCharArray());
        KeyStore.ProtectionParameter protectionParameter = new KeyStore.PasswordProtection(keyPass.toCharArray());
        KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(alias, protectionParameter);
        PrivateKey privateKey = privateKeyEntry.getPrivateKey();

        sendAuth(alias, privateKey, socket.getOutputStream());

        socket.close();
    }

    private static void sendAuth(String alias, PrivateKey privateKey, OutputStream outputStream) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        long t = (new Date()).getTime();
        double q = Math.random();

        Signature signature = Signature.getInstance("DSA");
        signature.initSign(privateKey);
        signature.update(Protection.makeByte(t,q));

        byte[] signatureByte = signature.sign();

        dataOutputStream.writeUTF(alias);
        dataOutputStream.writeLong(t);
        dataOutputStream.writeDouble(q);
        dataOutputStream.writeInt(signatureByte.length);
        dataOutputStream.write(signatureByte);
        dataOutputStream.flush();
    }
}
