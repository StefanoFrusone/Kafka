package com.udemy.kafka.badgeproducer.services;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.udemy.kafka.badgeproducer.interfaces.BadgeEventProducer;
import com.udemy.kafka.badgeproducer.model.BadgeEvent;
import com.udemy.kafka.badgeproducer.utils.Handler;

@Service
public class BadgeEventProducerImpl implements BadgeEventProducer {

	@Autowired
	KafkaTemplate<Integer, String> kafkaTemplate;
	
	@Override
	public void sendAsyncMessage(BadgeEvent badgeEvent) {

		// Send Default message to a predifined topic in application.yml
		kafkaTemplate.sendDefault(badgeEvent.getId(), badgeEvent.toString())
			.addCallback(new ListenableFutureCallback<org.springframework.kafka.support.SendResult<Integer, String>>() {

				@Override
				public void onSuccess(SendResult<Integer, String> result) {
					Handler.successKafkaMessage(badgeEvent.getId(), badgeEvent.toString(), result);
					
				}

				@Override
				public void onFailure(Throwable ex) {
					Handler.failureKafkaMessage(ex);
				}
			});

	}

	@Override
	public SendResult<Integer, String> sendSyncMessage(BadgeEvent badgeEvent) {
		SendResult<Integer, String> result = null;
		try {
			result = kafkaTemplate.sendDefault(badgeEvent.getId(), badgeEvent.toString()).get(1, TimeUnit.SECONDS);
		} catch ( ExecutionException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}

}
