package com.metaphorlism.kafka_springboot.controller;

import com.metaphorlism.kafka_springboot.service.SenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/messages")
public class SenderController {
    
    @Autowired
    private SenderService senderService;
    
    @PostMapping()
    public String sendMessage(@RequestBody String message) {
        try {
            senderService.sendMessage(message);
            return "Message sent: " + message;
        }catch(Exception ex){
            return ex.getMessage();
        }
    }
}
