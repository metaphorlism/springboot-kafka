package com.metaphorlism.kafka_message_receiver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MessageReceiver {

	public static void main(String[] args) {
		SpringApplication.run(MessageReceiver.class, args);
	}

}
