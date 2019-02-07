package com.ftn.isa.projekat.hotel.hotelCore.RezervacijeSobe.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ftn.isa.projekat.hotel.hotelApi.dto.HotelskaSobaDTO;
import com.ftn.isa.projekat.hotel.hotelApi.dto.RezervacijeSobeDTO;

@Service
public interface IRezervacijeSobeService {

	public RezervacijeSobeDTO findOneById(Long id);
	public List<RezervacijeSobeDTO> findAll();
	public RezervacijeSobeDTO save(RezervacijeSobeDTO rezervacijeSobeDTO);
	public RezervacijeSobeDTO deleteById(Long id);
	public RezervacijeSobeDTO change(Long id, RezervacijeSobeDTO rezervacijeSobeDTO);
	public List<HotelskaSobaDTO> getFreeRooms(Long hotelId, Date datumOd, Date datumDo);
	public Integer getDnevnaPosecenost(Long id, Date datumOd);
	public Integer getNedeljnaPosecenost(Long id, String datumOd) throws ParseException;
	public Integer getMesecnaPosecenost(Long id, String datumOd) throws ParseException;
	public List<HotelskaSobaDTO> getFreeRoomsPrice(Long hotelId, Date datumOd, Date datumDo, int cena, int cena1);
	
}
