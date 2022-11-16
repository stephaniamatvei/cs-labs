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
                    Files.readAllBytes(Paths.get("/Users/stephaniamatvei/IdeaProjects/cs-labs/src/com/utm/cs/labs/lab2/blockcipher/examples/plaintext.txt")),
                    encryptionKey192bit);
            File encryptedFile = new File("/Users/stephaniamatvei/IdeaProjects/cs-labs/src/com/utm/cs/labs/lab2/blockcipher/examples/ciphertext.txt");
            Files.write(encryptedFile.toPath(), fileCiphertext);

            byte[] filePlaintext = Twofish.twofishECBDecrypt(
                    Files.readAllBytes(Paths.get("/Users/stephaniamatvei/IdeaProjects/cs-labs/src/com/utm/cs/labs/lab2/blockcipher/examples/ciphertext.txt")),
                    encryptionKey192bit);
            File decryptedFile = new File("/Users/stephaniamatvei/IdeaProjects/cs-labs/src/com/utm/cs/labs/lab2/blockcipher/examples/decrypted.txt");
            Files.write(decryptedFile.toPath(), filePlaintext);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
