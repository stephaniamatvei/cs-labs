package com.utm.cs.labs.ciphers.classical;

public interface Cipher {

    String encrypt(String message, String key);
    String decrypt(String message, String key);

}
