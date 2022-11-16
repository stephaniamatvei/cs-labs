package com.utm.cs.labs.ciphers.symmetric.block.exceptions;

public class InvalidKeyException extends java.security.InvalidKeyException {
    public InvalidKeyException(String message) {
        System.err.println(message);
    }
}
