package com.ftn.isa.projekat.purchases.purchasesApi.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BonusPointsDiscountsDTO {
	
	private Long id;
	
	private int points;
	
	private int discount;

}
