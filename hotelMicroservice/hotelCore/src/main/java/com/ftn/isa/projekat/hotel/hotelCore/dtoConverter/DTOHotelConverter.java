package com.ftn.isa.projekat.hotel.hotelCore.dtoConverter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.hotel.hotelApi.dto.HotelDTO;
import com.ftn.isa.projekat.hotel.hotelCore.Hotel.model.Hotel;
import com.ftn.isa.projekat.hotel.hotelCore.Hotel.repository.HotelRepository;

@JsonComponent
@Component
public class DTOHotelConverter {
	
	@Autowired
	HotelRepository hotelRepository;
	
	public HotelDTO convertToDTO(Hotel hotel) {
		
		HotelDTO dto=new HotelDTO();	
		dto.setId(hotel.getId());
		dto.setName(hotel.getName());
		dto.setAdress(hotel.getAdress());
		dto.setPromotionalDescription(hotel.getPromotionalDescription());
		return dto;
		
	}
	
	public Hotel convertFromDTO(HotelDTO hotelDTO) {
		
		Optional<Hotel> hotel = hotelRepository.findById(hotelDTO.getId());
		if(hotel.isPresent()) {
			return hotel.get();
		}
		
		Hotel bean=new Hotel();
		bean.setName(hotelDTO.getName());
		bean.setAdress(hotelDTO.getAdress());
		bean.setPromotionalDescription(hotelDTO.getPromotionalDescription());
		return bean;
		
	}
}
