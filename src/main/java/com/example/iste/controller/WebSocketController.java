package com.example.iste.controller;

import com.example.iste.service.EncryptionService;
import com.example.iste.utility.KeyPairGeneratorExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

@Controller
public class WebSocketController {

    @Autowired
    private EncryptionService encryptionService;

    private KeyPair keyPair;

    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public String sendMessage(String message) throws Exception {
        if (keyPair == null) {
            keyPair = new KeyPairGeneratorExample().generateKeyPair();
        }

        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        String encryptedMessage = encryptionService.encryptMessage(message, publicKey);
        String decryptedMessage = encryptionService.decryptMessage(encryptedMessage, privateKey);

        return "Received: " + decryptedMessage;
    }
}