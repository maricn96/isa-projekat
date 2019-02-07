package com.ftn.isa.projekat.purchases.purchasesCore.converter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.purchases.purchasesApi.dto.RentACarRatingDTO;
import com.ftn.isa.projekat.purchases.purchasesCore.rentACarRating.model.RentACarRating;
import com.ftn.isa.projekat.purchases.purchasesCore.rentACarRating.repository.RentACarRatingRepository;
@Component
public class DTORentACarRatingConverter {
	
	@Autowired
	private RentACarRatingRepository rentRatingRepository;
	
	
	
	public RentACarRatingDTO convertToDTO (RentACarRating bean) {
		
		RentACarRatingDTO dto = new RentACarRatingDTO();
		
		dto.setId(bean.getId());
		dto.setRating(bean.getRating());
		dto.setRentACarId(bean.getRentACarId());
		dto.setUserId(bean.getUserId());
		
		return dto;
		
	}
	
	public RentACarRating convertFromDTO(RentACarRatingDTO dto) {
		
		Optional<RentACarRating> rentRating = rentRatingRepository.findById(dto.getId());
		
		if(rentRating.isPresent()) {
			
			return rentRating.get();
			
		}
		
		
		RentACarRating bean = new RentACarRating();
		
		bean.setId(dto.getId());
		bean.setRating(dto.getRating());
		bean.setRentACarId(dto.getRentACarId());
		bean.setUserId(dto.getUserId());
		
		return bean;
	}
	

}
