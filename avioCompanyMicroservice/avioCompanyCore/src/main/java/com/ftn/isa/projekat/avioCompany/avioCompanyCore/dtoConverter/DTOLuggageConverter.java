package com.ftn.isa.projekat.avioCompany.avioCompanyCore.dtoConverter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.avioCompany.avioCompanyApi.dto.IncomeDTO;
import com.ftn.isa.projekat.avioCompany.avioCompanyApi.dto.LuggageDTO;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.Income.model.Income;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.Luggage.model.Luggage;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.Luggage.repository.LuggageRepository;

@Component
public class DTOLuggageConverter 
{
	@Autowired
	private LuggageRepository repo;
	
	@Autowired
	private DTOTicketConverter ticketConverter;
	
	public LuggageDTO convertToDTO(Luggage bean)
	{
		LuggageDTO dto = new LuggageDTO();
		
		dto.setId(bean.getId());
		dto.setWeight(bean.getWeight());
		dto.setWidth(bean.getWidth());
		dto.setHeight(bean.getHeight());
		dto.setLength(bean.getLength());
		
		dto.setTicket(ticketConverter.convertToDto(bean.getTicket()));
		
		return dto;
	}

	public Luggage convertFromDTO(LuggageDTO dto) 
	{
		Optional<Luggage> luggage = repo.findById(dto.getId());
		
		if(luggage.isPresent())
		{
			return luggage.get();
		}
		
		Luggage bean = new Luggage(); 
		
		bean.setWeight(dto.getWeight());
		bean.setWidth(dto.getWidth());
		bean.setHeight(dto.getHeight());
		bean.setLength(dto.getLength());
		
		bean.setTicket(ticketConverter.convertFromDto(dto.getTicket()));
		
		
		return bean;
	}
}
