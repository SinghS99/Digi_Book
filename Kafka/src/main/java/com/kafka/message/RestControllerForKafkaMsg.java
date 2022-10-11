package com.kafka.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.services.Producer;

@RestController
@RequestMapping("/rest/api")
public class RestControllerForKafkaMsg {

	@Autowired
	private Producer producer;

	@GetMapping("/producermsg")
	public void getMessageFromClient(@RequestParam("message") String message) {
		producer.sendMsgToConsumer(message);
	}
}
