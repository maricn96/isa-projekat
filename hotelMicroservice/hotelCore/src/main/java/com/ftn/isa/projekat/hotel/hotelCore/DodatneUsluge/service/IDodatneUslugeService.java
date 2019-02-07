package com.ftn.isa.projekat.hotel.hotelCore.DodatneUsluge.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ftn.isa.projekat.hotel.hotelApi.dto.DodatneUslugeDTO;

@Service
public interface IDodatneUslugeService {

	public DodatneUslugeDTO findOneById(Long id);
	public List<DodatneUslugeDTO> findAll();
	public DodatneUslugeDTO save(DodatneUslugeDTO dodatneUslugeDTO);
	public DodatneUslugeDTO deleteById(Long id);
	public DodatneUslugeDTO change(Long id, DodatneUslugeDTO dodatneUslugeDTO);
	public List<DodatneUslugeDTO> findDodatneUslugeHotela(Long id);
	
}
