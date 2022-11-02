# Lab Nr.2 - Symmetric Ciphers. Stream Ciphers. Block Ciphers.

## Table of Contents

* [Theory](#theory)
* [Objectives](#objectives)
* [Implementation Description](#implementation-description)
    * [1. Rabbit Stream Cipher](#1-rabbit-stream-cipher)
    * [2. Twofish](#2-twofish)
* [Conclusions](#conclusions)

## Theory

&ensp;&ensp;&ensp; Symmetric Cryptography deals with the encryption of plain text when having only one encryption key which needs to remain private. Based on the way the plain text is processed/encrypted there are 2 types of ciphers:
- Stream ciphers:
    - The encryption is done one byte at a time.
    - Stream ciphers use confusion to hide the plain text.
    - Make use of substitution techniques to modify the plain text.
    - The implementation is fairly complex.
    - The execution is fast.
- Block ciphers:
    - The encryption is done one block of plain text at a time.
    - Block ciphers use confusion and diffusion to hide the plain text.
    - Make use of transposition techniques to modify the plain text.
    - The implementation is simpler relative to the stream ciphers.
    - The execution is slow compared to the stream ciphers.

&ensp;&ensp;&ensp; Some examples of stream ciphers are the following:
- Grain: ...
- HC-256: ...
- PANAMA: ...
- Rabbit: ...
- Rivest Cipher (RC4): It uses 64 or 128-bit long keys. It is used in TLS/SSL and IEEE 802.11 WLAN.
- Salsa20: ...
- Software-optimized Encryption Algorithm (SEAL): ...
- Scream: ...

&ensp;&ensp;&ensp; The block ciphers may differ in the block size which is a parameter that might be implementation specific. Here are some examples of such ciphers:
- 3DES
- Advanced Encryption Standard (AES): A cipher with 128-bit block length which uses 128, 192 or 256-bit symmetric key.
- Blowfish: ...
- Data Encryption Standard (DES): A 56-bit symmetric key cipher.
- Serpent: ...
- Twofish: A standard that uses Feistel networks. It uses blocks of 128 bits with key sizes from 128-bit to 256-bit.


## Objectives:

1. Get familiar with the symmetric cryptography, stream and block ciphers.

2. Implement an example of a stream cipher.

3. Implement an example of a block cipher.

4. The implementation should, ideally follow the abstraction/contract/interface used in the previous laboratory work.

5. Please use packages/directories to logically split the files that you will have.

6. As in the previous task, please use a client class or test classes to showcase the execution of your programs.


## Implementation Description

### 1. Rabbit Stream Cipher
&ensp;&ensp;&ensp; Rabbit is a stream cipher algorithm that has been designed for high performance in software implementations. Both key setup and encryption are very fast, making the algorithm particularly suited for all applications where large amounts of data or large numbers of data packages have to be encrypted.



### 2. Twofish
&ensp;&ensp;&ensp; In cryptography, Twofish is a symmetric key block cipher with a block size of 128 bits and key sizes up to 256 bits. It was one of the five finalists of the Advanced Encryption Standard contest, but it was not selected for standardization. Twofish is related to the earlier block cipher Blowfish.

&ensp;&ensp;&ensp; Twofish's distinctive features are the use of pre-computed key-dependent S-boxes, and a relatively complex key schedule. One half of an n-bit key is used as the actual encryption key and the other half of the n-bit key is used to modify the encryption algorithm (key-dependent S-boxes). Twofish borrows some elements from other designs; for example, the pseudo-Hadamard transform[3] (PHT) from the SAFER family of ciphers. Twofish has a Feistel structure like DES. Twofish also employs a Maximum Distance Separable matrix.


## Conclusions
&ensp;&ensp;&ensp; To summarize, in the second laboratory work for Cryptography & Security course, I have analyzed and implemented 2 symmetric ciphers: 1 stream cipher (Rabbit) and 1 block cipher (Twofish).

&ensp;&ensp;&ensp; In cryptography, a block cipher is a deterministic algorithm operating on fixed-length groups of bits, called blocks. They are specified elementary components in the design of many cryptographic protocols and are widely used to encrypt large amounts of data, including in data exchange protocols. It uses blocks as an unvarying transformation.
