package com.ftn.isa.projekat.hotel.hotelCore.RezervacijeSobe.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.hotel.hotelApi.dto.CenovnikUslugaDTO;
import com.ftn.isa.projekat.hotel.hotelApi.dto.HotelskaSobaDTO;
import com.ftn.isa.projekat.hotel.hotelApi.dto.RezervacijeSobeDTO;
import com.ftn.isa.projekat.hotel.hotelCore.CenovnikUsluga.model.CenovnikUsluga;
import com.ftn.isa.projekat.hotel.hotelCore.Hotel.model.Hotel;
import com.ftn.isa.projekat.hotel.hotelCore.Hotel.repository.HotelRepository;
import com.ftn.isa.projekat.hotel.hotelCore.HotelskaSoba.model.HotelskaSoba;
import com.ftn.isa.projekat.hotel.hotelCore.HotelskaSoba.repository.HotelskaSobaRepository;
import com.ftn.isa.projekat.hotel.hotelCore.RezervacijeSobe.model.RezervacijeSobe;
import com.ftn.isa.projekat.hotel.hotelCore.RezervacijeSobe.repository.RezervacijeSobeRepository;
import com.ftn.isa.projekat.hotel.hotelCore.dtoConverter.DTOHotelConverter;
import com.ftn.isa.projekat.hotel.hotelCore.dtoConverter.DTOHotelskaSobaConverter;
import com.ftn.isa.projekat.hotel.hotelCore.dtoConverter.DTORezervacijeSobeConverter;

@Component
public class RezervacijeSobeService implements IRezervacijeSobeService{
	
	@Autowired
	RezervacijeSobeRepository rezervacijeSobeRepository;
	
	@Autowired
	DTORezervacijeSobeConverter rezervacijeSobeConverter;
	
	@Autowired
	HotelskaSobaRepository hotelskaSobaRepository;
	
	@Autowired
	DTOHotelskaSobaConverter hotelskaSobaConverter;
	
	@Autowired
	HotelRepository hotelRepository;
	
	@Autowired
	DTOHotelConverter hotelConverter;
	
	public RezervacijeSobeDTO findOneById(Long id) {
		
		Optional<RezervacijeSobe> zaPronalazak=rezervacijeSobeRepository.findById(id);
		
		if(zaPronalazak.isPresent()) {
			return rezervacijeSobeConverter.convertToDTO(zaPronalazak.get());
		}else {
			return new RezervacijeSobeDTO();
		}		
	}
	
	public List<RezervacijeSobeDTO> findAll(){
		Optional<List<RezervacijeSobe>> list = Optional.of(rezervacijeSobeRepository.findAll());
		ArrayList<RezervacijeSobeDTO> arrayDTO = new ArrayList<RezervacijeSobeDTO>();
		if(list.isPresent()) {
			for(RezervacijeSobe item : list.get()) {
				arrayDTO.add(rezervacijeSobeConverter.convertToDTO(item));
			}
			return arrayDTO;
		}
		return Collections.emptyList();
	}
	
	public RezervacijeSobeDTO save(RezervacijeSobeDTO dto) {

		if(dto.getId() == null) {
			dto.setId((long) -1);
		}
		rezervacijeSobeRepository.save(rezervacijeSobeConverter.convertFromDTO(dto));
		return dto;
	}
	
	public RezervacijeSobeDTO deleteById(Long id) {
		int postojiRezervacija = 0;
		Optional<RezervacijeSobe> zaBrisanje = rezervacijeSobeRepository.findById(id);
		
		if(zaBrisanje.isPresent()) {
			rezervacijeSobeRepository.deleteById(id);
			List<RezervacijeSobe> lista = rezervacijeSobeRepository.findAll();
			for(RezervacijeSobe rs : lista) {
				if(rs.getSobaId().getId()==zaBrisanje.get().getSobaId().getId()) {
					postojiRezervacija = 1; //proveravamo da li postoji barem jos jedna rezervacija sa istom sobom, ako ne onda reserved atribut te sobe postavljamo na false
				}
			}
			if(postojiRezervacija != 1) {
				zaBrisanje.get().getSobaId().setReserved(false);
			}
			return rezervacijeSobeConverter.convertToDTO(zaBrisanje.get());
		}else {
			return new RezervacijeSobeDTO();
		}
		
	}
	
	public RezervacijeSobeDTO change(Long id, RezervacijeSobeDTO dto) {
		
		Optional<RezervacijeSobe> zaIzmenu = rezervacijeSobeRepository.findById(id);
		
		if(zaIzmenu.isPresent() && dto!=null) {
			
			zaIzmenu.get().setDateFrom(dto.getDateFrom());
			zaIzmenu.get().setDateUntil(dto.getDateUntil());
			zaIzmenu.get().setTotalPrice(dto.getTotalPrice());
			//zaIzmenu.get().setHotelskaSoba_rezervacijeSobe(hotelskaSobaConverter.convertFromDTO(dto.getHotelskaSoba_rezervacijeSobe()));
			zaIzmenu.get().setSobaId(hotelskaSobaConverter.convertFromDTO(dto.getSobaId()));
			
			rezervacijeSobeRepository.save(zaIzmenu.get());
			
			dto.setId(zaIzmenu.get().getId());
			
			return dto;
		}
		
		return new RezervacijeSobeDTO();
		
	}
	
	
	public List<HotelskaSobaDTO> getFreeRooms(Long id, Date datumOd, Date datumDo){
		int vecPostoji;
		int pronasao;
		List<HotelskaSobaDTO> list = new ArrayList<HotelskaSobaDTO>();		
		Optional<List<RezervacijeSobe>> reservationList = Optional.of(rezervacijeSobeRepository.findAll());
		List<Long> idList = new ArrayList<Long>();
		List<HotelskaSobaDTO> returnList = new ArrayList<HotelskaSobaDTO>();
		
		if(reservationList.isPresent()) {
			for(RezervacijeSobe rezervacija : reservationList.get()) {//da li soba pripada zadatom hotelu i da li je slobodna u okviru zadatog vremenskog intervala?
				if(rezervacija.getHotel_rezervacijeSobe().getId() == id) {
					if((rezervacija.getDateFrom().before(datumOd) && rezervacija.getDateUntil().after(datumOd)) || (rezervacija.getDateUntil().after(datumDo) && rezervacija.getDateFrom().before(datumDo))
							|| (rezervacija.getDateFrom().after(datumOd) && rezervacija.getDateUntil().before(datumDo))) {
						idList.add(rezervacija.getSobaId().getId());//dodaj id sobe koja nije slobodna, kako bi kasnije znali da je ignorisemo u daljoj potrazi
						continue;
					}else {
						if(!list.isEmpty()) {
							vecPostoji = 0;
							for(int i = 0; i<list.size(); i++) {
								if(list.get(i).getId()==rezervacija.getSobaId().getId()) {
									vecPostoji = 1;
									break;
								}
							}
							if(vecPostoji!=1) {	
								list.add(hotelskaSobaConverter.convertToDTO(hotelskaSobaRepository.findById(rezervacija.getSobaId().getId()).get()));
							}
						}else {
							list.add(hotelskaSobaConverter.convertToDTO(hotelskaSobaRepository.findById(rezervacija.getSobaId().getId()).get()));
						}
					}
				}
			}
			for(int i = 0; i < list.size(); i++) {
				pronasao = 0;
				for(int g = 0; g < idList.size(); g++) {
					if(list.get(i).getId() == idList.get(g)) {
						pronasao = 1;
						break;
					}
				}
				if(pronasao != 1) {
					returnList.add(list.get(i));
				}
			}
			return returnList;
		}

		return Collections.emptyList();
	}
	
	public List<HotelskaSobaDTO> getFreeRoomsPrice(Long id, Date datumOd, Date datumDo, int cena, int cena1){
		
		List<HotelskaSobaDTO> returnList = new ArrayList<HotelskaSobaDTO>();
		List<HotelskaSobaDTO> list = getFreeRooms(id, datumOd, datumDo);
		if(list != null) {
			for(HotelskaSobaDTO hs : list) {
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
	
	
	
	
	public Integer getDnevnaPosecenost(Long id, Date datumOd) {
		int posecenost=0;
		Optional<List<RezervacijeSobe>> rezervacijeList = Optional.of(rezervacijeSobeRepository.findAll());
		
		for(RezervacijeSobe rs : rezervacijeList.get()) {
			if(rs.getHotel_rezervacijeSobe().getId() == id) {
				if(rs.getDateFrom().equals(datumOd) || rs.getDateUntil().equals(datumOd) || (rs.getDateFrom().before(datumOd) && rs.getDateUntil().after(datumOd))) {
					int kapacitet = rs.getSobaId().getTipSobe_hotelskeSobe().getKapacitet();
					posecenost+=kapacitet;
				}	
			}
		}
		
		return posecenost;
	}
	
	public Integer getNedeljnaPosecenost(Long id, String datumOd) throws ParseException {
		String dt = datumOd;
		String dd = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dateOd = sdf.parse(dt);
		Calendar c = Calendar.getInstance();
		c.setTime(dateOd);
		c.add(Calendar.DATE, 7);
		dd = sdf.format(c.getTime());
		Date dateDo = sdf.parse(dd);
		
		int posecenost=0;
		Optional<List<RezervacijeSobe>> rezervacijeList = Optional.of(rezervacijeSobeRepository.findAll());
		
		for(RezervacijeSobe rs : rezervacijeList.get()) {
			if(rs.getHotel_rezervacijeSobe().getId() == id) {
				if((rs.getDateFrom().before(dateOd) && rs.getDateUntil().after(dateOd)) || (rs.getDateUntil().after(dateDo) && rs.getDateFrom().before(dateDo))
						|| (rs.getDateFrom().after(dateOd) && rs.getDateUntil().before(dateDo)) || (rs.getDateFrom().equals(dateOd) && rs.getDateUntil().equals(dateDo))) {
					int kapacitet = rs.getSobaId().getTipSobe_hotelskeSobe().getKapacitet();
					posecenost+=kapacitet;
				}	
			}
		}
		
		return posecenost;
	}
	
	public Integer getMesecnaPosecenost(Long id, String datumOd) throws ParseException {
		String dt = datumOd;
		String dd = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dateOd = sdf.parse(dt);
		Calendar c = Calendar.getInstance();
		c.setTime(dateOd);
		c.add(Calendar.MONTH, 1);
		dd = sdf.format(c.getTime());
		Date dateDo = sdf.parse(dd);
		
		int posecenost=0;
		Optional<List<RezervacijeSobe>> rezervacijeList = Optional.of(rezervacijeSobeRepository.findAll());
		
		for(RezervacijeSobe rs : rezervacijeList.get()) {
			if(rs.getHotel_rezervacijeSobe().getId() == id) {
				if((rs.getDateFrom().before(dateOd) && rs.getDateUntil().after(dateOd)) || (rs.getDateUntil().after(dateDo) && rs.getDateFrom().before(dateDo))
						|| (rs.getDateFrom().after(dateOd) && rs.getDateUntil().before(dateDo)) || (rs.getDateFrom().equals(dateOd) && rs.getDateUntil().equals(dateDo))) {
					int kapacitet = rs.getSobaId().getTipSobe_hotelskeSobe().getKapacitet();
					posecenost+=kapacitet;
				}	
			}
		}
		
		return posecenost;
	}
}
