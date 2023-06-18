package com.metaphorlism.kafka_springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SenderService {
    
    private final KafkaTemplate<String, String> kafkaTemplate;
    
    @Value("${kafka-topic}")
    private String topicName;
    
    @Autowired
    public SenderService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    
    public void sendMessage(String message) {
        kafkaTemplate.send(topicName, message);
        System.out.println("Message sent to Kafka: " + message);
    }
}