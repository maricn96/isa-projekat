package com.ftn.isa.projekat.hotel.hotelApi.dto;

import org.springframework.boot.jackson.JsonComponent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonComponent
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipSobeDTO {
	
	private Long id;
	private String roomType;
	private int kapacitet;
	/*private int nocenjePrice;
	private int polupansionPrice;
	private int pansionPrice;*/
	private HotelDTO hotel_tipSobe;
	
}
