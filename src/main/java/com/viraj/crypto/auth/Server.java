package com.viraj.crypto.auth;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * Created by Viraj on 1/6/2017.
 */
public class Server {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        String host = "localhost";
        int port = 9999;
        String userName = "foo";
        String password = "boo";

        ServerSocket serverSocket = new ServerSocket(port);
        Socket socket = serverSocket.accept();

        DataInputStream dataIn = new DataInputStream(socket.getInputStream());
        String user = dataIn.readUTF();
        long t1 = dataIn.readLong();
        double q1 = dataIn.readDouble();
        int length = dataIn.readInt();

        byte[] protectedByte = new byte[length];
        dataIn.readFully(protectedByte);

        byte[] local = Protection.makeDigest(user, password, t1, q1);
        if(MessageDigest.isEqual(protectedByte, local))
        {
            System.out.println("Login Successful");
        }
        else
        {
            System.out.println("Login failed");
        }
        socket.close();
    }
}
