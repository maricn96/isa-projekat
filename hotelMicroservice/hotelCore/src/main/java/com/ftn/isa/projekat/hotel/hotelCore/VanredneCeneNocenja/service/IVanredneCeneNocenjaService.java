package com.ftn.isa.projekat.hotel.hotelCore.VanredneCeneNocenja.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.ftn.isa.projekat.hotel.hotelApi.dto.VanredneCeneNocenjaDTO;

@Service
public interface IVanredneCeneNocenjaService {
	
	public VanredneCeneNocenjaDTO findOneById(Long id);
	public List<VanredneCeneNocenjaDTO> findAll();
	public VanredneCeneNocenjaDTO save(VanredneCeneNocenjaDTO ceneDTO);
	public VanredneCeneNocenjaDTO deleteById(Long id);
	public VanredneCeneNocenjaDTO change(Long id, VanredneCeneNocenjaDTO ceneDTO);

}
