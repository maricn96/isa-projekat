package com.ftn.isa.projekat.rentACar.rentACarApi.dto;

import lombok.Data;

@Data
public class CarTypeDTO {

	private Long id;
	
	private int numberOfSeats;
	private int modelYear;
	
	private String model;
	private String brand;
	private String carType;
	
}
