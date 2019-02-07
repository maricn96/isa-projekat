package com.ftn.isa.projekat.purchases.purchasesCore.converter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.purchases.purchasesApi.dto.HotelskaSobaRatingDTO;
import com.ftn.isa.projekat.purchases.purchasesCore.hotelskaSobaRating.model.HotelskaSobaRating;
import com.ftn.isa.projekat.purchases.purchasesCore.hotelskaSobaRating.repository.HotelskaSobaRatingRepository;

@Component
public class DTOHotelskaSobaRatingConverter {
	
	@Autowired
	private HotelskaSobaRatingRepository hotelskaSobaRatingRepository;
	
	public HotelskaSobaRatingDTO convertToDTO(HotelskaSobaRating soba) {
		
		HotelskaSobaRatingDTO dto = new HotelskaSobaRatingDTO();
		dto.setId(soba.getId());
		dto.setUserId(soba.getUserId());
		dto.setHotelId(soba.getHotelId());
		dto.setHotelskaSobaId(soba.getHotelskaSobaId());
		dto.setRating(soba.getRating());
		
		return dto;
		
	}
	
	public HotelskaSobaRating convertFromDTO(HotelskaSobaRatingDTO hotelskaSobaRatingDTO) {
		
		Optional<HotelskaSobaRating> rating = hotelskaSobaRatingRepository.findById(hotelskaSobaRatingDTO.getId());
		if(rating.isPresent()) {
			return rating.get();
		}
		
		HotelskaSobaRating bean = new HotelskaSobaRating();
		bean.setId(hotelskaSobaRatingDTO.getId());
		bean.setUserId(hotelskaSobaRatingDTO.getUserId());
		bean.setHotelId(hotelskaSobaRatingDTO.getHotelId());
		bean.setHotelskaSobaId(hotelskaSobaRatingDTO.getHotelskaSobaId());
		bean.setRating(hotelskaSobaRatingDTO.getRating());
		
		return bean;
	}
	
}
