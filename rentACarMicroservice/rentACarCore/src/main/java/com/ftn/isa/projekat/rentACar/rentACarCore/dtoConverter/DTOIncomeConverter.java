package com.ftn.isa.projekat.rentACar.rentACarCore.dtoConverter;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.rentACar.rentACarApi.dto.IncomeDTO;
import com.ftn.isa.projekat.rentACar.rentACarCore.income.model.Income;
import com.ftn.isa.projekat.rentACar.rentACarCore.income.repository.IncomeRepository;
@Component
public class DTOIncomeConverter {

	@Autowired
	private DTORentACarServiceConverter rentServiceConverter;
	
	@Autowired
	private IncomeRepository incomeRepository;
	
	
	public IncomeDTO convertToDTO (Income income) {
		
		IncomeDTO dto = new IncomeDTO();
		
		dto.setDate(income.getDate());
		dto.setId(income.getId());
		dto.setNumberOfCars(income.getNumberOfCars());
		dto.setRentIncome(income.getRentIncome());
		dto.setRentService(rentServiceConverter.convertToDTO(income.getIncomeRentACarservice()));
		
		
		return dto;

	}
	
	
	public Income convertFromDTO (IncomeDTO incomeDTO) {
		
		Optional<Income> income = incomeRepository.findById(incomeDTO.getId());
		
		if(income.isPresent()) {
			
			return income.get();
			
		}
		
		Income bean = new Income();
		
		bean.setDate(incomeDTO.getDate());
		bean.setId(incomeDTO.getId());
		bean.setNumberOfCars(incomeDTO.getNumberOfCars());
		bean.setRentIncome(incomeDTO.getRentIncome());
		bean.setIncomeRentACarservice(rentServiceConverter.convertFromDTO(incomeDTO.getRentService()));
		
		return bean;
		
	}
}
