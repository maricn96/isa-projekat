package com.ftn.isa.projekat.avioCompany.avioCompanyCore.dtoConverter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.avioCompany.avioCompanyApi.dto.DestinationDTO;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.Destination.model.Destination;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.Destination.repository.DestinationRepository;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.Flight.controller.FlightController;

@Component
public class DTODestinationConverter
{
	@Autowired
	private DestinationRepository destRepository;
	
	
	public DestinationDTO convertToDto(Destination dest)
	{
		DestinationDTO dto = new DestinationDTO();
		
		dto.setId(dest.getId());
		dto.setName(dest.getName());
		
		return dto;
	}

	public Destination convertFromDTO(DestinationDTO dto)
	{
		Optional<Destination> destination = destRepository.findById(dto.getId());
		
		if(destination.isPresent())
		{
			return destination.get();
		}
		
		Destination dest = new Destination();
		
		dest.setId(dto.getId());
		dest.setName(dto.getName());
		
		return dest;
	}

}
