package com.ftn.isa.projekat.hotel.hotelCore.dtoConverter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.hotel.hotelApi.dto.HotelDTO;
import com.ftn.isa.projekat.hotel.hotelApi.dto.HotelskaSobaDTO;
import com.ftn.isa.projekat.hotel.hotelCore.HotelskaSoba.model.HotelskaSoba;
import com.ftn.isa.projekat.hotel.hotelCore.HotelskaSoba.repository.HotelskaSobaRepository;

@JsonComponent
@Component
public class DTOHotelskaSobaConverter {
	
	@Autowired
	DTOHotelConverter hotelConverter;
	
	@Autowired
	HotelskaSobaRepository hotelskaSobaRepository;
	
	@Autowired
	DTOTipSobeConverter tipSobeConverter;

	public HotelskaSobaDTO convertToDTO(HotelskaSoba soba) {	
		HotelskaSobaDTO dto=new HotelskaSobaDTO();	
		dto.setId(soba.getId());
		dto.setFloor(soba.getFloor());
		dto.setOriginalnaCena(soba.getOriginalnaCena());
		dto.setReserved(soba.getReserved());
		dto.setHotel_hotelskeSobe(hotelConverter.convertToDTO(soba.getHotel_hotelskeSobe()));
		dto.setTipSobe_hotelskeSobe(tipSobeConverter.convertToDTO(soba.getTipSobe_hotelskeSobe()));
		return dto;
		
	}
	
	public HotelskaSoba convertFromDTO(HotelskaSobaDTO sobaDTO) {
		
		Optional<HotelskaSoba> hotelskaSoba = hotelskaSobaRepository.findById(sobaDTO.getId());
		if(hotelskaSoba.isPresent()) {
			return hotelskaSoba.get();
		}
		
		HotelskaSoba bean = new HotelskaSoba();		
		bean.setFloor(sobaDTO.getFloor());
		bean.setOriginalnaCena(sobaDTO.getOriginalnaCena());
		bean.setReserved(sobaDTO.getReserved());
		bean.setHotel_hotelskeSobe(hotelConverter.convertFromDTO(sobaDTO.getHotel_hotelskeSobe()));
		bean.setTipSobe_hotelskeSobe(tipSobeConverter.convertFromDTO(sobaDTO.getTipSobe_hotelskeSobe()));
		return bean;
		
	}
	
}
