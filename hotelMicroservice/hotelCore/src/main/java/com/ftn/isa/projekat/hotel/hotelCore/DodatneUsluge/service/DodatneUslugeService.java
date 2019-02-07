package com.ftn.isa.projekat.hotel.hotelCore.DodatneUsluge.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.hotel.hotelApi.dto.CenovnikUslugaDTO;
import com.ftn.isa.projekat.hotel.hotelApi.dto.DodatneUslugeDTO;
import com.ftn.isa.projekat.hotel.hotelCore.CenovnikUsluga.model.CenovnikUsluga;
import com.ftn.isa.projekat.hotel.hotelCore.CenovnikUsluga.repository.CenovnikUslugaRepository;
import com.ftn.isa.projekat.hotel.hotelCore.DodatneUsluge.model.DodatneUsluge;
import com.ftn.isa.projekat.hotel.hotelCore.DodatneUsluge.repository.DodatneUslugeRepository;
import com.ftn.isa.projekat.hotel.hotelCore.Hotel.repository.HotelRepository;
import com.ftn.isa.projekat.hotel.hotelCore.HotelskaSoba.repository.HotelskaSobaRepository;
import com.ftn.isa.projekat.hotel.hotelCore.dtoConverter.DTOCenovnikConverter;
import com.ftn.isa.projekat.hotel.hotelCore.dtoConverter.DTODodatneUslugeConverter;
import com.ftn.isa.projekat.hotel.hotelCore.dtoConverter.DTOHotelConverter;

@Component
public class DodatneUslugeService implements IDodatneUslugeService{
	
	@Autowired
	DTODodatneUslugeConverter dodatneUslugeConverter;
	
	@Autowired
	DodatneUslugeRepository dodatneUslugeRepository;
	
	@Autowired
	CenovnikUslugaRepository cenovnikUslugaRepository;
	
	@Autowired
	HotelRepository hotelRepository;
	
	@Autowired
	DTOHotelConverter hotelConverter;
	
	@Autowired
	DTOCenovnikConverter cenovnikConverter;
	
	public DodatneUslugeDTO findOneById(Long id) {
		
		Optional<DodatneUsluge> zaPronalazak=dodatneUslugeRepository.findById(id);
		
		if(zaPronalazak.isPresent()) {
			return dodatneUslugeConverter.convertToDTO(zaPronalazak.get());
		}else {
			return new DodatneUslugeDTO();
		}		
	}
	
	public List<DodatneUslugeDTO> findAll(){
		Optional<List<DodatneUsluge>> list = Optional.of(dodatneUslugeRepository.findAll());
		ArrayList<DodatneUslugeDTO> arrayDTO = new ArrayList<DodatneUslugeDTO>();
		if(list.isPresent()) {
			for(DodatneUsluge item : list.get()) {
				arrayDTO.add(dodatneUslugeConverter.convertToDTO(item));
			}
			return arrayDTO;
		}
		return Collections.emptyList();
	}
	
	public DodatneUslugeDTO save(DodatneUslugeDTO dto) {
		if(dto.getId() == null) {
			dto.setId((long) -1);
		}
		dodatneUslugeRepository.save(dodatneUslugeConverter.convertFromDTO(dto));
		return dto;
	}
	
	public DodatneUslugeDTO deleteById(Long id) {
		
		Optional<DodatneUsluge> zaBrisanje = dodatneUslugeRepository.findById(id);
		
		if(zaBrisanje.isPresent()) {
			dodatneUslugeRepository.deleteById(id);
			return dodatneUslugeConverter.convertToDTO(zaBrisanje.get());
		}else {
			return new DodatneUslugeDTO();
		}
		
	}
	
	public DodatneUslugeDTO change(Long id, DodatneUslugeDTO dto) {
		
		Optional<DodatneUsluge> zaIzmenu = dodatneUslugeRepository.findById(id);
		
		if(zaIzmenu.isPresent() && dto!=null) {
			
			zaIzmenu.get().setAdditionalServiceName(dto.getAdditionalServiceName());
			zaIzmenu.get().setAdditionalServicePrice(dto.getAdditionalServicePrice());
			zaIzmenu.get().setPopust(dto.getPopust());
			zaIzmenu.get().setHotel_dodatneUsluge(hotelConverter.convertFromDTO(dto.getHotel_dodatneUsluge()));
			dodatneUslugeRepository.save(zaIzmenu.get());
			
			dto.setId(zaIzmenu.get().getId());
			
			return dto;
		}
		
		return new DodatneUslugeDTO();
		
	}
	
	public List<DodatneUslugeDTO> findDodatneUslugeHotela(Long id){
		Optional<List<DodatneUsluge>> list = Optional.of(dodatneUslugeRepository.findAll());
		ArrayList<DodatneUslugeDTO> uslugeDTO = new ArrayList<DodatneUslugeDTO>();
		if(list.isPresent()) {
			for(DodatneUsluge usluga : list.get()) {
				if(usluga.getHotel_dodatneUsluge().getId()==id) {
					uslugeDTO.add(dodatneUslugeConverter.convertToDTO(usluga));
				}
			}
			return uslugeDTO;
		}
		return Collections.emptyList();
		
	}

}
