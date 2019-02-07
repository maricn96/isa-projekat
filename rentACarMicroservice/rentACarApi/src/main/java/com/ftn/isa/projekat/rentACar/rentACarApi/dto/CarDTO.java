package com.ftn.isa.projekat.rentACar.rentACarApi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDTO {

	private Long id;
	private int rentPrice;
	private CarTypeDTO carType;
	private RentACarServiceDTO rentService;
	private BranchOfficeDTO branchOffice;
	
}
