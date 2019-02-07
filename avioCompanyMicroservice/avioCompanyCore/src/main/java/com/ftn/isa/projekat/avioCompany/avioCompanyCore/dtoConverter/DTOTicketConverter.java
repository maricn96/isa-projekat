package com.ftn.isa.projekat.avioCompany.avioCompanyCore.dtoConverter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.avioCompany.avioCompanyApi.dto.TicketDTO;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.Ticket.model.Ticket;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.Ticket.repository.TicketRepository;

@Component
public class DTOTicketConverter
{
	@Autowired
	private TicketRepository ticketRepo;
	
	@Autowired
	private DTOFlightConverter flightConverter;
	
	public TicketDTO convertToDto(Ticket bean)
	{
		TicketDTO dto = new TicketDTO();
		
		dto.setId(bean.getId());
		dto.setFastReservation(bean.getFastReservation());
		dto.setTicketClass(bean.getTicketClass());
		dto.setDiscount(bean.getDiscount());
		dto.setRating(bean.getRating());
		dto.setPrice(bean.getPrice());
		dto.setIsBought(bean.getIsBought());
		dto.setIsCanceled(bean.getIsCanceled());
		dto.setGrade(bean.getGrade());
		
		dto.setFlight(flightConverter.convertToDTO(bean.getFlight()));
		
		return dto;
	}
	
	public Ticket convertFromDto(TicketDTO dto)
	{
		Optional<Ticket> ticket = ticketRepo.findById(dto.getId());
		
		if(ticket.isPresent())
		{
			return ticket.get();
		}
		
		Ticket bean = new Ticket();
		
		bean.setFastReservation(dto.getFastReservation());
		bean.setTicketClass(dto.getTicketClass());
		bean.setDiscount(dto.getDiscount());
		bean.setRating(dto.getRating());
		bean.setPrice(dto.getPrice());
		bean.setIsBought(dto.getIsBought());
		bean.setIsCanceled(dto.getIsCanceled());
		bean.setGrade(dto.getGrade());
		
		bean.setFlight(flightConverter.convertFromDTO(dto.getFlight()));
		
		return bean;
	}
}
