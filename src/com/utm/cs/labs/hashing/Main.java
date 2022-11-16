package com.utm.cs.labs.hashing;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import static com.utm.cs.labs.hashing.DigitalSignature.*;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter the message to get the hash code of it generated by SHA-256:");

            String message = scanner.nextLine();

            String messageDigest = toHexString(getSHA(message));
            String encryptedMessageDigest = encryptMessageDigest(messageDigest);
            String decryptedMessageDigest = decryptMessageDigest(new StringBuilder(encryptedMessageDigest));

            System.out.println("\n1. Hash code for " + message + ": " + messageDigest);
            System.out.println("2. Encrypting the message digest (hash code) using RSA implementation from lab 3 ...");
            System.out.println("3. Decrypted message digest using RSA implementation from lab 3: "
                    + decryptedMessageDigest);

            digitalSignatureCheck(messageDigest, decryptedMessageDigest);
        }
        catch (NoSuchAlgorithmException e) {
            System.out.println("Exception thrown for incorrect algorithm: " + e);
        }
    }
}