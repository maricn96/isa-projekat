package com.ftn.isa.projekat.hotel.hotelCore.HotelskaSoba.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.hotel.hotelApi.dto.CeneSobaDTO;
import com.ftn.isa.projekat.hotel.hotelApi.dto.HotelDTO;
import com.ftn.isa.projekat.hotel.hotelApi.dto.HotelskaSobaDTO;
import com.ftn.isa.projekat.hotel.hotelCore.CeneSoba.model.CeneSoba;
import com.ftn.isa.projekat.hotel.hotelCore.CeneSoba.repository.CeneSobaRepository;
import com.ftn.isa.projekat.hotel.hotelCore.Hotel.model.Hotel;
import com.ftn.isa.projekat.hotel.hotelCore.Hotel.repository.HotelRepository;
import com.ftn.isa.projekat.hotel.hotelCore.HotelskaSoba.model.HotelskaSoba;
import com.ftn.isa.projekat.hotel.hotelCore.HotelskaSoba.repository.HotelskaSobaRepository;
import com.ftn.isa.projekat.hotel.hotelCore.RezervacijeSobe.service.RezervacijeSobeService;
import com.ftn.isa.projekat.hotel.hotelCore.TipSobe.repository.TipSobeRepository;
import com.ftn.isa.projekat.hotel.hotelCore.dtoConverter.DTOHotelConverter;
import com.ftn.isa.projekat.hotel.hotelCore.dtoConverter.DTOHotelskaSobaConverter;
import com.ftn.isa.projekat.hotel.hotelCore.dtoConverter.DTOTipSobeConverter;

@Component
public class HotelskaSobaService implements IHotelskaSobaService{
	
	@Autowired
	CeneSobaRepository ceneSobaRepository;
	
	@Autowired
	HotelskaSobaRepository hotelskaSobaRepository;
	
	@Autowired
	RezervacijeSobeService rezervacijaService;
	
	@Autowired
	HotelRepository hotelRepository;
	
	@Autowired
	TipSobeRepository tipSobeRepository;
	
	@Autowired
	DTOHotelConverter hotelConverter;
	
	@Autowired
    DTOTipSobeConverter tipSobeConverter;
    
	@Autowired
	DTOHotelskaSobaConverter hotelskaSobaConverter;
	
	public HotelskaSobaDTO findOneById(Long id) {
		
		Optional<HotelskaSoba> soba=hotelskaSobaRepository.findById(id);
		
		if(soba.isPresent()) {
			return hotelskaSobaConverter.convertToDTO(soba.get());
		}else {
			return new HotelskaSobaDTO();
		}
		
	}
	
	public List<HotelskaSobaDTO> findAll(){
		Optional<List<HotelskaSoba>> list = Optional.of(hotelskaSobaRepository.findAll());
		ArrayList<HotelskaSobaDTO> sobeDTO = new ArrayList<HotelskaSobaDTO>();
		if(list.isPresent()) {
			for(HotelskaSoba soba : list.get()) {
				sobeDTO.add(hotelskaSobaConverter.convertToDTO(soba));
			}
			return sobeDTO;
		}
		return Collections.emptyList();
	}
	
	public HotelskaSobaDTO save(HotelskaSobaDTO sobaDTO) {
		if(sobaDTO.getId() == null) {
			sobaDTO.setId((long) -1);
		}
		hotelskaSobaRepository.save(hotelskaSobaConverter.convertFromDTO(sobaDTO));
		return sobaDTO;
	}
	
	public HotelskaSobaDTO deleteById(Long id) {
		
		Optional<HotelskaSoba> soba = hotelskaSobaRepository.findById(id);
		
		if(soba.isPresent() && hotelskaSobaRepository.findById(id).get().getReserved() != true) {
			hotelskaSobaRepository.deleteById(id);
			return hotelskaSobaConverter.convertToDTO(soba.get());
		}else {
			return new HotelskaSobaDTO();
		}
		
	}
	
	public HotelskaSobaDTO change(Long id, HotelskaSobaDTO sobaDTO) {
		
		Optional<HotelskaSoba> soba = hotelskaSobaRepository.findById(id);
		
		if(soba.isPresent() && sobaDTO!=null && hotelskaSobaRepository.findById(id).get().getReserved() != true) {
			soba.get().setFloor(sobaDTO.getFloor());
			soba.get().setReserved(sobaDTO.getReserved());
			soba.get().setOriginalnaCena(sobaDTO.getOriginalnaCena());
			soba.get().setHotel_hotelskeSobe(hotelConverter.convertFromDTO(sobaDTO.getHotel_hotelskeSobe()));
			soba.get().setTipSobe_hotelskeSobe(tipSobeConverter.convertFromDTO(sobaDTO.getTipSobe_hotelskeSobe()));

			hotelskaSobaRepository.save(soba.get());
			
			sobaDTO.setId(soba.get().getId());
			
			return sobaDTO;
		}
		
		return new HotelskaSobaDTO();
		
	}
	
	public List<HotelskaSobaDTO> findAllByHotelId(Long id){
		Optional<List<HotelskaSoba>> list = Optional.of(hotelskaSobaRepository.findAll());
		ArrayList<HotelskaSobaDTO> sobeDTO = new ArrayList<HotelskaSobaDTO>();
		if(list.isPresent()) {
			for(HotelskaSoba soba : list.get()) {
				if(soba.getHotel_hotelskeSobe().getId()==id) {
					sobeDTO.add(hotelskaSobaConverter.convertToDTO(soba));
				}
			}
			return sobeDTO;
		}
		return Collections.emptyList();
	}
	
	public List<HotelskaSobaDTO> getRoomsOnDiscount(Long id, String datumOd, String datumDo) throws ParseException{
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		Date dateOd = sdf.parse(datumOd);
		Date dateDo = sdf.parse(datumDo);
		List<HotelskaSobaDTO> naPopustu = new ArrayList<HotelskaSobaDTO>();		
		List<HotelskaSobaDTO> sobeDTO = rezervacijaService.getFreeRooms(id, dateOd, dateDo);
		List<CeneSoba> list = ceneSobaRepository.findAll();
		
		if(list != null && sobeDTO != null) {			
			for(HotelskaSobaDTO soba : sobeDTO) {
				for(CeneSoba cena : list) {					
					if(soba.getId() == cena.getHotelskaSoba_cena().getId() && soba.getOriginalnaCena()>cena.getCena() && ((cena.getDatumOd().after(dateOd) || cena.getDatumOd().equals(dateOd)) && (cena.getDatumDo().before(dateOd) || (cena.getDatumDo().equals(dateOd))))) {
						naPopustu.add(soba);
					}
				}			
			}
			
			return naPopustu;
			
		}
		
		return Collections.emptyList();
	}

	@Override
	public List<HotelskaSobaDTO> findAllByHotelAndPrice(Long id, int cena, int cena1) {
		List<HotelskaSobaDTO> sobeDTO = findAllByHotelId(id);
		List<HotelskaSobaDTO> returnList = new ArrayList<>();
		if(sobeDTO != null) {
			for(HotelskaSobaDTO hs : sobeDTO) {
				if(hs.getHotel_hotelskeSobe().getId() == id) {
					if(cena!=-1 && cena1!=-1) {
						if(hs.getOriginalnaCena()>=cena && hs.getOriginalnaCena()<=cena1) {
							returnList.add(hs);
						}
					}else if(cena!=-1 && cena1==-1) {
						if(hs.getOriginalnaCena()>=cena) {
							returnList.add(hs);
						}
					}else if(cena==-1 && cena1!=-1) {
						if(hs.getOriginalnaCena()<=cena1) {
							returnList.add(hs);
						}
					}else {
						returnList.add(hs);
					}
				}					
			}
			return returnList;
		}else {
			return Collections.emptyList();
		}
	}

}