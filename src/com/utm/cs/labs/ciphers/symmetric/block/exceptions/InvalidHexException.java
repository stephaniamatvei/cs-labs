package com.utm.cs.labs.ciphers.symmetric.block.exceptions;

public class InvalidHexException extends IllegalArgumentException {
    public InvalidHexException(String message) {
        System.err.println(message);
    }
}
