package com.ftn.isa.projekat.hotel.hotelCore.dtoConverter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.hotel.hotelApi.dto.CenovnikUslugaDTO;
import com.ftn.isa.projekat.hotel.hotelApi.dto.PrihodiHotelaDTO;
import com.ftn.isa.projekat.hotel.hotelCore.CenovnikUsluga.model.CenovnikUsluga;
import com.ftn.isa.projekat.hotel.hotelCore.PrihodiHotela.model.PrihodiHotela;
import com.ftn.isa.projekat.hotel.hotelCore.PrihodiHotela.repository.PrihodiHotelaRepository;

@JsonComponent
@Component
public class DTOPrihodiHotelaConverter {

	@Autowired
	DTOHotelConverter hotelConverter;
	
	@Autowired
	PrihodiHotelaRepository prihodiHotelaRepository;
	
	public PrihodiHotelaDTO convertToDTO(PrihodiHotela prihodHotela) {
		PrihodiHotelaDTO dto = new PrihodiHotelaDTO();
		dto.setId(prihodHotela.getId());
		dto.setIncome(prihodHotela.getIncome());
		dto.setIncomeDate(prihodHotela.getIncomeDate());
		dto.setBrojIznajmljivanja(prihodHotela.getBrojIznajmljivanja());
		dto.setHotel_prihodiHotela(hotelConverter.convertToDTO(prihodHotela.getHotel_prihodiHotela()));
		
		return dto;
	}
	
	public PrihodiHotela convertFromDTO(PrihodiHotelaDTO prihodiHotelaDTO) {
		
		Optional<PrihodiHotela> prihodiHotela = prihodiHotelaRepository.findById(prihodiHotelaDTO.getId());
		if(prihodiHotela.isPresent()) {
			return prihodiHotela.get();
		}
		
		PrihodiHotela bean = new PrihodiHotela();
		bean.setIncome(prihodiHotelaDTO.getIncome());
		bean.setIncomeDate(prihodiHotelaDTO.getIncomeDate());
		bean.setBrojIznajmljivanja(prihodiHotelaDTO.getBrojIznajmljivanja());
		bean.setHotel_prihodiHotela(hotelConverter.convertFromDTO(prihodiHotelaDTO.getHotel_prihodiHotela()));
		
		return bean;
		
	}
	
}
