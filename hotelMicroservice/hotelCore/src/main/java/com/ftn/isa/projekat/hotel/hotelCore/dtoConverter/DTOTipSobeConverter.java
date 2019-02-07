package com.ftn.isa.projekat.hotel.hotelCore.dtoConverter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.hotel.hotelApi.dto.TipSobeDTO;
import com.ftn.isa.projekat.hotel.hotelCore.TipSobe.model.TipSobe;
import com.ftn.isa.projekat.hotel.hotelCore.TipSobe.repository.TipSobeRepository;

@JsonComponent
@Component
public class DTOTipSobeConverter {

	@Autowired
	DTOHotelConverter hotelConverter;
	
	@Autowired
	TipSobeRepository tipSobeRepository;
	
	public TipSobeDTO convertToDTO(TipSobe tip) {
		
		TipSobeDTO dto=new TipSobeDTO();	
		dto.setId(tip.getId());
		/*dto.setNocenjePrice(tip.getNocenjePrice());
		dto.setPansionPrice(tip.getPansionPrice());
		dto.setPolupansionPrice(tip.getPolupansionPrice());*/
		dto.setRoomType(tip.getRoomType());
		dto.setKapacitet(tip.getKapacitet());
		dto.setHotel_tipSobe(hotelConverter.convertToDTO(tip.getHotel_tipSobe()));
		return dto;
		
	}
	
	public TipSobe convertFromDTO(TipSobeDTO tipDTO) {
		
		Optional<TipSobe> tipSobe = tipSobeRepository.findById(tipDTO.getId());
		if(tipSobe.isPresent()) {
			return tipSobe.get();
		}
		
		TipSobe bean=new TipSobe();
		/*bean.setNocenjePrice(tipDTO.getPansionPrice());
		bean.setPansionPrice(tipDTO.getNocenjePrice());
		bean.setPolupansionPrice(tipDTO.getPolupansionPrice());*/
		bean.setRoomType(tipDTO.getRoomType());
		bean.setKapacitet(tipDTO.getKapacitet());
		bean.setHotel_tipSobe(hotelConverter.convertFromDTO(tipDTO.getHotel_tipSobe()));
		//bean.setVanredneCeneNocenja_tipSobe(vanredneConverter.convertFromDTO(tipDTO.getVanredneCeneNocenja_tipSobe()));
		return bean;
		
	}
	
}
