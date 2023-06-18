---
title: "Real time communication of microservices with Apache Kafka"
disqus: JBeanny
---

By: Yim Sotharoth

docs: https://hackmd.io/@JBeanny/ry7hkV2v3

Real time communication of microservices with Apache Kafka
<img
    src="https://hackmd.io/_uploads/rkxXe43wn.png"   
    alt="kafka-png"
    width="30"
/>
===

## Table of Contents

[TOC]

## Installation

:::info
Go to https://start.spring.io/ to create the springboot project

The dependencies in used for this project:

```xml!
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.kafka</groupId>
        <artifactId>spring-kafka</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.kafka</groupId>
        <artifactId>spring-kafka-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

**_Note :_** I create 2 of this springboot project as : MessageSender and MessageReceiver
:::

:::warning
Before Getting started, make sure you have the kafka server up and running.
If not, then you can go the our repository to grab the `docker-compose.yaml`
file and then run the following command:

```bash!
docker compose up -d
```

After that take a look at the `kafka_command.md` and execute the create topic command to create the Kafka topic.

[Project Repository](#Demo1)
:::

## First we are going to build the message sender service

### Configuration in application.yaml

```yaml!
server:
  port: 80

spring:
  kafka:
    bootstrap-servers: http://localhost:9092
    producer:
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      value-serializer: org.apache.kafka.common.serialization.StringSerializer

kafka-topic: messages-topic
```

### Create the Sender service

> Inside `service` folder I created a file called `SenderService.java` with the following code:

```java!
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
```

### Create the controller for our message sender serivce

> Inside `controller` folder I created a file called `SenderController.java` with the following code:

```java!
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
```

**_after implementing all of this , you will be able to send the message to the `messages-topic` topic. But we want another service to work as a message consumer from the `messages-topic` and that service is going to be named as `message-receiver`_**

## Now we are going to build the message receiver service

### Configuration in application.yaml

```yaml!
server:
  port: 8081

spring:
  kafka:
    bootstrap-servers: http://localhost:9092
    consumer:
      group-id: message-group-id
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      value-serializer: org.apache.kafka.common.serialization.StringSerializer

kafka-topic: messages-topic
```

### Create the Receiver service

> Inside `service` folder I created a file called `RecieverService.java` with the following code:

```java!
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
```

:::success
After implementing both of the services, we will be able to see the magic of Apache Kafka with Springboot 😉😉
:::

## Demo

Sending the request via Postman

![](https://hackmd.io/_uploads/rJQMw43wh.png)

This is what I get in my MessageReceiver service

![](https://hackmd.io/_uploads/S1lNv42wn.png)

:::success
Okay, so after all of this you get the idea of what Apache Kafka can do and how to implement it. Goodluck with your Kafka project 😁
:::

Project Repository: https://github.com/metaphorlism/springboot-kafka

## Contact Us

- :mailbox: yimsotharoth999@gmail.com
- [GitHub](https://github.com/metaphorlism)
- [Facebook Page](https://www.facebook.com/Metaphorlism)
- [Instagram: Metaphorlism](https://www.instagram.com/metaphorlism/)
