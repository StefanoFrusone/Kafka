package com.udemy.kafka.badgeproducer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BadgeEvent {
	
	private Integer id;
	private Badge badge;
	
	@Override
	public String toString() {
		return "BadgeEvent: [id=" + id + ", badge= " + badge + "]";
	}

}
