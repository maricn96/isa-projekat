package com.ftn.isa.projekat.purchases.purchasesCore.converter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.purchases.purchasesApi.dto.TicketRatingDTO;
import com.ftn.isa.projekat.purchases.purchasesCore.ticketRating.model.TicketRating;
import com.ftn.isa.projekat.purchases.purchasesCore.ticketRating.repository.TicketRatingRepository;

@Component
public class DTOTicketRatingConverter
{
	
	@Autowired
	TicketRatingRepository ticketRepo;
	
	public TicketRatingDTO convertToDTO(TicketRating ticketRating) {
		
		TicketRatingDTO dto = new TicketRatingDTO();
		
		dto.setTicketId(ticketRating.getTicketId());
		dto.setId(ticketRating.getId());
		dto.setRating(ticketRating.getRating());
		dto.setUserId(ticketRating.getUserId());
		
		return dto;
		
	}
	
	public TicketRating convertFromDTO(TicketRatingDTO dto) {
		
		Optional<TicketRating> rating = ticketRepo.findById(dto.getId());
		
		if(rating.isPresent()) {
			
			return rating.get();
			
		}
		
		TicketRating bean = new TicketRating();
		
		bean.setTicketId(dto.getTicketId());
		bean.setId(dto.getId());
		bean.setRating(dto.getRating());
		bean.setUserId(dto.getUserId());
		
		return bean;
		
	}

}
