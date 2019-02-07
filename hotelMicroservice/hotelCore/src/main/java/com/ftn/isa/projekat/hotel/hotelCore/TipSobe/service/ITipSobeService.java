package com.ftn.isa.projekat.hotel.hotelCore.TipSobe.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ftn.isa.projekat.hotel.hotelApi.dto.TipSobeDTO;

@Service
public interface ITipSobeService {
	
	public TipSobeDTO findOneById(Long id);
	public List<TipSobeDTO> findAll();
	public TipSobeDTO save(TipSobeDTO tiSobeDTO);
	public TipSobeDTO deleteById(Long id);
	public TipSobeDTO change(Long id, TipSobeDTO tiSobeDTO);
	public List<TipSobeDTO> findTypeByHotelId(Long id);

}
