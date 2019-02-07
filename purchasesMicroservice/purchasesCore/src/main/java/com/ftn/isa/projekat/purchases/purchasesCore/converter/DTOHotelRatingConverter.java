package com.ftn.isa.projekat.purchases.purchasesCore.converter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.purchases.purchasesApi.dto.HotelRatingDTO;
import com.ftn.isa.projekat.purchases.purchasesCore.hotelRating.model.HotelRating;
import com.ftn.isa.projekat.purchases.purchasesCore.hotelRating.repository.HotelRatingRepository;

@Component
public class DTOHotelRatingConverter {
	
	@Autowired
	private HotelRatingRepository hotelRatingRepository;
	
	public HotelRatingDTO convertToDTO(HotelRating hotel) {
		
		HotelRatingDTO dto = new HotelRatingDTO();
		dto.setId(hotel.getId());
		dto.setUserId(hotel.getUserId());
		dto.setHotelId(hotel.getHotelId());
		dto.setRating(hotel.getRating());
		
		return dto;
		
	}
	
	public HotelRating convertFromDTO(HotelRatingDTO hotelRatingDTO) {
		
		Optional<HotelRating> rating = hotelRatingRepository.findById(hotelRatingDTO.getId());
		if(rating.isPresent()) {
			return rating.get();
		}
		
		HotelRating bean = new HotelRating();
		bean.setId(hotelRatingDTO.getId());
		bean.setUserId(hotelRatingDTO.getUserId());
		bean.setHotelId(hotelRatingDTO.getHotelId());
		bean.setRating(hotelRatingDTO.getRating());
		
		return bean;
	}

}
