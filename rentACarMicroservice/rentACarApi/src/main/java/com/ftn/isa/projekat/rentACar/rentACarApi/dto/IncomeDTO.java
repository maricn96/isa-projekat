package com.ftn.isa.projekat.rentACar.rentACarApi.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class IncomeDTO {

	private Long id;
	
	private int rentIncome;
	private int numberOfCars;
	
	private LocalDateTime date;
	private RentACarServiceDTO rentService;
	
	
}
