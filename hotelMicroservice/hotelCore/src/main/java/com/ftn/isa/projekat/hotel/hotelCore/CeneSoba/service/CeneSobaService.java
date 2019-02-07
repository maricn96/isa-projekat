package com.ftn.isa.projekat.hotel.hotelCore.CeneSoba.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.bouncycastle.asn1.eac.CertificationAuthorityReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.hotel.hotelApi.dto.CeneSobaDTO;
import com.ftn.isa.projekat.hotel.hotelApi.dto.CenovnikUslugaDTO;
import com.ftn.isa.projekat.hotel.hotelCore.CeneSoba.model.CeneSoba;
import com.ftn.isa.projekat.hotel.hotelCore.CeneSoba.repository.CeneSobaRepository;
import com.ftn.isa.projekat.hotel.hotelCore.CenovnikUsluga.model.CenovnikUsluga;
import com.ftn.isa.projekat.hotel.hotelCore.HotelskaSoba.repository.HotelskaSobaRepository;
import com.ftn.isa.projekat.hotel.hotelCore.dtoConverter.DTOCeneSobaConverter;
import com.ftn.isa.projekat.hotel.hotelCore.dtoConverter.DTOHotelskaSobaConverter;

@Component
public class CeneSobaService implements ICeneSobaService{
	
	@Autowired
	DTOHotelskaSobaConverter hotelskaSobaConverter;
	
	@Autowired
	HotelskaSobaRepository hotelskaSobaRepository;
	
	@Autowired
	DTOCeneSobaConverter ceneSobaConverter;
	
	@Autowired
	CeneSobaRepository ceneSobaRepository;
	
	public CeneSobaDTO findOneById(Long id) {
		
		Optional<CeneSoba> zaPronalazak=ceneSobaRepository.findById(id);
		
		if(zaPronalazak.isPresent()) {
			return ceneSobaConverter.convertToDTO(zaPronalazak.get());
		}else {
			return new CeneSobaDTO();
		}		
	}
	
	public List<CeneSobaDTO> findAll(){
		Optional<List<CeneSoba>> list = Optional.of(ceneSobaRepository.findAll());
		ArrayList<CeneSobaDTO> arrayDTO = new ArrayList<CeneSobaDTO>();
		if(list.isPresent()) {
			for(CeneSoba item : list.get()) {
				arrayDTO.add(ceneSobaConverter.convertToDTO(item));
			}
			return arrayDTO;
		}
		return Collections.emptyList();
	}
	
	public List<CeneSobaDTO> findByRoomId(Long id){
		Optional<List<CeneSoba>> list = Optional.of(ceneSobaRepository.findAll());
		ArrayList<CeneSobaDTO> arrayDTO = new ArrayList<CeneSobaDTO>();
		if(list.isPresent()) {
			for(CeneSoba item : list.get()) {
				if(item.getHotelskaSoba_cena().getId() == id) {
					arrayDTO.add(ceneSobaConverter.convertToDTO(item));
				}
			}
			return arrayDTO;
		}
		return Collections.emptyList();
	}
	
	public CeneSobaDTO save(CeneSobaDTO dto) {
		/*if(dto.getStandardna()) {
			List<CeneSoba> list = ceneSobaRepository.findAll();
			for(CeneSoba cena : list) {
				if(cena.getHotelskaSoba_cena().getId() == dto.getHotelskaSoba_cena().getId() && cena.getStandardna() == true) {
					return null;		
				}
			}
		}*/
		if(dto.getId() == null) {
			dto.setId((long) -1);
		}
		ceneSobaRepository.save(ceneSobaConverter.convertFromDTO(dto));
		return dto;
	}
	
	public CeneSobaDTO deleteById(Long id) {
		
		Optional<CeneSoba> zaBrisanje = ceneSobaRepository.findById(id);
		
		if(zaBrisanje.isPresent()) {
			ceneSobaRepository.deleteById(id);
			return ceneSobaConverter.convertToDTO(zaBrisanje.get());
		}else {
			return new CeneSobaDTO();
		}
		
	}
	
	public CeneSobaDTO change(Long id, CeneSobaDTO dto) {
		
		Optional<CeneSoba> zaIzmenu = ceneSobaRepository.findById(id);
		
		if(zaIzmenu.isPresent() && dto!=null) {
			
			zaIzmenu.get().setCena(dto.getCena());
			//zaIzmenu.get().setStandardna(dto.getStandardna());
			zaIzmenu.get().setDatumOd(dto.getDatumOd());
			zaIzmenu.get().setDatumDo(dto.getDatumDo());
			zaIzmenu.get().setHotelskaSoba_cena(hotelskaSobaConverter.convertFromDTO(dto.getHotelskaSoba_cena()));

			ceneSobaRepository.save(zaIzmenu.get());
			
			dto.setId(zaIzmenu.get().getId());
			
			return dto;
		}
		
		return new CeneSobaDTO();
		
	}

}
