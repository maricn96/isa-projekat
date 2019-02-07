package com.ftn.isa.projekat.hotel.hotelCore.dtoConverter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.hotel.hotelApi.dto.CenovnikUslugaDTO;
import com.ftn.isa.projekat.hotel.hotelApi.dto.DodatneUslugeDTO;
import com.ftn.isa.projekat.hotel.hotelCore.CenovnikUsluga.model.CenovnikUsluga;
import com.ftn.isa.projekat.hotel.hotelCore.DodatneUsluge.model.DodatneUsluge;
import com.ftn.isa.projekat.hotel.hotelCore.DodatneUsluge.repository.DodatneUslugeRepository;

@JsonComponent
@Component
public class DTODodatneUslugeConverter {

	@Autowired
	DTOCenovnikConverter cenovnikConverter;
	
	@Autowired
	DTOHotelConverter hotelConverter;
	
	@Autowired
	DodatneUslugeRepository dodatneUslugeRepository;
	
	public DodatneUslugeDTO convertToDTO(DodatneUsluge dodatneUsluge) {
		DodatneUslugeDTO dto = new DodatneUslugeDTO();
		dto.setId(dodatneUsluge.getId());
		dto.setAdditionalServiceName(dodatneUsluge.getAdditionalServiceName());
		dto.setAdditionalServicePrice(dodatneUsluge.getAdditionalServicePrice());
		dto.setPopust(dodatneUsluge.getPopust());
		dto.setHotel_dodatneUsluge(hotelConverter.convertToDTO(dodatneUsluge.getHotel_dodatneUsluge()));
		
		return dto;
	}
	
	public DodatneUsluge convertFromDTO(DodatneUslugeDTO dodatneUslugeDTO) {
		
		Optional<DodatneUsluge> dodatneUsluge = dodatneUslugeRepository.findById(dodatneUslugeDTO.getId());
		if(dodatneUsluge.isPresent()) {
			return dodatneUsluge.get();
		}
		
		DodatneUsluge bean = new DodatneUsluge();
		bean.setAdditionalServiceName(dodatneUslugeDTO.getAdditionalServiceName());
		bean.setAdditionalServicePrice(dodatneUslugeDTO.getAdditionalServicePrice());
		bean.setPopust(dodatneUslugeDTO.getPopust());
		bean.setHotel_dodatneUsluge(hotelConverter.convertFromDTO(dodatneUslugeDTO.getHotel_dodatneUsluge()));
		
		return bean;
		
	}
	
	
	
}
