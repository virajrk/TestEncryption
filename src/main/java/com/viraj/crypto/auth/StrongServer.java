package com.viraj.crypto.auth;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.*;
import java.security.cert.CertificateException;

/**
 * Created by Viraj on 1/23/2017.
 */
public class StrongServer {
    protected static KeyStore keyStore;

    public static void main(String[] args) throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException,
            SignatureException, InvalidKeyException {

        String keystoreFile = "c:\\windows\\.keystore";
        String storePass = "password";

        int port = 7999;
        ServerSocket serverSocket = new ServerSocket(port);
        Socket socket = serverSocket.accept();

        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(new FileInputStream(keystoreFile), storePass.toCharArray());

        if (auth(socket.getInputStream())) {
            System.out.println("Client loged in");
        } else {
            System.out.println("Client log in failed");
        }
        socket.close();
    }

    public static boolean auth(InputStream inputStream) throws IOException, NoSuchAlgorithmException, KeyStoreException, InvalidKeyException, SignatureException {
        DataInputStream dataInputStream = new DataInputStream(inputStream);

        String user = dataInputStream.readUTF();
        long t = dataInputStream.readLong();
        double q = dataInputStream.readDouble();
        int length = dataInputStream.readInt();
        byte[] signatureBytes = new byte[length];
        dataInputStream.readFully(signatureBytes);

        Signature signature = Signature.getInstance("DSA");
        signature.initVerify(keyStore.getCertificate(user).getPublicKey());
        signature.update(Protection.makeByte(t, q));

        return signature.verify(signatureBytes);
    }
}
