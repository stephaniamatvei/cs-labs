package com.utm.cs.labs.lab3.rsa;

public interface Cipher {
    void encrypt(String message);
    String decrypt();
}
