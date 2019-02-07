package com.ftn.isa.projekat.hotel.hotelCore.CeneSoba.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ftn.isa.projekat.hotel.hotelApi.dto.CeneSobaDTO;
import com.ftn.isa.projekat.hotel.hotelApi.dto.CenovnikUslugaDTO;

@Service
public interface ICeneSobaService {
	
	public CeneSobaDTO findOneById(Long id);
	public List<CeneSobaDTO> findAll();
	public CeneSobaDTO save(CeneSobaDTO cena);
	public CeneSobaDTO deleteById(Long id);
	public CeneSobaDTO change(Long id, CeneSobaDTO cena);
	public List<CeneSobaDTO> findByRoomId(Long id);
	
}
