package com.ftn.isa.projekat.purchases.purchasesCore.hotelskaSobaRating.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ftn.isa.projekat.purchases.purchasesApi.dto.HotelskaSobaRatingDTO;

@Service
public interface IHotelskaSobaRatingService {
	
	public HotelskaSobaRatingDTO findOneById(Long id);
	public List<HotelskaSobaRatingDTO> findAll();
	public HotelskaSobaRatingDTO save(HotelskaSobaRatingDTO rating);
	public HotelskaSobaRatingDTO deleteById(Long id);
	public HotelskaSobaRatingDTO change(Long id, HotelskaSobaRatingDTO rating);
	public Double getRoomAverageRating(Long id);
	
}
