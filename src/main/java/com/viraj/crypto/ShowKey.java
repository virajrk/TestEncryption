package com.viraj.crypto;

import java.security.*;

/**
 * Created by vkulkarni3 on 11/27/16.
 */
public class ShowKey {
    public static void main(String[] args) throws KeyStoreException {
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        System.out.println(keyStore.aliases().toString());


    }
}
