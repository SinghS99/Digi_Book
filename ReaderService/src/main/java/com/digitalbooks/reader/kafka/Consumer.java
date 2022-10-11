package com.digitalbooks.reader.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

	@KafkaListener(topics = "Message_Author", groupId = "Block_Book")
	public void listenToTopic(String recivedMessage) {
		System.out.println(recivedMessage);
	}
}
