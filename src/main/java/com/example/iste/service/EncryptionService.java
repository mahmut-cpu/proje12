package com.example.iste.service;

import com.example.iste.utility.EncryptionUtil;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

@Service
public class EncryptionService {

    public String encryptMessage(String message, PublicKey publicKey) throws Exception {
        return EncryptionUtil.encrypt(message, publicKey);
    }

    public String decryptMessage(String encryptedMessage, PrivateKey privateKey) throws Exception {
        return EncryptionUtil.decrypt(encryptedMessage, privateKey);
    }
}