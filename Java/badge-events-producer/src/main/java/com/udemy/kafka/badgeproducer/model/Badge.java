package com.udemy.kafka.badgeproducer.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Badge {

	private Integer id;
	private String number;
	private String patientName;
	private String patientSurname;
	private String patientCF;
	
	@Override
	public String toString() {
		return "[id=" + id + ", number=" + number + ", patientName=" + patientName + ", patientSurname="
				+ patientSurname + ", patientCF=" + patientCF + "]";
	}
	
	
}
