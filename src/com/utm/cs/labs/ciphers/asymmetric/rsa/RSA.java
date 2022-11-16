package com.utm.cs.labs.ciphers.asymmetric.rsa;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class RSA implements Cipher {
    private HashMap<String, BigInteger> publicKey;
    private HashMap<String, BigInteger> privateKey;
    private BigInteger totient;
    private BigInteger n;
    private BigInteger[] cipherText;

    public RSA(int keyBits) {
        if (keyBits >= 1024) {
            publicKey = new HashMap<>();
            privateKey = new HashMap<>();

            BigInteger p = BigInteger.probablePrime(keyBits / 2, new Random());
            BigInteger q = BigInteger.probablePrime(keyBits / 2, new Random());

            BigInteger pMinus1 = p.subtract(BigInteger.ONE);
            BigInteger qMinus1 = q.subtract(BigInteger.ONE);

            BigInteger e = BigInteger.valueOf(65_537);
            n = p.multiply(q);
            totient = pMinus1.multiply(qMinus1);

            publicKey.put("e", e);
            publicKey.put("n", n);
            privateKey.put("d", e.modInverse(totient));
            privateKey.put("n", n);
        }
    }

    @Override
    public String encrypt(String message) {
        if (this.isECoPrime()) {
            if (message.length() > 0) {
                cipherText = new BigInteger[message.length()];
                String ascii = stringToAscii(message);

                if (ascii != null) {
                    Scanner scanner = new Scanner(ascii);
                    int counter = 0;
                    while (scanner.hasNext()) {
                        int character = Integer.parseInt(scanner.next());
                        cipherText[counter] = encrypt(character);
                        counter++;
                    }
                }
            }
        }
        return message;
    }

    private BigInteger encrypt(int asciiCode) {
        BigInteger bigAscii = BigInteger.valueOf(asciiCode);
        return bigAscii.modPow(publicKey.get("e"), publicKey.get("n")); // returns c = M^e mod(n)
    }

    @Override
    public String decrypt(StringBuilder plainText) {
        if (cipherText != null) {
            BigInteger d = privateKey.get("d");
            for (BigInteger cipherValue : cipherText) {
                char character =  (char) cipherValue.modPow(d, n).intValue(); // m = c^d mod(n)
                plainText.append(character);

            }
            System.out.println(plainText);
            return plainText.toString();
        }
        return null;
    }

    public boolean isECoPrime() {
        return totient.gcd(n).equals(BigInteger.ONE);
    }

    public static String stringToAscii(String message) {
        if (message.length() > 0) {
            StringBuilder asciiFormat = new StringBuilder();
            for (int i = 0; i < message.length(); i++) {
                if (i < message.length() - 1) {
                    asciiFormat.append((int) message.charAt(i)).append(" ");
                } else {
                    asciiFormat.append((int) message.charAt(i));
                }
            }
            return asciiFormat.toString();
        }
        return null;
    }
}
