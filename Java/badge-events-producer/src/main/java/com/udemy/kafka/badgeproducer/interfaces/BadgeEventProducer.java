package com.udemy.kafka.badgeproducer.interfaces;

import org.springframework.kafka.support.SendResult;

import com.udemy.kafka.badgeproducer.model.BadgeEvent;

public interface BadgeEventProducer {

	public void sendAsyncMessage(BadgeEvent badgeEvent);
	public SendResult<Integer, String> sendSyncMessage(BadgeEvent badgeEvent);
}
