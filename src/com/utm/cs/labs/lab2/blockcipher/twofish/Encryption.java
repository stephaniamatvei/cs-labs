package com.utm.cs.labs.lab2.blockcipher.twofish;

import static com.utm.cs.labs.lab2.blockcipher.twofish.Constants.ROUNDS;
import static com.utm.cs.labs.lab2.blockcipher.twofish.Constants.ROUND_SUBKEYS;

public class Encryption {

    protected static byte[] blockEncrypt(byte[] in, int inOffset, Object sessionKey) {
        Object[] sk = (Object[]) sessionKey; // extract S-box and session key
        int[] sBox = (int[]) sk[0];
        int[] sKey = (int[]) sk[1];


        int x0 = (in[inOffset++] & 0xFF) |
                (in[inOffset++] & 0xFF) << 8 |
                (in[inOffset++] & 0xFF) << 16 |
                (in[inOffset++] & 0xFF) << 24;
        int x1 = (in[inOffset++] & 0xFF) |
                (in[inOffset++] & 0xFF) << 8 |
                (in[inOffset++] & 0xFF) << 16 |
                (in[inOffset++] & 0xFF) << 24;
        int x2 = (in[inOffset++] & 0xFF) |
                (in[inOffset++] & 0xFF) << 8 |
                (in[inOffset++] & 0xFF) << 16 |
                (in[inOffset++] & 0xFF) << 24;
        int x3 = (in[inOffset++] & 0xFF) |
                (in[inOffset++] & 0xFF) << 8 |
                (in[inOffset++] & 0xFF) << 16 |
                (in[inOffset++] & 0xFF) << 24;

        x0 ^= sKey[0];
        x1 ^= sKey[1];
        x2 ^= sKey[2];
        x3 ^= sKey[3];

        int t0, t1;
        int k = ROUND_SUBKEYS;
        for (int R = 0; R < ROUNDS; R += 2) {
            t0 = IntermediateUtilityMethods.Fe32(sBox, x0, 0);
            t1 = IntermediateUtilityMethods.Fe32(sBox, x1, 3);
            x2 ^= t0 + t1 + sKey[k++];
            x2 = x2 >>> 1 | x2 << 31;
            x3 = x3 << 1 | x3 >>> 31;
            x3 ^= t0 + 2 * t1 + sKey[k++];
            t0 = IntermediateUtilityMethods.Fe32(sBox, x2, 0);
            t1 = IntermediateUtilityMethods.Fe32(sBox, x3, 3);
            x0 ^= t0 + t1 + sKey[k++];
            x0 = x0 >>> 1 | x0 << 31;
            x1 = x1 << 1 | x1 >>> 31;
            x1 ^= t0 + 2 * t1 + sKey[k++];
        }
        x2 ^= sKey[4];
        x3 ^= sKey[5];
        x0 ^= sKey[6];
        x1 ^= sKey[7];

        return new byte[]{
                (byte) x2, (byte) (x2 >>> 8), (byte) (x2 >>> 16), (byte) (x2 >>> 24),
                (byte) x3, (byte) (x3 >>> 8), (byte) (x3 >>> 16), (byte) (x3 >>> 24),
                (byte) x0, (byte) (x0 >>> 8), (byte) (x0 >>> 16), (byte) (x0 >>> 24),
                (byte) x1, (byte) (x1 >>> 8), (byte) (x1 >>> 16), (byte) (x1 >>> 24),
        };
    }

}
