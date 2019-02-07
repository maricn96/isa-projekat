package com.ftn.isa.projekat.purchases.purchasesCore.ticketRating.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.purchases.purchasesApi.dto.TicketRatingDTO;
import com.ftn.isa.projekat.purchases.purchasesCore.converter.DTOTicketRatingConverter;
import com.ftn.isa.projekat.purchases.purchasesCore.ticketRating.model.TicketRating;
import com.ftn.isa.projekat.purchases.purchasesCore.ticketRating.repository.TicketRatingRepository;
import com.ftn.isa.projekat.purchases.purchasesCore.utils.DatasFromOtherMicroservices;
import com.ftn.isa.projekat.rentACar.rentACarApi.dto.CarDTO;
import com.ftn.isa.projekat.user.userApi.dto.UserDTO;
@Component
public class TicketRatingServiceImpl implements ITicketRatingService
{
	@Autowired
	TicketRatingRepository ticketRepo;
	
	@Autowired
	DTOTicketRatingConverter ticketConv;
	
	@Autowired
	DatasFromOtherMicroservices servicesProxy;

	@Override
	public TicketRatingDTO findOneById(Long id)
	{
		Optional <TicketRating> ticketRating = ticketRepo.findById(id);
		
		
		if (ticketRating.isPresent()) {
			
			return ticketConv.convertToDTO(ticketRating.get());
		
		}
		else {
			
			return new TicketRatingDTO();
			
		}
	}

	@Override
	public List<TicketRatingDTO> findAll() 
	{
		Optional< List<TicketRating> > list = Optional.of(ticketRepo.findAll());
		ArrayList< TicketRatingDTO > ticketRating = new ArrayList< TicketRatingDTO >();
		
		if ( list.isPresent() ) {
			
			for ( TicketRating carRating : list.get()) {
				
				ticketRating.add(ticketConv.convertToDTO(carRating));
				
			}
			
			return ticketRating;
			
		}
		
		return Collections.emptyList();
	}

	@Override
	public TicketRatingDTO save(TicketRatingDTO dto) 
	{
		//TicketDTO ticketForRate = servicesProxy.getTicketById(dto.getTicketId());
		UserDTO userWhoRates = servicesProxy.getUserById(dto.getUserId());
		
		//if(ticketForRate.getId()!=null && userWhoRates.getId()!=null && dto.getRating()>0 && dto.getRating()<6) {
			
			TicketRating rating = ticketConv.convertFromDTO(dto);
			rating.setRatingDate(LocalDateTime.now());
			ticketRepo.save(rating);
			
			//return dto;
			
		//}
		
		return dto;
	}

	@Override
	public TicketRatingDTO deleteById(Long id)
	{
		Optional<TicketRating> ticketRatingToDelete = ticketRepo.findById(id);
		
		if( ticketRatingToDelete.isPresent() ) {
		
			ticketRepo.deleteById(id);
			return ticketConv.convertToDTO(ticketRatingToDelete.get());
		
		}
		
		return new TicketRatingDTO();
	}

	@Override
	public TicketRatingDTO changeTicketRating(Long id, TicketRatingDTO ticketRatingDto)
	{
		Optional<TicketRating> carRatingForChange = ticketRepo.findById(id);
		
		if(carRatingForChange.isPresent() && ticketRatingDto != null) 
		{
			CarDTO carForRate = servicesProxy.getCarById(ticketRatingDto.getTicketId());
			UserDTO userWhoRates = servicesProxy.getUserById(ticketRatingDto.getUserId());
			
			if(carForRate.getId()!=null && userWhoRates.getId()!=null && ticketRatingDto.getRating()>0 && ticketRatingDto.getRating()<6) {
				
				carRatingForChange.get().setTicketId(ticketRatingDto.getTicketId());
				carRatingForChange.get().setRating(ticketRatingDto.getRating());
				carRatingForChange.get().setUserId(ticketRatingDto.getUserId());
				carRatingForChange.get().setRatingDate(LocalDateTime.now());
				
				ticketRepo.save(carRatingForChange.get());
				
				ticketRatingDto.setId(carRatingForChange.get().getId());
				
				return ticketRatingDto;
			
			}
			
		}
		return new TicketRatingDTO();
	}
	
}
