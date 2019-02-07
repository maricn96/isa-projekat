package com.ftn.isa.projekat.rentACar.rentACarApi.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDiscountsDTO {

	private Long id;
	
	private CarDTO carId;
	
	private LocalDateTime dateFrom;
	
	private LocalDateTime dateTo;
	
	private int carDiscountPrecentage;
	
}
