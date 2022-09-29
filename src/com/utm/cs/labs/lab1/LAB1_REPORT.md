# Lab Nr.1 - Intro to Cryptography. Classical ciphers. Caesar cipher.

### Course: Cryptography & Security
### Author: Stephania Matvei

---

## Table of Contents

* [Theory](#theory)
* [Objectives](#objectives)
* [Implementation Description](#implementation-description)
  * [1. Caesar Cipher](#1.-caesar-cipher)
  * [2. Caesar with Permutation Cipher](#2.-caesar-with-permutation-cipher)
  * [3. Playfair Cipher](#3.-playfair-cipher)
  * [4. Vigenere Cipher](#4-vigenere-cipher)
* [Conclusions](#conclusions)

## Theory
&ensp;&ensp;&ensp; Cryptography consists a part of the science known as Cryptology. The other part is Cryptanalysis. There are a lot of different algorithms/mechanisms used in Cryptography, but in the scope of these laboratory works the students need to get familiar with some examples of each kind.

&ensp;&ensp;&ensp; First, it is important to understand the basics so for the first task students will need to implement a classical and relatively simple cipher. This would be the Caesar cipher which uses substitution to encrypt a message.

&ensp;&ensp;&ensp; In it's simplest form, the cipher has a key which is used to substitute the characters with the next ones, by the order number in a pre-established alphabet. Mathematically it would be expressed as follows:

$em = enc_{k}(x) = x + k (mod \; n),$

$dm = dec_{k}(x) = x + k (mod \; n),$

where:
- em: the encrypted message,
- dm: the decrypted message (i.e. the original one),
- x: input,
- k: key,
- n: size of the alphabet.

&ensp;&ensp;&ensp; Judging by the encryption mechanism one can conclude that this cipher is pretty easy to break. In fact, a brute force attack would have __*O(nm)*__ complexity, where __*n*__ would be the size of the alphabet and __*m*__ the size of the message. This is why there were other variations of this cipher, which are supposed to make the cryptanalysis more complex.

## Objectives:
1. Get familiar with the basics of cryptography and classical ciphers.

2. Implement 4 types of the classical ciphers:
    - Caesar cipher with one key used for substitution (as explained above),
    - Caesar cipher with one key used for substitution, and a permutation of the alphabet,
    - Vigenere cipher,
    - Playfair cipher.

## Implementation Description

### 1. Caesar Cipher
&ensp;&ensp;&ensp; It is an old encryption technique developed by Julius Ceasar for hiding the content of a message by substituting each letter in a message with a letter existing three or more places down or up in the same alphabet.

#### Encryption:

```
public StringBuilder encrypt(String message, int offset) {
        StringBuilder result = new StringBuilder();

        for (char character : message.toCharArray()) {
            if (character != ' ') {
                int originalAlphabetPosition = character - 'a';
                int newAlphabetPosition = (originalAlphabetPosition + offset) % 26;
                char newCharacter = (char) ('a' + newAlphabetPosition);
                result.append(newCharacter);
            } else {
                result.append(character);
            }
        }
        return result;
}
```

#### Decryption:

```
public StringBuilder decrypt(String message, int offset) {
        return encrypt(message, 26 - (offset % 26));
}
```


### 2. Caesar with Permutation Cipher
&ensp;&ensp;&ensp; Permutation is an ordered sequence of all elements in a finite set of element S, with each element appears exactly once. For example, if S={1,2,3}, there are 3! = 6 permutations 123, 132, 213, 231, 312, 321.
Suppose that S is the english alphabet, so there are 26! permutations. These permutations can be an arbitrary substitutions providing more security rather than Ceasar cipher which has only 25 keys. Encryption is done by maping each letter in the message with correspondent letters in the key and decryption is done by reversing operation.

#### Encryption: 

```
public String encrypt(String plaintext, String key) {
        StringBuilder ciphertext = new StringBuilder();

        for (char chr : plaintext.toLowerCase().toCharArray()) {
            byte position = (byte) alphabet.indexOf(chr);
            if (chr == ' ') {
                ciphertext.append(" ");
            } else {
                ciphertext.append(key.charAt(position));
            }
        }

        return ciphertext.toString();
}
```

#### Decryption:

```
public String decrypt(String ciphertext, String key) {
        StringBuilder plaintext = new StringBuilder();

        for (char chr : ciphertext.toLowerCase().toCharArray()) {
            byte position = (byte) key.indexOf(chr);
            if (chr == ' ') {
                plaintext.append(" ");
            } else {
                plaintext.append(alphabet.charAt(position));
            }
        }

        return plaintext.toString();
}
```

### 3. Playfair Cipher
&ensp;&ensp;&ensp; The Playfair algorithm is based on the use of a 5 * 5 matrix of letters constructed using a keyword. This matrix is filled by keyword letters from left to right and then filling the reminders places in the matrix with the remaining letters in alphabetic order. Letters I and J are considered one letter.

#### Rules for encryption:
1. Message is divided into pairs of letters, if a pair has same letter, separate between then by x, such as tree {tr, ee} >>{tr, ex, ex}.
2. Two plaintext letters that fall in the same row of the matrix are each replaced by the letter to the right, with the first element of the row circularly following the last. For example, ry is encrypted as ye.
3. Two plaintext letters that fall in the same column are each replaced by the letter beneath, with the top element of the column circularly following the last. For example, eu is encrypted as pe.
4. Otherwise, each plaintext letter in a pair is replaced by the letter that lies in its own row and the column occupied by the other plaintext letter. Such as pl >> tk.

```
public String encrypt(String[] pairs, String matrix) {

        StringBuilder ciphertext = new StringBuilder();

        for (String pair : pairs) {//using zero index
            byte row1 = (byte) (matrix.indexOf(pair.charAt(0)) / 5);
            byte col1 = (byte) (matrix.indexOf(pair.charAt(0)) % 5);
            byte row2 = (byte) (matrix.indexOf(pair.charAt(1)) / 5);
            byte col2 = (byte) (matrix.indexOf(pair.charAt(1)) % 5);

            char chr1;
            char chr2;
            if (col1 == col2) {
                chr2 = matrix.charAt(((row2 + 1) % 5 * 5 + col2));
                chr1 = matrix.charAt(((row1 + 1) % 5 * 5 + col1));
            } else if (row1 == row2) {
                chr1 = matrix.charAt(row1 * 5 + ((col1 + 1) % 5));
                chr2 = matrix.charAt(row2 * 5 + ((col2 + 1) % 5));
            } else {
                chr1 = matrix.charAt(row1 * 5 + col2);
                chr2 = matrix.charAt(row2 * 5 + col1);
            }
            ciphertext.append(chr1).append(chr2);
        }

        return ciphertext.toString();
}
```

#### Rules for decryption:
1. Ciphertext is divided into pairs of letters.
2. Two ciphertext letters that fall in the same row of the matrix are each replaced by the letter to the left, with the first element of the row circularly following the last. For example, ye is encrypted as ry.
3. Two ciphertext letters that fall in the same column are each replaced by the letter above, with the top element of the column circularly following the last. For example, pe is encrypted as eu.
4. Otherwise, each ciphertext letter in a pair is replaced by the letter that lies in its own row and the column occupied by the other ciphertext letter. Such as tk >> pl.

```
public String decrypt(String[] pairs, String matrix) {

        StringBuilder ciphertext = new StringBuilder();

        for (String pair : pairs) {
            byte row1 = (byte) (matrix.indexOf(pair.charAt(0)) / 5);
            byte col1 = (byte) (matrix.indexOf(pair.charAt(0)) % 5);
            byte row2 = (byte) (matrix.indexOf(pair.charAt(1)) / 5);
            byte col2 = (byte) (matrix.indexOf(pair.charAt(1)) % 5);

            char chr1;
            char chr2;
            if (col1 == col2) {
                chr2 = matrix.charAt(((row2 - 1) % 5 * 5 + col2));
                chr1 = matrix.charAt(((row1 - 1) % 5 * 5 + col1));
            } else if (row1 == row2) {
                chr1 = matrix.charAt(row1 * 5 + ((col1 - 1) % 5));
                chr2 = matrix.charAt(row2 * 5 + ((col2 - 1) % 5));
            } else {
                chr1 = matrix.charAt(row1 * 5 + col2);
                chr2 = matrix.charAt(row2 * 5 + col1);
            }
            ciphertext.append(chr1).append(chr2);
        }

        return ciphertext.toString();
}

```

### 4. Vigenere Cipher
&ensp;&ensp;&ensp; Vigenere Cipher is a method of encrypting alphabetic text. It uses a simple form of polyalphabetic substitution. A polyalphabetic cipher is any cipher based on substitution, using multiple substitution alphabets. The encryption of the original text is done using the Vigenère square or Vigenère table.

- The table consists of the alphabets written out 26 times in different rows, each alphabet shifted cyclically to the left compared to the previous alphabet, corresponding to the 26 possible Caesar Ciphers.
- At different points in the encryption process, the cipher uses a different alphabet from one of the rows.
- The alphabet used at each point depends on a repeating keyword.

#### Encryption:

```
public String encrypt(String message, final String key) {
        StringBuilder res = new StringBuilder();
        message = message.toUpperCase();
        for (int i = 0, j = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            if (c < 'A' || c > 'Z')
                continue;
            res.append((char) ((c + key.charAt(j) - 2 * 'A') % 26 + 'A'));
            j = ++j % key.length();
        }
        return res.toString();
}
```

#### Decryption:

```
public String decrypt(String message, final String key) {
        StringBuilder res = new StringBuilder();
        message = message.toUpperCase();
        for (int i = 0, j = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            if (c < 'A' || c > 'Z')
                continue;
            res.append((char) ((c - key.charAt(j) + 26) % 26 + 'A'));
            j = ++j % key.length();
        }
        return res.toString();
}
```

## Conclusions
&ensp;&ensp;&ensp; To summarize, in the first laboratory work for Cryptography & Security course, I have analyzed and implemented 4 classical ciphers: Caesar, Caesar with permutation, Playfair and Vigenere.

&ensp;&ensp;&ensp; Classical ciphers were used historically but for the most part, have fallen into disuse. In contrast to modern cryptographic algorithms, most classical ciphers can be practically computed and solved by hand. However, they are also usually very simple to break with modern technology.
