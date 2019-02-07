package com.ftn.isa.projekat.avioCompany.avioCompanyCore.Luggage.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.isa.projekat.avioCompany.avioCompanyApi.dto.LuggageDTO;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.Luggage.model.Luggage;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.Luggage.repository.LuggageRepository;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.Ticket.model.Ticket;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.Ticket.repository.TicketRepository;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.dtoConverter.DTOLuggageConverter;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.dtoConverter.DTOTicketConverter;

@Service
public class LuggageServiceImpl implements ILuggageService
{
	@Autowired
	LuggageRepository lugRepo;
	@Autowired
	DTOLuggageConverter lugConv;
	
	@Autowired
	TicketRepository tickRepo;
	@Autowired
	DTOTicketConverter tickConv;

	@Override
	public LuggageDTO findOneById(Long id) 
	{
		Optional<Luggage> income = lugRepo.findById(id);
		
		if(income.isPresent()) 
			return lugConv.convertToDTO(income.get());
		else
			return new LuggageDTO();
	}

	@Override
	public List<LuggageDTO> findAll() 
	{
		Optional< List<Luggage> > list = Optional.of(lugRepo.findAll()); //of vraca optional sa trenutnom ne nula vrednosti.
		ArrayList<LuggageDTO> dto = new ArrayList<LuggageDTO>();
		
		if(list.isPresent())
		{
			for(Luggage lug : list.get())
			{
				dto.add(lugConv.convertToDTO(lug));
			}
				
			return dto;
		}
		
		return Collections.emptyList();
	}

	@Override
	public LuggageDTO save(LuggageDTO dto) 
	{
		Optional<Ticket> ticket = tickRepo.findById(dto.getTicket().getId());
		
		if(ticket.isPresent())
		{
			lugRepo.save(lugConv.convertFromDTO(dto));
			
			return dto;
		}
		
		return new LuggageDTO();
	}

	@Override
	public LuggageDTO deleteById(Long id)
	{
		Optional<Luggage> lugToDelete = lugRepo.findById(id);
		
		if(lugToDelete.isPresent())
		{
			lugRepo.deleteById(id);
			return lugConv.convertToDTO(lugToDelete.get());
		}
		return null;
	}

	@Override
	public LuggageDTO changeLuggage(Long id, LuggageDTO luggageDto) 
	{
		Optional<Luggage> change = lugRepo.findById(id);
		
		if(change.isPresent() && luggageDto != null)
		{
			Optional<Ticket> ticket = tickRepo.findById(luggageDto.getTicket().getId());
			
			if(ticket.isPresent())
			{
				change.get().setWeight(luggageDto.getWeight());
				change.get().setWidth(luggageDto.getWidth());
				change.get().setHeight(luggageDto.getHeight());
				change.get().setLength(luggageDto.getLength());
				
				lugRepo.save(change.get());
				
				luggageDto.setId(change.get().getId());
				
				return luggageDto;				
			}
		}
		return new LuggageDTO();
	}

}
