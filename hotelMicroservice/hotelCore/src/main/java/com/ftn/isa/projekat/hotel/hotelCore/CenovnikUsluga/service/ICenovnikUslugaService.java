package com.ftn.isa.projekat.hotel.hotelCore.CenovnikUsluga.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ftn.isa.projekat.hotel.hotelApi.dto.CenovnikUslugaDTO;

@Service
public interface ICenovnikUslugaService {

	public CenovnikUslugaDTO findOneById(Long id);
	public List<CenovnikUslugaDTO> findAll();
	public CenovnikUslugaDTO save(CenovnikUslugaDTO cenovnikUslugaDTO);
	public CenovnikUslugaDTO deleteById(Long id);
	public CenovnikUslugaDTO change(Long id, CenovnikUslugaDTO cenovnikUslugaDTO);
	public List<CenovnikUslugaDTO> findCenovnikeHotela(Long id);
}
