package com.ftn.isa.projekat.purchases.purchasesCore.rentACarRating.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ftn.isa.projekat.purchases.purchasesApi.dto.RentACarRatingDTO;

@Service
public interface IRentACarRatingService {

	public RentACarRatingDTO findOneById ( Long id );
	
	public List<RentACarRatingDTO> findAll();
	
	public RentACarRatingDTO save (RentACarRatingDTO rentCarRatingToSave, LocalDateTime date);
	
	public RentACarRatingDTO deleteById ( Long id );
	
	public RentACarRatingDTO changeRentACarRating ( Long id, RentACarRatingDTO rentCarRating );
	
	public Double getAverageRating(Long rentServiceId);

}
