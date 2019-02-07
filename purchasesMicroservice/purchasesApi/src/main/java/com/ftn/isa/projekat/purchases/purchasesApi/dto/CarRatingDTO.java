package com.ftn.isa.projekat.purchases.purchasesApi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarRatingDTO {

	private Long id;
	
	private Long userId;
	
	private Long carId;
	
	private Integer rating;
	
	
}
