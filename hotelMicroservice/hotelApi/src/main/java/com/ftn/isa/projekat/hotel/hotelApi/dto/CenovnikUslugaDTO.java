package com.ftn.isa.projekat.hotel.hotelApi.dto;

import org.springframework.boot.jackson.JsonComponent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonComponent
@NoArgsConstructor
@AllArgsConstructor
public class CenovnikUslugaDTO {
	
	private Long id;
	private String imeUsluge;
	private int cenaUsluge;
	private HotelDTO hotel_cenovnikUsluga;
	
}
