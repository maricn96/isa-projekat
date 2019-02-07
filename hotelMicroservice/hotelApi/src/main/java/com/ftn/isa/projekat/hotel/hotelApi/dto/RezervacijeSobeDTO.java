package com.ftn.isa.projekat.hotel.hotelApi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
@Data
@NoArgsConstructor
@AllArgsConstructor

public class RezervacijeSobeDTO {

	private Long id;
	private int totalPrice;
	private Date dateFrom;
	private Date dateUntil;
	//private int rating;
	private HotelDTO hotel_rezervacijeSobe;
	private HotelskaSobaDTO sobaId;
	
}
