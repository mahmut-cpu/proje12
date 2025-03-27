package com.example.iste.controller;

import com.example.iste.service.KeyPairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.KeyPair;

@RestController
@RequestMapping("/api/keypair")
public class KeyPairController {

    @Autowired
    private KeyPairService keyPairService;

    @GetMapping("/generate")
    public KeyPair generateKeyPair() {
        return keyPairService.generateKeyPair();
    }
}