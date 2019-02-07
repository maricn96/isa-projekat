package com.ftn.isa.projekat.purchases.purchasesCore.converter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.purchases.purchasesApi.dto.CarRatingDTO;
import com.ftn.isa.projekat.purchases.purchasesCore.carRating.model.CarRating;
import com.ftn.isa.projekat.purchases.purchasesCore.carRating.repository.CarRatingRepository;

@Component
public class DTOCarRatingConverter {
	
	@Autowired
	private CarRatingRepository ratingRepository;
	
	public CarRatingDTO convertToDTO(CarRating carRating) {
		
		CarRatingDTO dto = new CarRatingDTO();
		
		dto.setCarId(carRating.getCarId());
		dto.setId(carRating.getId());
		dto.setRating(carRating.getRating());
		dto.setUserId(carRating.getUserId());
		
		return dto;
		
	}
	
	public CarRating convertFromDTO (CarRatingDTO dto) {
		
		Optional<CarRating> rating = ratingRepository.findById(dto.getId());
		
		if(rating.isPresent()) {
			
			return rating.get();
			
		}
		
		CarRating bean = new CarRating();
		
		bean.setCarId(dto.getCarId());
		bean.setId(dto.getId());
		bean.setRating(dto.getRating());
		bean.setUserId(dto.getUserId());
		
		return bean;
		
	}

}
