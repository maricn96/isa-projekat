package com.ftn.isa.projekat.hotel.hotelApi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DodatneUslugeDTO {
	
	private Long id;
	private String additionalServiceName;
	private int additionalServicePrice;
	private int popust;
	private HotelDTO hotel_dodatneUsluge;
	
}
