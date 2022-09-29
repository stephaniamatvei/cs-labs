package com.utm.cs.labs.lab1.classicalciphers;

import java.util.LinkedHashSet;
import java.util.Set;

public class Playfair {

    private static final String alphabet = "abcdefghiklmnopqrstuvwxyz";

    public String generateMatrix(String key) {

        String matrixString = key + alphabet;
        StringBuilder mat = new StringBuilder();
        Set<Object> set = new LinkedHashSet<>();

        for (char chr : matrixString.toLowerCase().toCharArray()) {
            set.add(chr);
        }
        for (Object chr : set) {
            mat.append(chr);
        }

        return mat.toString();
    }

    public String[] divideToPairs(String message) {

        message = formatMessage(message);
        String[] pairs = new String[message.length() / 2];
        int j = 0;

        for (int i = 0; i < message.length(); i = i + 2) {
            pairs[j] = message.substring(i, i + 2);
            j++;
        }

        return pairs;
    }

    public String formatMessage(String message) {

        message = message.toLowerCase().replace(" ", "");
        StringBuilder mes = new StringBuilder(message);

        for (int i = 0; i < message.length() - 1; i += 2) {
            if (mes.charAt(i) == mes.charAt(i + 1)) {
                mes.insert(i + 1, "x");
            }
        }
        if (mes.length() % 2 == 1) {
            mes.append("x");
        }

        return mes.toString();
    }

    public String encrypt(String[] pairs, String matrix) {

        StringBuilder ciphertext = new StringBuilder();

        for (String pair : pairs) {//using zero index
            byte row1 = (byte) (matrix.indexOf(pair.charAt(0)) / 5);
            byte col1 = (byte) (matrix.indexOf(pair.charAt(0)) % 5);
            byte row2 = (byte) (matrix.indexOf(pair.charAt(1)) / 5);
            byte col2 = (byte) (matrix.indexOf(pair.charAt(1)) % 5);

            char chr1;
            char chr2;
            if (col1 == col2) {
                chr2 = matrix.charAt(((row2 + 1) % 5 * 5 + col2));
                chr1 = matrix.charAt(((row1 + 1) % 5 * 5 + col1));
            } else if (row1 == row2) {
                chr1 = matrix.charAt(row1 * 5 + ((col1 + 1) % 5));
                chr2 = matrix.charAt(row2 * 5 + ((col2 + 1) % 5));
            } else {
                chr1 = matrix.charAt(row1 * 5 + col2);
                chr2 = matrix.charAt(row2 * 5 + col1);
            }
            ciphertext.append(chr1).append(chr2);
        }

        return ciphertext.toString();
    }

    public String decrypt(String[] pairs, String matrix) {

        StringBuilder ciphertext = new StringBuilder();

        for (String pair : pairs) {
            byte row1 = (byte) (matrix.indexOf(pair.charAt(0)) / 5);
            byte col1 = (byte) (matrix.indexOf(pair.charAt(0)) % 5);
            byte row2 = (byte) (matrix.indexOf(pair.charAt(1)) / 5);
            byte col2 = (byte) (matrix.indexOf(pair.charAt(1)) % 5);

            char chr1;
            char chr2;
            if (col1 == col2) {
                chr2 = matrix.charAt(((row2 - 1) % 5 * 5 + col2));
                chr1 = matrix.charAt(((row1 - 1) % 5 * 5 + col1));
            } else if (row1 == row2) {
                chr1 = matrix.charAt(row1 * 5 + ((col1 - 1) % 5));
                chr2 = matrix.charAt(row2 * 5 + ((col2 - 1) % 5));
            } else {
                chr1 = matrix.charAt(row1 * 5 + col2);
                chr2 = matrix.charAt(row2 * 5 + col1);
            }
            ciphertext.append(chr1).append(chr2);
        }

        return ciphertext.toString();
    }

}