package com.utm.cs.labs.ciphers.symmetric.block.exceptions;

public class InputSizeMismatchException extends IllegalArgumentException {
    public InputSizeMismatchException(String message) {
        System.err.println("Input must be divisible into 128 bit blocks.");
        System.err.println(message);
    }
}
