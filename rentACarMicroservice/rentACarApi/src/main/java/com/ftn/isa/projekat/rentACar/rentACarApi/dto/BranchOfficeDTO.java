package com.ftn.isa.projekat.rentACar.rentACarApi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BranchOfficeDTO {

	private Long id;
	private String name;
	private String adress;
	private String city;
	
	private RentACarServiceDTO rentServiceDTO;
	
	
	
}
