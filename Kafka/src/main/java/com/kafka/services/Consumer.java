package com.kafka.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

	@KafkaListener(topics = "Message_producer", groupId = "message_group")
	public void listenToTopic(String recivedMessage) {
		System.out.println("This message form producer" + recivedMessage);
	}
}
