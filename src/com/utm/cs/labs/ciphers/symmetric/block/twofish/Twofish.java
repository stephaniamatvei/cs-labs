package com.utm.cs.labs.ciphers.symmetric.block.twofish;

import com.utm.cs.labs.ciphers.symmetric.block.exceptions.InputSizeMismatchException;
import com.utm.cs.labs.ciphers.symmetric.block.exceptions.InvalidKeyException;
import com.utm.cs.labs.ciphers.symmetric.block.exceptions.InvalidPaddingException;

import static com.utm.cs.labs.ciphers.symmetric.block.twofish.Decryption.blockDecrypt;
import static com.utm.cs.labs.ciphers.symmetric.block.twofish.Encryption.blockEncrypt;
import static com.utm.cs.labs.ciphers.symmetric.block.twofish.KeyWrapper.makeKey;

public class Twofish {

    public static byte[] twofishECBEncrypt(byte[] plaintext, byte[] keyBytes) throws InvalidKeyException {
        byte[] plaintextBytes = Padding.padding(plaintext);
        byte[] ciphertext = new byte[0];

        Object key = makeKey(keyBytes);

        for (int i = 0; i < plaintextBytes.length; i += 16) {
            byte[] encryptedBlock = blockEncrypt(plaintextBytes, i, key);
            ciphertext = IntermediateUtilityMethods.concatenateArrays(ciphertext, encryptedBlock);
        }
        return ciphertext;
    }

    public static byte[] twofishECBEncryptNoPadding(byte[] plaintext, byte[] keyBytes) throws InvalidKeyException {
        if (plaintext.length % 16 != 0) throw new InputSizeMismatchException("Plaintext size = " + plaintext.length);

        byte[] ciphertext = new byte[0];

        Object key = makeKey(keyBytes);

        for (int i = 0; i < plaintext.length; i += 16) {
            byte[] encryptedBlock = blockEncrypt(plaintext, i, key);
            ciphertext = IntermediateUtilityMethods.concatenateArrays(ciphertext, encryptedBlock);
        }
        return ciphertext;
    }

    public static byte[] twofishECBDecrypt(byte[] ciphertextBytes, byte[] keyBytes) throws InvalidKeyException, InvalidPaddingException {
        if (ciphertextBytes.length % 16 != 0) throw new InputSizeMismatchException("Plaintext size = " + ciphertextBytes.length);


        byte[] plaintextBytes = new byte[0];

        Object key = makeKey(keyBytes);

        for (int i = 0; i < ciphertextBytes.length; i += 16) {
            byte[] decryptedBlock = blockDecrypt(ciphertextBytes, i, key);
            plaintextBytes = IntermediateUtilityMethods.concatenateArrays(plaintextBytes, decryptedBlock);
        }
        return Padding.removePadding(plaintextBytes);
    }

    public static byte[] twofishECBEncryptNoPadding(String ciphertext, String key) throws InvalidKeyException, InvalidPaddingException {
        return twofishECBEncryptNoPadding(IntermediateUtilityMethods.decodeHexString(ciphertext), IntermediateUtilityMethods.decodeHexString(key));
    }

    public static byte[] twofishECBDecryptNoPadding(byte[] ciphertextBytes, byte[] keyBytes) throws InvalidKeyException, InvalidPaddingException {
        byte[] plaintextBytes = new byte[0];

        Object key = makeKey(keyBytes);

        for (int i = 0; i < ciphertextBytes.length; i += 16) {
            byte[] decryptedBlock = blockDecrypt(ciphertextBytes, i, key);
            plaintextBytes = IntermediateUtilityMethods.concatenateArrays(plaintextBytes, decryptedBlock);
        }
        return plaintextBytes;
    }

    public static byte[] twofishECBDecryptNoPadding(byte[] ciphertextBytes, String key) throws InvalidKeyException, InvalidPaddingException {
        return twofishECBDecryptNoPadding(ciphertextBytes, IntermediateUtilityMethods.decodeHexString(key));
    }


    public static byte[] twofishECBEncrypt(String plaintext, String keyString) throws Exception {
        byte[] keyBytes = IntermediateUtilityMethods.decodeHexString(keyString);
        byte[] plaintextBytes = IntermediateUtilityMethods.decodeHexString(plaintext);
        return twofishECBEncrypt(plaintextBytes, keyBytes);
    }

    public static byte[] twofishECBEncrypt(byte[] plaintext, String keyString) throws Exception {
        byte[] keyBytes = IntermediateUtilityMethods.decodeHexString(keyString);
        return twofishECBEncrypt(plaintext, keyBytes);
    }

    public static byte[] twofishECBDecrypt(byte[] ciphertext, String keyString) throws Exception {
        byte[] keyBytes = IntermediateUtilityMethods.decodeHexString(keyString);
        return twofishECBDecrypt(ciphertext, keyBytes);
    }

}
