package com.utm.cs.labs.lab2.blockcipher.twofish;

import com.utm.cs.labs.lab2.blockcipher.exceptions.InvalidPaddingException;

import java.util.Random;

import static com.utm.cs.labs.lab2.blockcipher.twofish.Constants.PADDING_BLOCK1;
import static com.utm.cs.labs.lab2.blockcipher.twofish.Constants.PADDING_BLOCK2;

public class Padding {

    static byte[] padding(byte[] plaintextBytes) {
        if (plaintextBytes.length % 16 == 0) {
            return IntermediateUtilityMethods.concatenateArrays(PADDING_BLOCK1, IntermediateUtilityMethods.concatenateArrays(PADDING_BLOCK2, plaintextBytes));
        } else {
            int paddingLength = 16 - plaintextBytes.length % 16;
            byte[] padding = new byte[paddingLength];
            padding[paddingLength - 1] = (byte) 1;
            for (int i = 0; i < padding.length - 1; i++) {
                Random random = new Random();
                padding[i] = (byte) (random.nextInt(61) + 2);
            }
            byte[] output;
            output = IntermediateUtilityMethods.concatenateArrays(PADDING_BLOCK1, padding);
            output = IntermediateUtilityMethods.concatenateArrays(output, plaintextBytes);
            return output;
        }
    }

    static byte[] removePadding(byte[] paddedText) throws InvalidPaddingException {
        int paddingBytes = 0;
        if (paddedText[0] == (byte) 128) {
            paddingBytes++;
            while (paddedText[paddingBytes] != (byte) 1) {
                paddingBytes++;
                if (paddingBytes > 32) {
                    throw new InvalidPaddingException("Too many padding bytes for the data to be correct.");
                }
            }
            if (paddedText[paddingBytes] == (byte) 1) {
                paddingBytes++;
                byte[] plaintextWithoutPadding = new byte[paddedText.length - paddingBytes];
                if (paddedText.length - paddingBytes >= 0)
                    System.arraycopy(paddedText, paddingBytes, plaintextWithoutPadding, 0, paddedText.length - paddingBytes);
                return plaintextWithoutPadding;
            } else {
                throw new InvalidPaddingException("It appears that some padding bytes are missing.");
            }
        } else {
            throw new InvalidPaddingException("Padding appears to have already been removed.");
        }
    }

}
