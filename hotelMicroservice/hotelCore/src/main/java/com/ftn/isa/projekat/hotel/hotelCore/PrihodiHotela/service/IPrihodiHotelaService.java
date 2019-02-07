package com.ftn.isa.projekat.hotel.hotelCore.PrihodiHotela.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ftn.isa.projekat.hotel.hotelApi.dto.PrihodiHotelaDTO;

@Service
public interface IPrihodiHotelaService {

	public PrihodiHotelaDTO findOneById(Long id);
	public List<PrihodiHotelaDTO> findAll();
	public PrihodiHotelaDTO save(PrihodiHotelaDTO prihodiHotelaDTO);
	public PrihodiHotelaDTO deleteById(Long id);
	public PrihodiHotelaDTO change(Long id, PrihodiHotelaDTO prihodiHotelaDTO);
	public int getPrihodiHotela(Long id, Date datumOd, Date datumDo);
	public Double getNedeljniPrihod(Long id, String datumOd) throws ParseException; 
	public Double getMesecniPrihod(Long id, String datumOd) throws ParseException; 
	public Double getGodisnjiPrihod(Long id, String datumOd) throws ParseException;
	
}
