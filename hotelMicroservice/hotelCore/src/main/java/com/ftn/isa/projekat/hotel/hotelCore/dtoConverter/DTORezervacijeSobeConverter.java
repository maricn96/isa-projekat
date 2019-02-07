package com.ftn.isa.projekat.hotel.hotelCore.dtoConverter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.hotel.hotelApi.dto.CenovnikUslugaDTO;
import com.ftn.isa.projekat.hotel.hotelApi.dto.RezervacijeSobeDTO;
import com.ftn.isa.projekat.hotel.hotelCore.CenovnikUsluga.model.CenovnikUsluga;
import com.ftn.isa.projekat.hotel.hotelCore.RezervacijeSobe.model.RezervacijeSobe;
import com.ftn.isa.projekat.hotel.hotelCore.RezervacijeSobe.repository.RezervacijeSobeRepository;

@JsonComponent
@Component
public class DTORezervacijeSobeConverter {
	
	@Autowired
	DTOHotelConverter hotelConverter;
	
	@Autowired
	DTOHotelskaSobaConverter hotelskaSobaConverter;
	
	@Autowired
	RezervacijeSobeRepository rezervacijeSobeRepository;
	
	
	public RezervacijeSobeDTO convertToDTO(RezervacijeSobe rezervacijeSobe) {
		RezervacijeSobeDTO dto = new RezervacijeSobeDTO();
		dto.setId(rezervacijeSobe.getId());
		dto.setDateFrom(rezervacijeSobe.getDateFrom());
		dto.setDateUntil(rezervacijeSobe.getDateUntil());
		dto.setTotalPrice(rezervacijeSobe.getTotalPrice());
		dto.setHotel_rezervacijeSobe(hotelConverter.convertToDTO(rezervacijeSobe.getHotel_rezervacijeSobe()));
		dto.setSobaId(hotelskaSobaConverter.convertToDTO(rezervacijeSobe.getSobaId()));
		
		return dto;
	}
	
	public RezervacijeSobe convertFromDTO(RezervacijeSobeDTO rezervacijeSobeDTO) {
		
		Optional<RezervacijeSobe> rezervacijaSobe = rezervacijeSobeRepository.findById(rezervacijeSobeDTO.getId());
		if(rezervacijaSobe.isPresent()) {
			return rezervacijaSobe.get();
		}
		
		RezervacijeSobe bean = new RezervacijeSobe();
		bean.setDateFrom(rezervacijeSobeDTO.getDateFrom());
		bean.setDateUntil(rezervacijeSobeDTO.getDateUntil());
		bean.setTotalPrice(rezervacijeSobeDTO.getTotalPrice());
		bean.setHotel_rezervacijeSobe(hotelConverter.convertFromDTO(rezervacijeSobeDTO.getHotel_rezervacijeSobe()));
		bean.setSobaId(hotelskaSobaConverter.convertFromDTO(rezervacijeSobeDTO.getSobaId()));
		
		
		return bean;
		
	}
	
}
