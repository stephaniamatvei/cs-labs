package com.utm.cs.labs.lab2.blockcipher.exceptions;

public class InvalidKeyException extends java.security.InvalidKeyException {
    public InvalidKeyException(String message) {
        System.err.println(message);
    }
}
