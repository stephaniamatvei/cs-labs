package com.utm.cs.labs.ciphers.classical;

public class Main {

    public static void main(String[] args) {

        String plaintext = "encryption algorithm";
        String ciphertext = "oxmbizdsyx kvqybsdrw";


        // algorithm 1: Caesar -------------------------------------------------------------------------------

        Caesar caesar = new Caesar();

        StringBuilder encryptedMessage = caesar.encrypt(plaintext, 3);
        StringBuilder decryptedMessage = caesar.decrypt(ciphertext, 36);
        System.out.println("CAESAR CIPHER\nEncrypted message: " + encryptedMessage 
                + "\nDecrypted message: " + decryptedMessage);



        // algorithm 2: Caesar Permutation -------------------------------------------------------------------

        CaesarPermutation caesarPermutation = new CaesarPermutation();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        String key = caesarPermutation.getCharsPermutation();
        System.out.println("\nCAESAR PERMUTATION CIPHER\nPlaintext : " + plaintext +"\nAlphabet  : " 
                + alphabet + "\nKey       : " + key);
        String ciphertext2 = caesarPermutation.encrypt(plaintext, key);
        System.out.println("Encrypted message: " + ciphertext2);
        System.out.println("Decrypted message: " + caesarPermutation.decrypt(ciphertext2, key));



        // algorithm 3: Playfair ---------------------------------------------------------------------------

        Playfair playfair = new Playfair();
        String playfairKey = "encryption";

        String matrix = playfair.generateMatrix(playfairKey.replace(" ", ""));
        String[] pairs = playfair.divideToPairs(plaintext.replace("j", "i"));

        System.out.println("\nPLAYFAIR CIPHER\nPlaintext : " + plaintext + "\nAlphabet  : " + alphabet
                + "\nwith key  : " + playfairKey);
        System.out.print("Generated matrix :  ");
        char[] chars = matrix.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (i % 5 == 0 & i != 0) {
                System.out.println("");
            }
            System.out.print(chars[i]);
        }

        System.out.println("\nEncrypted message: " + playfair.encrypt(pairs, matrix));
        System.out.println("Recovered message: "
                + playfair.decrypt(playfair.divideToPairs(playfair.encrypt(pairs, matrix)), matrix));



        // algorithm 4: Vigenere ---------------------------------------------------------------------------

        Vigenere vigenere = new Vigenere();
        String vigenereKey = "VIGENERECIPHER";

        String message = "congratulations!";
        String encryptedMsg = vigenere.encrypt(message, vigenereKey);
        System.out.println("\nVIGENERE CIPHER\nString: " + message);
        System.out.println("Encrypted message: " + encryptedMsg);
        System.out.println("Decrypted message: " + vigenere.decrypt(encryptedMsg, vigenereKey));

    }

}
