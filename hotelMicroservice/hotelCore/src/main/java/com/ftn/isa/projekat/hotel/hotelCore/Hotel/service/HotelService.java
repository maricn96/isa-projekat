package com.ftn.isa.projekat.hotel.hotelCore.Hotel.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.hotel.hotelApi.dto.CenovnikUslugaDTO;
import com.ftn.isa.projekat.hotel.hotelApi.dto.HotelDTO;
import com.ftn.isa.projekat.hotel.hotelApi.dto.HotelskaSobaDTO;
import com.ftn.isa.projekat.hotel.hotelCore.CenovnikUsluga.model.CenovnikUsluga;
import com.ftn.isa.projekat.hotel.hotelCore.CenovnikUsluga.repository.CenovnikUslugaRepository;
import com.ftn.isa.projekat.hotel.hotelCore.Hotel.model.Hotel;
import com.ftn.isa.projekat.hotel.hotelCore.Hotel.repository.HotelRepository;
import com.ftn.isa.projekat.hotel.hotelCore.HotelskaSoba.model.HotelskaSoba;
import com.ftn.isa.projekat.hotel.hotelCore.HotelskaSoba.repository.HotelskaSobaRepository;
import com.ftn.isa.projekat.hotel.hotelCore.RezervacijeSobe.repository.RezervacijeSobeRepository;
import com.ftn.isa.projekat.hotel.hotelCore.RezervacijeSobe.service.RezervacijeSobeService;
import com.ftn.isa.projekat.hotel.hotelCore.dtoConverter.DTOCenovnikConverter;
import com.ftn.isa.projekat.hotel.hotelCore.dtoConverter.DTOHotelConverter;
import com.ftn.isa.projekat.hotel.hotelCore.dtoConverter.DTOHotelskaSobaConverter;

@Component
public class HotelService implements IHotelService{
	
	@Autowired
	HotelRepository hotelRepository;
	
	@Autowired
	DTOHotelConverter hotelConverter;
	
	@Autowired
	HotelskaSobaRepository hotelskaSobaRepository;
	
	@Autowired
	RezervacijeSobeService rezervacijaSobeService;
	
	@Autowired
	DTOHotelskaSobaConverter hotelskaSobaConverter;
	
	@Autowired
	CenovnikUslugaRepository cenovnikUslugaRepository;
	
	@Autowired
	DTOCenovnikConverter cenovnikConverter;
	
	public HotelDTO findOneById(Long id) {
		
		Optional<Hotel> hotel=hotelRepository.findById(id);
		
		if(hotel.isPresent()) {
			return hotelConverter.convertToDTO(hotel.get());
		}else {
			return new HotelDTO();
		}
		
	}
	
	public List<HotelDTO> findAll(){
		Optional<List<Hotel>> list = Optional.of(hotelRepository.findAll());
		ArrayList<HotelDTO> hoteliDTO = new ArrayList<HotelDTO>();
		if(list.isPresent()) {
			for(Hotel hotel : list.get()) {
				hoteliDTO.add(hotelConverter.convertToDTO(hotel));
			}
			return hoteliDTO;
		}
		return Collections.emptyList();
	}
	
	public HotelDTO save(HotelDTO hotelDTO) {
		if(hotelDTO.getId() == null) {
			hotelDTO.setId((long) -1);
		}
		hotelRepository.save(hotelConverter.convertFromDTO(hotelDTO));
		return hotelDTO;
	}
	
	public HotelDTO deleteById(Long id) {
		
		Optional<Hotel> hotel = hotelRepository.findById(id);
		
		if(hotel.isPresent()) {
			hotelRepository.deleteById(id);
			return hotelConverter.convertToDTO(hotel.get());
		}else {
			return new HotelDTO();
		}
		
	}
	
	public HotelDTO change(Long id, HotelDTO hotelDTO) {
		
		Optional<Hotel> hotel = hotelRepository.findById(id);
		
		if(hotel.isPresent() && hotelDTO!=null) {
			hotel.get().setName(hotelDTO.getName());
			hotel.get().setAdress(hotelDTO.getAdress());
			hotel.get().setPromotionalDescription(hotelDTO.getPromotionalDescription());
			
			hotelRepository.save(hotel.get());
			
			hotelDTO.setId(hotel.get().getId());
			
			return hotelDTO;
		}
		
		return new HotelDTO();
		
	}
	
	public List<HotelDTO> filterHotel(String hotelIliAdresa, /*String cenaMin, String cenaMax,*/ String datumDolaska, String datumOdlaska, String brojGostiju, String brojSoba) throws ParseException{
		
		int guestNumber = Integer.parseInt(brojGostiju);
		int roomNumber = Integer.parseInt(brojSoba);
		/*int minPrice = Integer.parseInt(cenaMin);
		int maxPrice = Integer.parseInt(cenaMax);*/
		Optional<List<Hotel>> list = Optional.of(hotelRepository.findAll());
		ArrayList<HotelDTO> hoteliDTO = new ArrayList<HotelDTO>();
		List<Hotel> lista = new ArrayList<Hotel>();
		List<HotelskaSoba> sobeList = new ArrayList<HotelskaSoba>();
		Optional<List<HotelskaSoba>> sobeListBezDatuma = Optional.of(hotelskaSobaRepository.findAll());
		
		if(list.isPresent()) {
			if(!hotelIliAdresa.equals("-1")) {
				for(Hotel hotel : list.get()) {
					if(hotel.getName().contains(hotelIliAdresa) || hotel.getAdress().contains(hotelIliAdresa)) {
						lista.add(hotel);
					}	
				}
				list.get().clear();
				for(Hotel hotel : lista) {
					list.get().add(hotel);
				}
				lista.clear();
			}
			
			/*if(minPrice != -1) {
				
				if(sobeListBezDatuma.isPresent()) {
					for(Hotel hotel : list.get()) {
						for(HotelskaSoba hs : sobeListBezDatuma.get()) {
							if(hs.getHotel_hotelskeSobe().getId() == hotel.getId() && hs.getOriginalnaCena()>=minPrice) {
								lista.add(hotel);
								break;
							}
						}
					}
					list.get().clear();
					for(Hotel hotel : lista) {
						list.get().add(hotel);
					}
					lista.clear();
				}
			}
			
			if(maxPrice != -1) {
				
				if(sobeListBezDatuma.isPresent()) {
					for(Hotel hotel : list.get()) {
						for(HotelskaSoba hs : sobeListBezDatuma.get()) {
							if(hs.getHotel_hotelskeSobe().getId() == hotel.getId() && hs.getOriginalnaCena()<=maxPrice) {
								lista.add(hotel);
								break;
							}
						}
					}
					list.get().clear();
					for(Hotel hotel : lista) {
						list.get().add(hotel);
					}
					lista.clear();
				}
			}*/
			
			if(!datumDolaska.equals("-1") && !datumOdlaska.equals("-1")) {
				Date dateOd = new SimpleDateFormat("yyyy-MM-dd").parse(datumDolaska);
				Date dateDo = new SimpleDateFormat("yyyy-MM-dd").parse(datumOdlaska);
				for(Hotel hotel : list.get()) {
					List<HotelskaSobaDTO> sobe = rezervacijaSobeService.getFreeRooms(hotel.getId(), dateOd, dateDo);
					for(HotelskaSobaDTO soba : sobe) {
						sobeList.add(hotelskaSobaConverter.convertFromDTO(soba));
					}
					if(sobe.size()>0) {
						lista.add(hotel);
					}
					
				}
				list.get().clear();
				for(Hotel hotel : lista) {
					list.get().add(hotel);
				}
				lista.clear();
			}
			
			if(guestNumber != -1 && guestNumber > 0) {
				
				if(!datumDolaska.equals("-1") && !datumOdlaska.equals("-1")) {		
					
					for(Hotel hotel : list.get()) {
						int kapacitet = 0;
						for(HotelskaSoba soba : sobeList) {
							if(hotel.getId() == soba.getHotel_hotelskeSobe().getId()) {
								kapacitet+=soba.getTipSobe_hotelskeSobe().getKapacitet();
							}
						}
						if(kapacitet>=guestNumber) {
							lista.add(hotel);
						}	
					}
					list.get().clear();
					for(Hotel hotel : lista) {
						list.get().add(hotel);
					}
					lista.clear();
					
				}else {
					
					for(Hotel hotel : list.get()) {
					int kapacitet = 0;
					for(HotelskaSoba soba : sobeListBezDatuma.get()) {
						if(hotel.getId() == soba.getHotel_hotelskeSobe().getId()) {
							kapacitet+=soba.getTipSobe_hotelskeSobe().getKapacitet();
						}
					}
					if(kapacitet>=guestNumber) {
						lista.add(hotel);
					}	
				}
				list.get().clear();
				for(Hotel hotel : lista) {
					list.get().add(hotel);
				}
				lista.clear();
						
				}
			}
			
			if(roomNumber != -1 && roomNumber > 0) {
				if(!datumDolaska.equals("-1") && !datumOdlaska.equals("-1")) {	
					
					for(Hotel hotel : list.get()) {
						int broj = 0;
						for(HotelskaSoba soba : sobeList) {
							if(hotel.getId() == soba.getHotel_hotelskeSobe().getId()) {
								broj++;
							}
						}
						if(broj>=roomNumber) {
							lista.add(hotel);
						}	
					}
					list.get().clear();
					for(Hotel hotel : lista) {
						list.get().add(hotel);
					}
					lista.clear();
					
				}else {
					
					for(Hotel hotel : list.get()) {
						int broj = 0;
						for(HotelskaSoba soba : sobeListBezDatuma.get()) {
							if(hotel.getId() == soba.getHotel_hotelskeSobe().getId()) {
								broj++;
							}
						}
						if(broj>=roomNumber) {
							lista.add(hotel);
						}	
					}
					list.get().clear();
					for(Hotel hotel : lista) {
						list.get().add(hotel);
					}
					lista.clear();
					
				}
			}
			
			
			
			for(Hotel hotel : list.get()) {
				hoteliDTO.add(hotelConverter.convertToDTO(hotel));
			}
			return hoteliDTO;
		}
		return Collections.emptyList();
	}
	
	/*public List<CenovnikUslugaDTO> findCenovnike(Long id){
		Optional<List<CenovnikUsluga>> list = Optional.of(cenovnikUslugaRepository.findAll());
		ArrayList<CenovnikUslugaDTO> uslugeDTO = new ArrayList<CenovnikUslugaDTO>();
		if(list.isPresent()) {
			for(CenovnikUsluga cenovnik : list.get()) {
				if(cenovnik.getHotel_cenovnikUsluga().getId()==id) {
					uslugeDTO.add(cenovnikConverter.convertToDTO(cenovnik));
				}
			}
			return uslugeDTO;
		}
		return Collections.emptyList();
		
	}*/
	
	/*public List<HotelskaSobaDTO> findSobeHotela(Long id){
		Optional<List<HotelskaSoba>> list = Optional.of(hotelskaSobaRepository.findAll());
		ArrayList<HotelskaSobaDTO> sobeDTO = new ArrayList<HotelskaSobaDTO>();
		if(list.isPresent()) {
			for(HotelskaSoba soba : list.get()) {
				sobeDTO.add(hotelskaSobaConverter.convertToDTO(soba));
			}
			return sobeDTO;
		}
		return Collections.emptyList();
		
	}*/		

}
