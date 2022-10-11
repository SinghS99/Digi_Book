package com.kafka.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;

	public void sendMsgToConsumer(String message) {
		kafkaTemplate.send("Message_producer", message);
	}
}
