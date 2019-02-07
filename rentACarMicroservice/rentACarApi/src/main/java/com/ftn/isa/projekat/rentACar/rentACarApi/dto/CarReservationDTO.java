package com.ftn.isa.projekat.rentACar.rentACarApi.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CarReservationDTO {

	private Long id;
	private LocalDateTime dateFrom;
	private LocalDateTime dateTo;
	private int rating;
	private int carRating;
	
	private BranchOfficeDTO branchOfficeFrom;
	private BranchOfficeDTO branchOfficeTo;
	private CarDTO reservedCar;
	private RentACarServiceDTO service;
	
}
