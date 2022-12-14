package com.utm.cs.labs.ciphers.symmetric.block.twofish;

import com.utm.cs.labs.ciphers.symmetric.block.exceptions.InvalidHexException;
import com.utm.cs.labs.ciphers.symmetric.block.exceptions.WrongNumberOfBitsException;

import java.util.Arrays;

import static com.utm.cs.labs.ciphers.symmetric.block.twofish.Constants.*;

public class IntermediateUtilityMethods {

    protected static int LSB16(int x) {
        return x & 0xFF;
    }

    protected static int MB16(int x) {
        return (x >>> 8) & 0xFF;
    }

    protected static int MSB16(int x) {
        return (x >>> 16) & 0xFF;
    }

    protected static int MSB8(int x) {
        return (x >>> 24) & 0xFF;
    }

    protected static int F32(int k64Cnt, int x, int[] k32) {
        int b0 = LSB16(x);
        int b1 = MB16(x);
        int b2 = MSB16(x);
        int b3 = MSB8(x);
        int k0 = k32[0];
        int k1 = k32[1];
        int k2 = k32[2];
        int k3 = k32[3];

        int result = 0;
        switch (k64Cnt & 3) {
            case 1:
                result = MDS[0][(P[P_01][b0] & 0xFF) ^ LSB16(k0)] ^
                                MDS[1][(P[P_11][b1] & 0xFF) ^ MB16(k0)] ^
                                MDS[2][(P[P_21][b2] & 0xFF) ^ MSB16(k0)] ^
                                MDS[3][(P[P_31][b3] & 0xFF) ^ MSB8(k0)];
                break;
            case 0:  // same as 4
                b0 = (P[P_04][b0] & 0xFF) ^ LSB16(k3);
                b1 = (P[P_14][b1] & 0xFF) ^ MB16(k3);
                b2 = (P[P_24][b2] & 0xFF) ^ MSB16(k3);
                b3 = (P[P_34][b3] & 0xFF) ^ MSB8(k3);
            case 3:
                b0 = (P[P_03][b0] & 0xFF) ^ LSB16(k2);
                b1 = (P[P_13][b1] & 0xFF) ^ MB16(k2);
                b2 = (P[P_23][b2] & 0xFF) ^ MSB16(k2);
                b3 = (P[P_33][b3] & 0xFF) ^ MSB8(k2);
            case 2:
                result =
                        MDS[0][(P[P_01][(P[P_02][b0] & 0xFF) ^ LSB16(k1)] & 0xFF) ^ LSB16(k0)] ^
                                MDS[1][(P[P_11][(P[P_12][b1] & 0xFF) ^ MB16(k1)] & 0xFF) ^ MB16(k0)] ^
                                MDS[2][(P[P_21][(P[P_22][b2] & 0xFF) ^ MSB16(k1)] & 0xFF) ^ MSB16(k0)] ^
                                MDS[3][(P[P_31][(P[P_32][b3] & 0xFF) ^ MSB8(k1)] & 0xFF) ^ MSB8(k0)];
                break;
        }
        return result;
    }

    protected static int reedSolomonEncode(int k0, int k1) {
        int r = k1;
        for (int i = 0; i < 4; i++) // shift 1 byte at a time
            r = reedSolomonRemainder(r);
        r ^= k0;
        for (int i = 0; i < 4; i++)
            r = reedSolomonRemainder(r);
        return r;
    }

    private static int reedSolomonRemainder(int x) {
        int b = (x >>> 24) & 0xFF;
        int g2 = ((b << 1) ^ ((b & 0x80) != 0 ? RS_GF_FDBK : 0)) & 0xFF;
        int g3 = (b >>> 1) ^ ((b & 0x01) != 0 ? (RS_GF_FDBK >>> 1) : 0) ^ g2;
        int result = (x << 8) ^ (g3 << 24) ^ (g2 << 16) ^ (g3 << 8) ^ b;
        return result;
    }


    private static int whichBits(int x, int N) {
        int result = switch (N % 4) {
            case 0 -> LSB16(x);
            case 1 -> MB16(x);
            case 2 -> MSB16(x);
            case 3 -> MSB8(x);
            default -> 0;
        };
        return result;
    }

    protected static int Fe32(int[] sBox, int x, int R) {
        return sBox[2 * whichBits(x, R)] ^
                sBox[2 * whichBits(x, R + 1) + 1] ^
                sBox[0x200 + 2 * whichBits(x, R + 2)] ^
                sBox[0x200 + 2 * whichBits(x, R + 3) + 1];
    }

    static byte[] decodeHexString(String hexString) throws WrongNumberOfBitsException {
        if (hexString.length() % 2 == 1) {
            throw new WrongNumberOfBitsException (
                    "Bit length not a multiple of 8. String: " + hexString);
        }

        byte[] bytes = new byte[hexString.length() / 2];
        for (int i = 0; i < hexString.length(); i += 2) {
            bytes[i / 2] = hexToByte(hexString.substring(i, i + 2));
        }
        return bytes;
    }

    private static byte hexToByte(String hexString) {
        int firstDigit = toDigit(hexString.charAt(0));
        int secondDigit = toDigit(hexString.charAt(1));
        return (byte) ((firstDigit << 4) + secondDigit);
    }

    private static int toDigit(char hexChar) {
        int digit = Character.digit(hexChar, 16);
        if (digit == -1) {
            throw new InvalidHexException(
                    "Invalid Hexadecimal Character: " + hexChar);
        }
        return digit;
    }

    static byte[] concatenateArrays(byte[] array1, byte[] array2) {
        byte[] result = Arrays.copyOf(array1, array1.length + array2.length);
        System.arraycopy(array2, 0, result, array1.length, array2.length);
        return result;
    }

}
