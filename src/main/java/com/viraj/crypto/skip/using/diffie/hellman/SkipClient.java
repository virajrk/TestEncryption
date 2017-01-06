package com.viraj.crypto.skip.using.diffie.hellman;

import javax.crypto.KeyAgreement;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

/**
 * Created by vkulkarni3 on 11/27/16.
 */
public class SkipClient {
    public static final String HOST = "127.0.0.1";
    public static final int PORT = 7999;
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException,
            IOException, InvalidKeySpecException, InvalidKeyException {

        //Generate Key Pair
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DH");
        keyPairGenerator.initialize(Skip.DH_PARAMETER_SPEC);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        //Connect to server
        Socket socket = new Socket(HOST, PORT);
        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

        //Send Client public key
        byte[] keyBytes = keyPair.getPublic().getEncoded();
        dataOutputStream.writeInt(keyBytes.length);
        dataOutputStream.write(keyBytes);

        //Get public key from server
        keyBytes = new byte[dataInputStream.readInt()];
        dataInputStream.read(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("DH");
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);

        //Generate Secret Value
        KeyAgreement keyAgreement = KeyAgreement.getInstance("DH");
        keyAgreement.init(keyPair.getPrivate());
        keyAgreement.doPhase(publicKey, true);
        byte[] secret = keyAgreement.generateSecret();

        System.out.println(secret.toString());

        //Closeit
        dataOutputStream.close();
        dataInputStream.close();
    }
}
