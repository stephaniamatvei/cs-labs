package com.utm.cs.labs.hashing;

import com.utm.cs.labs.ciphers.asymmetric.rsa.RSA;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class DigitalSignature {

    public static byte[] getSHA(String input) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        return messageDigest.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String toHexString(byte[] hash) {
        // convert byte array into sign representation
        BigInteger number = new BigInteger(1, hash);

        // convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // pad with leading zeros
        while (hexString.length() < 64) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }

//    public static String encryptMessageDigest(String messageDigest) {
//        RSA rsa = new RSA(1024);
//        return rsa.encrypt(messageDigest);
//    }

    public static String decryptMessageDigest(String messageDigest) {
        RSA rsa = new RSA(1024);
        String encryptedMessageDigest = rsa.encrypt(messageDigest);
        return rsa.decrypt(new StringBuilder(encryptedMessageDigest));
    }

    public static void digitalSignatureCheck(String messageDigest, String decryptedMessage) {
        if (Objects.equals(messageDigest, decryptedMessage)) {
            System.out.println("\nDigital signature check finished with success.");
        } else {
            System.out.println("\nDigital signature check failed. " +
                    "The hash of the message and the decrypted one aren't equal.");
        }
    }

}