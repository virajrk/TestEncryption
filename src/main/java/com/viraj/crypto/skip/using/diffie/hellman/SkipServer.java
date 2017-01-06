package com.viraj.crypto.skip.using.diffie.hellman;

import javax.crypto.KeyAgreement;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

/**
 * Created by vkulkarni3 on 11/26/16.
 */
public class SkipServer {

    public static final int PORT = 7999;

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException,
            IOException, InvalidKeySpecException, InvalidKeyException {

        //Create DH key pair
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DH");
        keyPairGenerator.initialize(Skip.DH_PARAMETER_SPEC);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        //Start server
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Listing on " + PORT);
        Socket socket = serverSocket.accept();
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

        //Accept Key from client
        byte[] keyBytes = new byte[dataInputStream.readInt()];
        dataInputStream.readFully(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("DH");
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
        PublicKey clientPublicKey = keyFactory.generatePublic(x509EncodedKeySpec);

        //Send server public key
        keyBytes = keyPair.getPublic().getEncoded();
        dataOutputStream.writeInt(keyBytes.length);
        dataOutputStream.write(keyBytes);

        //Generate secret value
        KeyAgreement keyAgreement = KeyAgreement.getInstance("DH");
        keyAgreement.init(keyPair.getPrivate());
        keyAgreement.doPhase(clientPublicKey, true);
        byte[] secrets = keyAgreement.generateSecret();

        //Print out secret
        System.out.println(secrets.toString());

        //Closing
        dataOutputStream.close();
        dataInputStream.close();
    }
}
