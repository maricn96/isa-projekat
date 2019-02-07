package com.ftn.isa.projekat.hotel.hotelCore.VanredneCeneNocenja.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.hotel.hotelApi.dto.HotelDTO;
import com.ftn.isa.projekat.hotel.hotelApi.dto.VanredneCeneNocenjaDTO;
import com.ftn.isa.projekat.hotel.hotelCore.Hotel.model.Hotel;
import com.ftn.isa.projekat.hotel.hotelCore.VanredneCeneNocenja.model.VanredneCeneNocenja;
import com.ftn.isa.projekat.hotel.hotelCore.VanredneCeneNocenja.repository.VanredneCeneNocenjaRepository;
import com.ftn.isa.projekat.hotel.hotelCore.dtoConverter.DTOHotelConverter;
import com.ftn.isa.projekat.hotel.hotelCore.dtoConverter.DTOVanredneCeneConverter;

@Component
public class VanredneCeneNocenjaService implements IVanredneCeneNocenjaService{

	@Autowired
	VanredneCeneNocenjaRepository vanredneCeneRepository;
	
	@Autowired
	DTOVanredneCeneConverter vanredneCeneConverter;
	
	@Autowired
	DTOHotelConverter hotelConverter;
	
	public VanredneCeneNocenjaDTO findOneById(Long id) {
		
		Optional<VanredneCeneNocenja> cene=vanredneCeneRepository.findById(id);
		
		if(cene.isPresent()) {
			return vanredneCeneConverter.convertToDTO(cene.get());
		}else {
			return new VanredneCeneNocenjaDTO();
		}
		
	}
	
	public List<VanredneCeneNocenjaDTO> findAll(){
		Optional<List<VanredneCeneNocenja>> list = Optional.of(vanredneCeneRepository.findAll());
		ArrayList<VanredneCeneNocenjaDTO> ceneDTO = new ArrayList<VanredneCeneNocenjaDTO>();
		if(list.isPresent()) {
			for(VanredneCeneNocenja cena : list.get()) {
				ceneDTO.add(vanredneCeneConverter.convertToDTO(cena));
			}
			return ceneDTO;
		}
		return Collections.emptyList();
	}
	
	public VanredneCeneNocenjaDTO save(VanredneCeneNocenjaDTO ceneDTO) {
		vanredneCeneRepository.save(vanredneCeneConverter.convertFromDTO(ceneDTO));
		return ceneDTO;
	}
	
	public VanredneCeneNocenjaDTO deleteById(Long id) {
		
		Optional<VanredneCeneNocenja> cena = vanredneCeneRepository.findById(id);
		
		if(cena.isPresent()) {
			vanredneCeneRepository.deleteById(id);
			return vanredneCeneConverter.convertToDTO(cena.get());
		}else {
			return new VanredneCeneNocenjaDTO();
		}
		
	}
	
	public VanredneCeneNocenjaDTO change(Long id, VanredneCeneNocenjaDTO cenaDTO) {
		
		Optional<VanredneCeneNocenja> cena = vanredneCeneRepository.findById(id);
		
		if(cena.isPresent() && cenaDTO!=null) {
			cena.get().setDateFrom(cenaDTO.getDateFrom());
			cena.get().setDateUntil(cenaDTO.getDateUntil());
			cena.get().setIsItCheaper(cenaDTO.getIsItCheaper());
			cena.get().setPriceChange(cenaDTO.getPriceChange());
			cena.get().setHotel_vandredneCeneNocenja(hotelConverter.convertFromDTO(cenaDTO.getHotel_vandredneCeneNocenja()));
			
			vanredneCeneRepository.save(cena.get());
			
			cenaDTO.setId(cena.get().getId());
			
			return cenaDTO;
		}
		
		return new VanredneCeneNocenjaDTO();
		
	}
	
}
