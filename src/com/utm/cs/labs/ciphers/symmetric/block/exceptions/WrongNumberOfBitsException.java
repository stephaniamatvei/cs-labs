package com.utm.cs.labs.ciphers.symmetric.block.exceptions;

public class WrongNumberOfBitsException extends IllegalArgumentException {
    public WrongNumberOfBitsException(String message) {
        System.err.println(message);
    }
}
