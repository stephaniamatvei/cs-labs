package com.utm.cs.labs.ciphers.asymmetric.rsa;

public interface Cipher {
    String encrypt(String message);
    String decrypt(StringBuilder message);
}
