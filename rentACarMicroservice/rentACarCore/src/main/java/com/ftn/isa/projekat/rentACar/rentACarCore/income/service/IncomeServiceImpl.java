package com.ftn.isa.projekat.rentACar.rentACarCore.income.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.isa.projekat.rentACar.rentACarApi.dto.IncomeDTO;
import com.ftn.isa.projekat.rentACar.rentACarCore.dtoConverter.DTOIncomeConverter;
import com.ftn.isa.projekat.rentACar.rentACarCore.dtoConverter.DTORentACarServiceConverter;
import com.ftn.isa.projekat.rentACar.rentACarCore.income.model.Income;
import com.ftn.isa.projekat.rentACar.rentACarCore.income.repository.IncomeRepository;
import com.ftn.isa.projekat.rentACar.rentACarCore.rentACarService.model.RentACarService;
import com.ftn.isa.projekat.rentACar.rentACarCore.rentACarService.repository.RentACarServiceRepository;

@Service
public class IncomeServiceImpl implements IIncomeService {

	@Autowired
	IncomeRepository incomeRepository;
	@Autowired
	RentACarServiceRepository rentACarServiceRepository;
	
	@Autowired
	DTOIncomeConverter incomeConverter;
	@Autowired
	DTORentACarServiceConverter rentACarServiceConverter;
	
	@Override
	public IncomeDTO findOneById(Long id) {
		Optional <Income> income = incomeRepository.findById(id);
		
		
		if (income.isPresent()) {
			
			return incomeConverter.convertToDTO(income.get());
		
		}
		else {
			
			return new IncomeDTO();
			
		}
	}

	@Override
	public List<IncomeDTO> findAll() {
		Optional< List<Income> > list = Optional.of(incomeRepository.findAll());
		ArrayList< IncomeDTO > incomesDTO = new ArrayList< IncomeDTO >();
		
		if ( list.isPresent() ) {
			
			for ( Income income : list.get()) {
				
				incomesDTO.add(incomeConverter.convertToDTO(income));
				
			}
			
			return incomesDTO;
			
		}
		
		return Collections.emptyList();
	}

	@Override
	public IncomeDTO save(IncomeDTO incomeToSave) {
		
		//checking if there is rent a car service.
		//if there is not, then we will return empty object.
		Optional<RentACarService> rentService = rentACarServiceRepository.findById(incomeToSave.getRentService().getId());
		
		if(rentService.isPresent()) {
			
			incomeRepository.save(incomeConverter.convertFromDTO(incomeToSave));
			
			return incomeToSave;
		
		}
		
		return new IncomeDTO();
	}

	@Override
	public IncomeDTO deleteById(Long id) {
		
		Optional<Income> incomeToDelete = incomeRepository.findById(id);
		
		if( incomeToDelete.isPresent() ) {
		
			incomeRepository.deleteById(id);
			return incomeConverter.convertToDTO(incomeToDelete.get());
		
		}
		
		return new IncomeDTO();
		
	}

	@Override
	public IncomeDTO changeIncome(Long id, IncomeDTO income) {
		
		Optional<Income> incomeForChange = incomeRepository.findById(id);
		
		if(incomeForChange.isPresent() && income != null) {
			
			Optional<RentACarService> rentService = rentACarServiceRepository.findById(income.getRentService().getId());
			
			if ( rentService.isPresent() ) {
				
				incomeForChange.get().setDate(income.getDate());
				incomeForChange.get().setIncomeRentACarservice(rentService.get());
				incomeForChange.get().setNumberOfCars(income.getNumberOfCars());
				incomeForChange.get().setRentIncome(income.getRentIncome());
				
				incomeRepository.save(incomeForChange.get());
				
				income.setId(incomeForChange.get().getId());
				
				return income;
			}
			
			
		}
		return new IncomeDTO();
		
	}

	
	
	
}
