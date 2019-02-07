package com.ftn.isa.projekat.avioCompany.avioCompanyCore.dtoConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.avioCompany.avioCompanyApi.dto.AvioCompanyDTO;
import com.ftn.isa.projekat.avioCompany.avioCompanyApi.dto.IncomeDTO;
import com.ftn.isa.projekat.avioCompany.avioCompanyApi.dto.DestinationDTO;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.AvioCompany.model.AvioCompany;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.Income.model.Income;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.Income.repository.IncomeRepository;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.Destination.model.Destination;

@Component
public class DTOIncomeConverter
{
	@Autowired
	private IncomeRepository incomeRepository;
	
	@Autowired
	private DTOAvioCompanyConverter avioConverter;
	
	
	/*
	 * Returns DTO object from regular model object
	 */
	public IncomeDTO convertToDTO(Income bean)
	{
		IncomeDTO dto = new IncomeDTO();
		
		dto.setId(bean.getId());
		dto.setValue(bean.getValue());
		dto.setIncomeDate(bean.getIncomeDate());
		dto.setTicketsNumnber(bean.getTicketsNumber());
		
		dto.setCompanyId(avioConverter.convertToDTO(bean.getCompanyId()));
		
		return dto;
	}

	public Income convertFromDTO(IncomeDTO dto) 
	{
		Optional<Income> income = incomeRepository.findById(dto.getId());
		
		if(income.isPresent())
		{
			return income.get();
		}
		
		Income bean = new Income(); //kako mi ovde ne trazi difoltni konstruktor, vec ga nadje preko @Data? wuut
		
		bean.setValue(dto.getValue());
		bean.setIncomeDate(dto.getIncomeDate());
		bean.setTicketsNumber(dto.getTicketsNumnber());
		
		bean.setCompanyId(avioConverter.convertFromDTO(dto.getCompanyId()));
		
		return bean;
	}

}
