package com.utm.cs.labs.lab2.blockcipher.exceptions;

public class WrongNumberOfBitsException extends IllegalArgumentException {
    public WrongNumberOfBitsException(String message) {
        System.err.println(message);
    }
}
