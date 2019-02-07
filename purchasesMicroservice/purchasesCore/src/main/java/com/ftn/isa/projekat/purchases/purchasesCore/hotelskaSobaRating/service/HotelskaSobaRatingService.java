package com.ftn.isa.projekat.purchases.purchasesCore.hotelskaSobaRating.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.purchases.purchasesApi.dto.HotelRatingDTO;
import com.ftn.isa.projekat.purchases.purchasesApi.dto.HotelskaSobaRatingDTO;
import com.ftn.isa.projekat.purchases.purchasesCore.converter.DTOHotelskaSobaRatingConverter;
import com.ftn.isa.projekat.purchases.purchasesCore.hotelRating.model.HotelRating;
import com.ftn.isa.projekat.purchases.purchasesCore.hotelskaSobaRating.model.HotelskaSobaRating;
import com.ftn.isa.projekat.purchases.purchasesCore.hotelskaSobaRating.repository.HotelskaSobaRatingRepository;

@Component
public class HotelskaSobaRatingService implements IHotelskaSobaRatingService {

	@Autowired
	DTOHotelskaSobaRatingConverter hotelskaSobaRatingConverter;
	
	@Autowired
	HotelskaSobaRatingRepository hotelskaSobaRatingRepository;
	
	public HotelskaSobaRatingDTO findOneById(Long id) {
		
		Optional<HotelskaSobaRating> zaPronalazak=hotelskaSobaRatingRepository.findById(id);
		
		if(zaPronalazak.isPresent()) {
			return hotelskaSobaRatingConverter.convertToDTO(zaPronalazak.get());
		}else {
			return new HotelskaSobaRatingDTO();
		}		
	}
	
	public List<HotelskaSobaRatingDTO> findAll(){
		Optional<List<HotelskaSobaRating>> list = Optional.of(hotelskaSobaRatingRepository.findAll());
		ArrayList<HotelskaSobaRatingDTO> arrayDTO = new ArrayList<HotelskaSobaRatingDTO>();
		if(list.isPresent()) {
			for(HotelskaSobaRating item : list.get()) {
				arrayDTO.add(hotelskaSobaRatingConverter.convertToDTO(item));
			}
			return arrayDTO;
		}
		return Collections.emptyList();
	}
	
	public HotelskaSobaRatingDTO save(HotelskaSobaRatingDTO dto) {
		hotelskaSobaRatingRepository.save(hotelskaSobaRatingConverter.convertFromDTO(dto));
		//Optional<Hotel> hotel = hotelRepository.findById(dto.getHotel_cenovnikUsluga().getId());//pronadji hotel ciji je cenovnik
		//hotel.get().getCenovnikUsluga().add(cenovnikConverter.convertFromDTO(dto));//dodaj cenovnik u listu cenovnika hotela kome pripada
		return dto;
	}
	
	public HotelskaSobaRatingDTO deleteById(Long id) {
		
		Optional<HotelskaSobaRating> zaBrisanje = hotelskaSobaRatingRepository.findById(id);
		
		if(zaBrisanje.isPresent()) {
			hotelskaSobaRatingRepository.deleteById(id);
			return hotelskaSobaRatingConverter.convertToDTO(zaBrisanje.get());
		}else {
			return new HotelskaSobaRatingDTO();
		}
		
	}
	
	public HotelskaSobaRatingDTO change(Long id, HotelskaSobaRatingDTO dto) {
		
		Optional<HotelskaSobaRating> zaIzmenu = hotelskaSobaRatingRepository.findById(id);
		
		if(zaIzmenu.isPresent() && dto!=null) {
			
			zaIzmenu.get().setUserId(dto.getUserId());
			zaIzmenu.get().setHotelId(dto.getHotelId());
			zaIzmenu.get().setHotelskaSobaId(dto.getHotelskaSobaId());
			zaIzmenu.get().setRating(dto.getRating());
			hotelskaSobaRatingRepository.save(zaIzmenu.get());
			
			dto.setId(zaIzmenu.get().getId());
			
			return dto;
		}
		
		return new HotelskaSobaRatingDTO();
		
	}
	
	public Double getRoomAverageRating(Long id) {
		
		List<HotelskaSobaRating> lista = hotelskaSobaRatingRepository.findAll();
		int count = lista.size();
		int suma = 0;
		Double prosek = 0.0;
		for(HotelskaSobaRating hr : lista) {
			suma+=hr.getRating();
		}
		Double a = (double) suma;
		Double b = (double) count;
		prosek = (double) (a/b);
		Double roundOff = Math.round(prosek * 100.0) / 100.0;
		
		return roundOff;
	}
	
}
