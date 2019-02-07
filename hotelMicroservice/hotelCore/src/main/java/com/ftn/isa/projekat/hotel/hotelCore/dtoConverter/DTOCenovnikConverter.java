package com.ftn.isa.projekat.hotel.hotelCore.dtoConverter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.hotel.hotelApi.dto.CenovnikUslugaDTO;
import com.ftn.isa.projekat.hotel.hotelCore.CenovnikUsluga.model.CenovnikUsluga;
import com.ftn.isa.projekat.hotel.hotelCore.CenovnikUsluga.repository.CenovnikUslugaRepository;

@JsonComponent
@Component
public class DTOCenovnikConverter {
	
	@Autowired
	DTOHotelConverter hotelConverter;
	
	@Autowired
	CenovnikUslugaRepository cenovnikUslugaRepository;
	
	public CenovnikUslugaDTO convertToDTO(CenovnikUsluga cenovnikUsluga) {
		CenovnikUslugaDTO dto = new CenovnikUslugaDTO();
		dto.setId(cenovnikUsluga.getId());
		dto.setHotel_cenovnikUsluga(hotelConverter.convertToDTO(cenovnikUsluga.getHotel_cenovnikUsluga()));
		dto.setImeUsluge(cenovnikUsluga.getImeUsluge());
		dto.setCenaUsluge(cenovnikUsluga.getCenaUsluge());		
		return dto;
	}
	
	public CenovnikUsluga convertFromDTO(CenovnikUslugaDTO cenovnikUslugaDTO) {
		
		Optional<CenovnikUsluga> cenovnikUsluga = cenovnikUslugaRepository.findById(cenovnikUslugaDTO.getId());
		if(cenovnikUsluga.isPresent()) {
			return cenovnikUsluga.get();
		}
		
		CenovnikUsluga bean = new CenovnikUsluga();
		bean.setImeUsluge(cenovnikUslugaDTO.getImeUsluge());
		bean.setCenaUsluge(cenovnikUslugaDTO.getCenaUsluge());
		bean.setHotel_cenovnikUsluga(hotelConverter.convertFromDTO(cenovnikUslugaDTO.getHotel_cenovnikUsluga()));
		
		return bean;
		
	}

}
