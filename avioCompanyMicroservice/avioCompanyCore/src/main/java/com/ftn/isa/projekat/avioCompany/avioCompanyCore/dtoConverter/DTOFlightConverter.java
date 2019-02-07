package com.ftn.isa.projekat.avioCompany.avioCompanyCore.dtoConverter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.avioCompany.avioCompanyApi.dto.FlightDTO;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.Destination.model.Destination;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.Flight.model.Flight;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.Flight.repository.FlightRepository;

@Component
public class DTOFlightConverter
{
	@Autowired
	private FlightRepository FlightRepository;
	
	@Autowired
	private DTODestinationConverter destConverter;
	
	@Autowired
	DTOAvioCompanyConverter companyConverter;
	
	public FlightDTO convertToDTO(Flight bean)
	{
		FlightDTO dto = new FlightDTO();
		
		dto.setId(bean.getId());
		dto.setTakeOffTime(bean.getTakeOffTime());
		dto.setLandingTime(bean.getLandingTime());
		dto.setFlightLength(bean.getFlightLength());
		dto.setNumberOfTransfers(bean.getNumberOfTransfers());
		dto.setAllTickets(bean.getAllTickets());
		dto.setTicketsSold(bean.getTicketsSold());
		dto.setTravelType(bean.getTravelType());
		dto.setAvgRating(bean.getAvgRating());
		
		dto.setAvioCompany(companyConverter.convertToDTO(bean.getAvioCompany()));
		dto.setDestinationTakeOff(destConverter.convertToDto(bean.getTakeOffDestination()));
		dto.setDestinationLanding(destConverter.convertToDto(bean.getLandingDestination()));
		
		return dto;
		
	}
	
	public Flight convertFromDTO(FlightDTO dto)
	{
		Optional<Flight> flight = FlightRepository.findById(dto.getId());
		
		if(flight.isPresent())
		{
			return flight.get();
		}
	
		Flight bean = new Flight();
		
		bean.setTakeOffTime(dto.getTakeOffTime());
		bean.setLandingTime(dto.getLandingTime());
		bean.setFlightLength(dto.getFlightLength());
		bean.setNumberOfTransfers(dto.getNumberOfTransfers());
		bean.setAllTickets(dto.getAllTickets());
		bean.setTicketsSold(dto.getTicketsSold());
		bean.setTravelType(dto.getTravelType());
		bean.setAvgRating(dto.getAvgRating());
		
		bean.setAvioCompany(companyConverter.convertFromDTO(dto.getAvioCompany()));
		bean.setTakeOffDestination(destConverter.convertFromDTO(dto.getDestinationTakeOff()));
		bean.setLandingDestination(destConverter.convertFromDTO(dto.getDestinationLanding()));
		
		return bean;
	}
	
}
