package com.ftn.isa.projekat.hotel.hotelCore.dtoConverter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.hotel.hotelApi.dto.CeneSobaDTO;
import com.ftn.isa.projekat.hotel.hotelApi.dto.HotelskaSobaDTO;
import com.ftn.isa.projekat.hotel.hotelCore.CeneSoba.model.CeneSoba;
import com.ftn.isa.projekat.hotel.hotelCore.CeneSoba.repository.CeneSobaRepository;
import com.ftn.isa.projekat.hotel.hotelCore.HotelskaSoba.model.HotelskaSoba;

@JsonComponent
@Component
public class DTOCeneSobaConverter {
	
	@Autowired
	DTOHotelskaSobaConverter hotelskaSobaConverter;
	
	@Autowired
	CeneSobaRepository ceneSobaRepository;
	
	public CeneSobaDTO convertToDTO(CeneSoba cena) {	
		CeneSobaDTO dto = new CeneSobaDTO();	
		dto.setId(cena.getId());
		dto.setCena(cena.getCena());
		//dto.setStandardna(cena.getStandardna());
		dto.setDatumOd(cena.getDatumOd());
		dto.setDatumDo(cena.getDatumDo());
		dto.setHotelskaSoba_cena(hotelskaSobaConverter.convertToDTO(cena.getHotelskaSoba_cena()));
		
		return dto;
		
	}
	
	public CeneSoba convertFromDTO(CeneSobaDTO cenaDTO) {
		
		Optional<CeneSoba> cenaSoba = ceneSobaRepository.findById(cenaDTO.getId());
		if(cenaSoba.isPresent()) {
			return cenaSoba.get();
		}
		
		CeneSoba bean = new CeneSoba();		
		bean.setCena(cenaDTO.getCena());
		//bean.setStandardna(cenaDTO.getStandardna());
		bean.setDatumOd(cenaDTO.getDatumOd());
		bean.setDatumDo(cenaDTO.getDatumDo());
		bean.setHotelskaSoba_cena(hotelskaSobaConverter.convertFromDTO(cenaDTO.getHotelskaSoba_cena()));
		return bean;
		
	}

}
