package com.udemy.kafka.badgeproducer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.kafka.badgeproducer.model.BadgeEvent;
import com.udemy.kafka.badgeproducer.services.BadgeEventProducerImpl;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BadgeEventsController {
	
	@Autowired
	BadgeEventProducerImpl badgeEventProducer;

	@PostMapping("/v1/badgeEvent/newBadgeAsync")
	public ResponseEntity<BadgeEvent> postBadgeEventAsync(@RequestBody(required = true) BadgeEvent badgeEvent){
		
		//invoke kafka producer service
		badgeEventProducer.sendAsyncMessage(badgeEvent);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(badgeEvent);
	}
	
	@PostMapping("/v1/badgeEvent/newBadgeSync")
	public ResponseEntity<BadgeEvent> postBadgeEventSync(@RequestBody(required = true) BadgeEvent badgeEvent){
		
		//invoke kafka producer service
		SendResult<Integer, String> result = badgeEventProducer.sendSyncMessage(badgeEvent);
		if(!result.equals(null))
			log.info("Message Send Successfully with value: {}", result.getProducerRecord().value());
		
		return !result.equals(null) ? ResponseEntity.status(HttpStatus.CREATED).body(badgeEvent) : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
}
