package com.viraj.crypto.auth;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * Created by Viraj on 1/7/2017.
 */
public class Client {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        String host = "localhost";
        int port = 9999;
        String user = "foo";
        String password = "boo";

        Socket socket = new Socket(host, port);

        DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());
        long t1 = (new Date().getTime());
        double q1 = Math.random();
        byte[] protectedByte = Protection.makeDigest(user, password, t1, q1);

        dataOut.writeUTF(user);
        dataOut.writeLong(t1);
        dataOut.writeDouble(q1);
        dataOut.writeInt(protectedByte.length);
        dataOut.write(protectedByte);
        dataOut.flush();
    }
}
