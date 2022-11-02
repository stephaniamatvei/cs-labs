package com.utm.cs.labs.lab3.test;

import com.utm.cs.labs.lab3.rsa.RSA;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static com.utm.cs.labs.lab3.rsa.RSA.stringToAscii;

public class RSATest {
    @Test
    public void decrypt() {
        String test1 = "This is laboratory work nr 3 about asymmetric ciphers";
        String test2 = "This is an ascii test.";
        String test3 = "3.14ZQxY'£*&()^%$!-+][{};:'#?,<>.|/";

        RSA rsa = new RSA(1024);

        rsa.encrypt(test1);
        assertEquals(rsa.decrypt(), test1);
        rsa.encrypt(test2);
        assertEquals(rsa.decrypt(), test2);
        rsa.encrypt(test3);
        assertEquals(rsa.decrypt(), test3);
    }

    @Test
    public void stringToAsciiTest() {
        String test1 = "This is laboratory work nr 3 about asymmetric ciphers";
        String test2 = "This is an ascii test test.";
        String test3 = "3.14ZQxY'£*&()^%$!-+][{};:'#?,<>.|/";

        String test1Expected = "84 104 105 115 32 105 115 32 108 97 98 111 114 97 116 " +
                "111 114 121 32 119 111 114 107 32 110 114 32 51 32 97 98 111 117 116 " +
                "32 97 115 121 109 109 101 116 114 105 99 32 99 105 112 104 101 114 115";
        String test2Expected = "84 104 105 115 32 105 115 32 97 110 32 97 115 " +
                "99 105 105 32 116 101 115 116 32 116 101 115 116 46";
        String test3Expected = "51 46 49 52 90 81 120 89 39 163 42 38 40 41 94 37 " +
                "36 33 45 43 93 91 123 125 59 58 39 35 63 44 60 62 46 124 47";

        assertEquals(stringToAscii(test1), test1Expected);
        assertEquals(stringToAscii(test2), test2Expected);
        assertEquals(stringToAscii(test3), test3Expected);
    }

    @Test
    public void isECoPrime() {
        RSA rsa = new RSA(1024);
        assertTrue(rsa.isECoPrime());
    }
}
