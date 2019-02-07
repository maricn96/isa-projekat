package com.ftn.isa.projekat.hotel.hotelApi.dto;

import java.util.Date;

import org.springframework.boot.jackson.JsonComponent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonComponent
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrihodiHotelaDTO {

	private Long id;
	private int income;
	private Date incomeDate;
	private int brojIznajmljivanja;
	private HotelDTO hotel_prihodiHotela;
	
}
