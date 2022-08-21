package com.udemy.kafka.badgeproducer.utils;

import org.springframework.kafka.support.SendResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Handler {
	
	public static void successKafkaMessage(Integer key, String value, SendResult<Integer, String> result) {
		log.info("Message Sent Successfully for the key: {}, the value is {} and partition is {}", key, value, result.getRecordMetadata().partition());
	}
	
	public static void failureKafkaMessage(Throwable exception) {
		log.info("Error sending the message and the exception is {}", exception.getMessage());
	}

}
