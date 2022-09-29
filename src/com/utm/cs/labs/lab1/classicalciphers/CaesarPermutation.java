package com.utm.cs.labs.lab1.classicalciphers;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.LinkedHashSet;
import java.util.Set;

public class CaesarPermutation implements Cipher {

    private static final String alphabet = "abcdefghijklmnopqrstuvwxyz";

    public String getCharsPermutation() {
        Set<Object> set = new LinkedHashSet<>();
        SecureRandom random;
        StringBuilder chars = new StringBuilder();
        try {
            random = SecureRandom.getInstance("SHA1PRNG");
            while (set.size() < 26) {
                set.add(alphabet.charAt(random.nextInt(alphabet.length())));
            }
            for (Object object : set) {
                chars.append(object);
            }
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("No such Algorithm");
        }
        return chars.toString();
    }

    public String encrypt(String plaintext, String key) {
        StringBuilder ciphertext = new StringBuilder();

        for (char chr : plaintext.toLowerCase().toCharArray()) {
            byte position = (byte) alphabet.indexOf(chr);
            if (chr == ' ') {
                ciphertext.append(" ");
            } else {
                ciphertext.append(key.charAt(position));
            }
        }

        return ciphertext.toString();
    }

    public String decrypt(String ciphertext, String key) {
        StringBuilder plaintext = new StringBuilder();

        for (char chr : ciphertext.toLowerCase().toCharArray()) {
            byte position = (byte) key.indexOf(chr);
            if (chr == ' ') {
                plaintext.append(" ");
            } else {
                plaintext.append(alphabet.charAt(position));
            }
        }

        return plaintext.toString();
    }

}
