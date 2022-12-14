package com.utm.cs.labs.ciphers.symmetric.tests;

import org.junit.jupiter.api.DisplayName;
import com.utm.cs.labs.ciphers.symmetric.block.twofish.Twofish;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class TwofishTestVectors {

    @Test
    @DisplayName("128 bit key")
    public void testVector1() throws Exception {
        String key = "00000000000000000000000000000000";
        String plaintext = "00000000000000000000000000000000";
        String ciphertext = "9F589F5CF6122C32B6BFEC2F2AE8C35A";
        byte[] ciphertextBytes = Twofish.twofishECBEncryptNoPadding(plaintext, key);
        assertArrayEquals(ciphertextBytes, hexStringToByteArray(ciphertext));
        assertArrayEquals(Twofish.twofishECBDecryptNoPadding(ciphertextBytes, key), hexStringToByteArray(plaintext));
    }

    @Test
    @DisplayName("128 bit key")
    public void testVector2() throws Exception {
        String key = "00000000000000000000000000000000";
        String plaintext = "9F589F5CF6122C32B6BFEC2F2AE8C35A";
        String ciphertext = "D491DB16E7B1C39E86CB086B789F5419";
        byte[] ciphertextBytes = Twofish.twofishECBEncrypt(plaintext, key);
        assertArrayEquals(removePaddingFromCiphertext(ciphertextBytes), hexStringToByteArray(ciphertext));
        assertArrayEquals(Twofish.twofishECBDecrypt(ciphertextBytes, key), hexStringToByteArray(plaintext));
    }

    @Test
    @DisplayName("128 bit key")
    public void testVector3() throws Exception {
        String key = "9F589F5CF6122C32B6BFEC2F2AE8C35A";
        String plaintext = "D491DB16E7B1C39E86CB086B789F5419";
        String ciphertext = "019F9809DE1711858FAAC3A3BA20FBC3";
        byte[] ciphertextBytes = Twofish.twofishECBEncrypt(plaintext, key);
        assertArrayEquals(removePaddingFromCiphertext(ciphertextBytes), hexStringToByteArray(ciphertext));
        assertArrayEquals(Twofish.twofishECBDecrypt(ciphertextBytes, key), hexStringToByteArray(plaintext));
    }

    @Test
    @DisplayName("192 bit key")
    public void testVector4() throws Exception {
        String key = "000000000000000000000000000000000000000000000000";
        String plaintext = "00000000000000000000000000000000";
        String ciphertext = "EFA71F788965BD4453F860178FC19101";
        byte[] ciphertextBytes = Twofish.twofishECBEncrypt(plaintext, key);
        assertArrayEquals(removePaddingFromCiphertext(ciphertextBytes), hexStringToByteArray(ciphertext));
        assertArrayEquals(Twofish.twofishECBDecrypt(ciphertextBytes, key), hexStringToByteArray(plaintext));
    }

    @Test
    @DisplayName("192 bit key")
    public void testVector5() throws Exception {
        String key = "000000000000000000000000000000000000000000000000";
        String plaintext = "EFA71F788965BD4453F860178FC19101";
        String ciphertext = "88B2B2706B105E36B446BB6D731A1E88";
        byte[] ciphertextBytes = Twofish.twofishECBEncrypt(plaintext, key);
        assertArrayEquals(removePaddingFromCiphertext(ciphertextBytes), hexStringToByteArray(ciphertext));
        assertArrayEquals(Twofish.twofishECBDecrypt(ciphertextBytes, key), hexStringToByteArray(plaintext));
    }

    @Test
    @DisplayName("192 bit key")
    public void testVector6() throws Exception {
        String key = "EFA71F788965BD4453F860178FC191010000000000000000";
        String plaintext = "88B2B2706B105E36B446BB6D731A1E88";
        String ciphertext = "39DA69D6BA4997D585B6DC073CA341B2";
        byte[] ciphertextBytes = Twofish.twofishECBEncrypt(plaintext, key);
        assertArrayEquals(removePaddingFromCiphertext(ciphertextBytes), hexStringToByteArray(ciphertext));
        assertArrayEquals(Twofish.twofishECBDecrypt(ciphertextBytes, key), hexStringToByteArray(plaintext));
    }

    @Test
    @DisplayName("192 bit key")
    public void testVector7() throws Exception {
        String key = "88B2B2706B105E36B446BB6D731A1E88EFA71F788965BD44";
        String plaintext = "39DA69D6BA4997D585B6DC073CA341B2";
        String ciphertext = "182B02D81497EA45F9DAACDC29193A65";
        byte[] ciphertextBytes = Twofish.twofishECBEncrypt(plaintext, key);
        assertArrayEquals(removePaddingFromCiphertext(ciphertextBytes), hexStringToByteArray(ciphertext));
        assertArrayEquals(Twofish.twofishECBDecrypt(ciphertextBytes, key), hexStringToByteArray(plaintext));
    }

    @Test
    @DisplayName("256 bit key")
    public void testVector8() throws Exception {
        String key = "0000000000000000000000000000000000000000000000000000000000000000";
        String plaintext = "00000000000000000000000000000000";
        String ciphertext = "57FF739D4DC92C1BD7FC01700CC8216F";
        byte[] ciphertextBytes = Twofish.twofishECBEncrypt(plaintext, key);
        assertArrayEquals(removePaddingFromCiphertext(ciphertextBytes), hexStringToByteArray(ciphertext));
        assertArrayEquals(Twofish.twofishECBDecrypt(ciphertextBytes, key), hexStringToByteArray(plaintext));
    }

    @Test
    @DisplayName("256 bit key")
    public void testVector9() throws Exception {
        String key = "0000000000000000000000000000000000000000000000000000000000000000";
        String plaintext = "57FF739D4DC92C1BD7FC01700CC8216F";
        String ciphertext = "D43BB7556EA32E46F2A282B7D45B4E0D";
        byte[] ciphertextBytes = Twofish.twofishECBEncrypt(plaintext, key);
        assertArrayEquals(removePaddingFromCiphertext(ciphertextBytes), hexStringToByteArray(ciphertext));
        assertArrayEquals(Twofish.twofishECBDecrypt(ciphertextBytes, key), hexStringToByteArray(plaintext));
    }

    @Test
    @DisplayName("256 bit key")
    public void testVector10() throws Exception {
        String key = "57FF739D4DC92C1BD7FC01700CC8216F00000000000000000000000000000000";
        String plaintext = "D43BB7556EA32E46F2A282B7D45B4E0D";
        String ciphertext = "90AFE91BB288544F2C32DC239B2635E6";
        byte[] ciphertextBytes = Twofish.twofishECBEncrypt(plaintext, key);
        assertArrayEquals(removePaddingFromCiphertext(ciphertextBytes), hexStringToByteArray(ciphertext));
        assertArrayEquals(Twofish.twofishECBDecrypt(ciphertextBytes, key), hexStringToByteArray(plaintext));
    }


    @Test
    @DisplayName("256 bit key")
    public void testVector11() throws Exception {
        String key = "D43BB7556EA32E46F2A282B7D45B4E0D57FF739D4DC92C1BD7FC01700CC8216F";
        String plaintext = "90AFE91BB288544F2C32DC239B2635E6";
        String ciphertext = "6CB4561C40BF0A9705931CB6D408E7FA";
        byte[] ciphertextBytes = Twofish.twofishECBEncrypt(plaintext, key);
        assertArrayEquals(removePaddingFromCiphertext(ciphertextBytes), hexStringToByteArray(ciphertext));
        assertArrayEquals(Twofish.twofishECBDecrypt(ciphertextBytes, key), hexStringToByteArray(plaintext));
    }

    private byte[] removePaddingFromCiphertext(byte[] paddedCiphertext) {
        byte[] ciphertextWithoutPadding = new byte[16];
        for (int i = 0; i < 16; i++) {
            ciphertextWithoutPadding[i] = paddedCiphertext[paddedCiphertext.length - 16 + i];
        }
        return ciphertextWithoutPadding;
    }

    private byte[] hexStringToByteArray(String hexString) {
        if (hexString.length() % 2 == 1) {
            throw new IllegalArgumentException(
                    "Invalid hexadecimal String supplied.");
        }

        byte[] bytes = new byte[hexString.length() / 2];
        for (int i = 0; i < hexString.length(); i += 2) {
            bytes[i / 2] = hexToByte(hexString.substring(i, i + 2));
        }
        return bytes;
    }

    private byte hexToByte(String hexString) {
        int firstDigit = hexCharToInt(hexString.charAt(0));
        int secondDigit = hexCharToInt(hexString.charAt(1));
        return (byte) ((firstDigit << 4) + secondDigit);
    }

    private int hexCharToInt(char hexChar) {
        int digit = Character.digit(hexChar, 16);
        if (digit == -1) {
            throw new IllegalArgumentException(
                    "Invalid Hexadecimal Character: " + hexChar);
        }
        return digit;
    }

}
