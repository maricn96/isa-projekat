package com.ftn.isa.projekat.hotel.hotelCore.HotelskaSoba.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ftn.isa.projekat.hotel.hotelApi.dto.DodatneUslugeDTO;
import com.ftn.isa.projekat.hotel.hotelApi.dto.HotelskaSobaDTO;

@Service
public interface IHotelskaSobaService {
	
	public HotelskaSobaDTO findOneById(Long id);
	public List<HotelskaSobaDTO> findAll();
	public List<HotelskaSobaDTO> findAllByHotelId(Long id);
	public HotelskaSobaDTO save(HotelskaSobaDTO hotelskaSobaDTO);
	public HotelskaSobaDTO deleteById(Long id);
	public HotelskaSobaDTO change(Long id, HotelskaSobaDTO hotelskaSobaDTO);
	public List<HotelskaSobaDTO> getRoomsOnDiscount(Long id, String datumOd, String datumDo) throws ParseException;
	public List<HotelskaSobaDTO> findAllByHotelAndPrice(Long id, int cena, int cena1);

}
