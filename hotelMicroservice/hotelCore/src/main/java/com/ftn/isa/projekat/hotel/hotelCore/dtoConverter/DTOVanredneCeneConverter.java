package com.ftn.isa.projekat.hotel.hotelCore.dtoConverter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.hotel.hotelApi.dto.HotelskaSobaDTO;
import com.ftn.isa.projekat.hotel.hotelApi.dto.VanredneCeneNocenjaDTO;
import com.ftn.isa.projekat.hotel.hotelCore.Hotel.service.HotelService;
import com.ftn.isa.projekat.hotel.hotelCore.HotelskaSoba.model.HotelskaSoba;
import com.ftn.isa.projekat.hotel.hotelCore.VanredneCeneNocenja.model.VanredneCeneNocenja;
import com.ftn.isa.projekat.hotel.hotelCore.VanredneCeneNocenja.repository.VanredneCeneNocenjaRepository;

@JsonComponent
@Component
public class DTOVanredneCeneConverter {
	
	@Autowired
	DTOHotelConverter hotelConverter;
	
	@Autowired
	VanredneCeneNocenjaRepository vanredneCeneNocenjaRepository;
	
	public VanredneCeneNocenjaDTO convertToDTO(VanredneCeneNocenja cena) {
		VanredneCeneNocenjaDTO dto=new VanredneCeneNocenjaDTO();	
		dto.setId(cena.getId());
		dto.setDateFrom(cena.getDateFrom());
		dto.setDateUntil(cena.getDateUntil());
		dto.setIsItCheaper(cena.getIsItCheaper());
		dto.setPriceChange(cena.getPriceChange());
		dto.setHotel_vandredneCeneNocenja(hotelConverter.convertToDTO(cena.getHotel_vandredneCeneNocenja()));
		return dto;
		
	}
	
	public VanredneCeneNocenja convertFromDTO(VanredneCeneNocenjaDTO cenaDTO) {
		
		Optional<VanredneCeneNocenja> vanredneCene = vanredneCeneNocenjaRepository.findById(cenaDTO.getId());
		if(vanredneCene.isPresent()) {
			return vanredneCene.get();
		}
		
		VanredneCeneNocenja bean=new VanredneCeneNocenja();
		bean.setDateFrom(cenaDTO.getDateFrom());
		bean.setDateUntil(cenaDTO.getDateUntil());
		bean.setIsItCheaper(cenaDTO.getIsItCheaper());
		bean.setPriceChange(cenaDTO.getPriceChange());
		bean.setHotel_vandredneCeneNocenja(hotelConverter.convertFromDTO(cenaDTO.getHotel_vandredneCeneNocenja()));

		return bean;
		
	}

}
