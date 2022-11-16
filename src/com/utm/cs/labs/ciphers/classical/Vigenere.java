package com.utm.cs.labs.ciphers.classical;

public class Vigenere implements Cipher {

    public String encrypt(String message, final String key) {
        StringBuilder res = new StringBuilder();
        message = message.toUpperCase();
        for (int i = 0, j = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            if (c < 'A' || c > 'Z')
                continue;
            res.append((char) ((c + key.charAt(j) - 2 * 'A') % 26 + 'A'));
            j = ++j % key.length();
        }
        return res.toString();
    }

    public String decrypt(String message, final String key) {
        StringBuilder res = new StringBuilder();
        message = message.toUpperCase();
        for (int i = 0, j = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            if (c < 'A' || c > 'Z')
                continue;
            res.append((char) ((c - key.charAt(j) + 26) % 26 + 'A'));
            j = ++j % key.length();
        }
        return res.toString();
    }

}
