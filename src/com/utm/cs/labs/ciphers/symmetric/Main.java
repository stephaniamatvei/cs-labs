package com.utm.cs.labs.ciphers.symmetric;

import com.utm.cs.labs.ciphers.symmetric.block.twofish.Twofish;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws Exception {
        try {
            String encryptionKey192bit = "D1079B789F666649B6BD7D1629F1F77E7AFF7A70CA2FF28A";

            byte[] fileCiphertext = Twofish.twofishECBEncrypt(
                    Files.readAllBytes(Paths.get("src/com/utm/cs/labs/ciphers/symmetric/block/examples/plaintext.txt")),
                    encryptionKey192bit);
            File encryptedFile = new File("src/com/utm/cs/labs/ciphers/symmetric/block/examples/ciphertext.txt");
            Files.write(encryptedFile.toPath(), fileCiphertext);

            byte[] filePlaintext = Twofish.twofishECBDecrypt(
                    Files.readAllBytes(Paths.get("src/com/utm/cs/labs/ciphers/symmetric/block/examples/ciphertext.txt")),
                    encryptionKey192bit);
            File decryptedFile = new File("src/com/utm/cs/labs/ciphers/symmetric/block/examples/decrypted.txt");
            Files.write(decryptedFile.toPath(), filePlaintext);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
