package com.utm.cs.labs.lab2.blockcipher.exceptions;

public class InvalidHexException extends IllegalArgumentException {
    public InvalidHexException(String message) {
        System.err.println(message);
    }
}
