package com.udemy.kafka.badgeproducer.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import com.udemy.kafka.badgeproducer.utils.ConstantConfig;

@Configuration
public class AutoCreateConfig {

	@Bean
	public NewTopic createNewtopic() {
		return TopicBuilder
				.name(ConstantConfig.NEW_TOPIC)
				.partitions(1)
				.replicas(1)
				.build();
	}

}
