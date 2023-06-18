package com.metaphorlism.kafka_message_receiver.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ReceiverService {
    
    @KafkaListener(topics = "${kafka-topic}")
    public void messageConsumer(String message){
        System.out.println("Message received: " + message);
    }
}
