package com.utm.cs.labs.ciphers.symmetric.block.exceptions;

public class IncorrectDecryptionException extends Exception {
    public IncorrectDecryptionException(String message) {
        System.err.println("Decrypted data is not valid.");
    }
}
