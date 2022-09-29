package com.utm.cs.labs.lab1.classicalciphers;

public interface Cipher {

    String encrypt(String message, String key);
    String decrypt(String message, String key);

}
