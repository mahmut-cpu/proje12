package com.example.iste.service;

import com.example.iste.utility.KeyPairGeneratorExample;
import org.springframework.stereotype.Service;

import java.security.KeyPair;

@Service
public class KeyPairService {

    public KeyPair generateKeyPair() {
        return KeyPairGeneratorExample.generateKeyPair();
    }
}