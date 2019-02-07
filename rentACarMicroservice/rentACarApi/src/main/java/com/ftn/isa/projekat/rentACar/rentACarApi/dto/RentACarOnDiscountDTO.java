package com.ftn.isa.projekat.rentACar.rentACarApi.dto;

import java.time.LocalDateTime;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentACarOnDiscountDTO {
	
	private Long id;
	private int rentPrice;
	private CarTypeDTO carType;
	private RentACarServiceDTO rentService;
	private BranchOfficeDTO branchOffice;	
	private LocalDateTime dateFrom;	
	private LocalDateTime dateTo;
	private int carDiscountPrecentage;

}
