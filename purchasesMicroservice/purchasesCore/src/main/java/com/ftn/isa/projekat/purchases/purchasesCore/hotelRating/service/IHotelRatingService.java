package com.ftn.isa.projekat.purchases.purchasesCore.hotelRating.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ftn.isa.projekat.purchases.purchasesApi.dto.HotelRatingDTO;

@Service
public interface IHotelRatingService {
	
	public HotelRatingDTO findOneById(Long id);
	public List<HotelRatingDTO> findAll();
	public HotelRatingDTO save(HotelRatingDTO rating);
	public HotelRatingDTO deleteById(Long id);
	public HotelRatingDTO change(Long id, HotelRatingDTO hotelRatingDTO);
	public Double getHotelAverageRating(Long id);
	
}
