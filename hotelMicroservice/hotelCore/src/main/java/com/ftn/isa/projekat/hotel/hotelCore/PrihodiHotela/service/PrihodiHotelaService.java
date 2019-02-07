package com.ftn.isa.projekat.hotel.hotelCore.PrihodiHotela.service;

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
import com.ftn.isa.projekat.hotel.hotelApi.dto.PrihodiHotelaDTO;
import com.ftn.isa.projekat.hotel.hotelCore.CenovnikUsluga.model.CenovnikUsluga;
import com.ftn.isa.projekat.hotel.hotelCore.Hotel.repository.HotelRepository;
import com.ftn.isa.projekat.hotel.hotelCore.PrihodiHotela.model.PrihodiHotela;
import com.ftn.isa.projekat.hotel.hotelCore.PrihodiHotela.repository.PrihodiHotelaRepository;
import com.ftn.isa.projekat.hotel.hotelCore.RezervacijeSobe.model.RezervacijeSobe;
import com.ftn.isa.projekat.hotel.hotelCore.RezervacijeSobe.repository.RezervacijeSobeRepository;
import com.ftn.isa.projekat.hotel.hotelCore.dtoConverter.DTOHotelConverter;
import com.ftn.isa.projekat.hotel.hotelCore.dtoConverter.DTOPrihodiHotelaConverter;

@Component
public class PrihodiHotelaService implements IPrihodiHotelaService{
	
	@Autowired
	HotelRepository hotelRepository;
	
	@Autowired
	DTOHotelConverter hotelConverter;
	
	@Autowired
	PrihodiHotelaRepository prihodiHotelaRepository;
	
	@Autowired
	RezervacijeSobeRepository rezervacijeRepository;
	
	@Autowired
	DTOPrihodiHotelaConverter prihodiHotelaConverter;
	
	
	public PrihodiHotelaDTO findOneById(Long id) {
		
		Optional<PrihodiHotela> zaPronalazak=prihodiHotelaRepository.findById(id);
		
		if(zaPronalazak.isPresent()) {
			return prihodiHotelaConverter.convertToDTO(zaPronalazak.get());
		}else {
			return new PrihodiHotelaDTO();
		}		
	}
	
	public List<PrihodiHotelaDTO> findAll(){
		Optional<List<PrihodiHotela>> list = Optional.of(prihodiHotelaRepository.findAll());
		ArrayList<PrihodiHotelaDTO> arrayDTO = new ArrayList<PrihodiHotelaDTO>();
		if(list.isPresent()) {
			for(PrihodiHotela item : list.get()) {
				arrayDTO.add(prihodiHotelaConverter.convertToDTO(item));
			}
			return arrayDTO;
		}
		return Collections.emptyList();
	}
	
	public PrihodiHotelaDTO save(PrihodiHotelaDTO dto) {
		prihodiHotelaRepository.save(prihodiHotelaConverter.convertFromDTO(dto));
		return dto;
	}
	
	public PrihodiHotelaDTO deleteById(Long id) {
		
		Optional<PrihodiHotela> zaBrisanje = prihodiHotelaRepository.findById(id);
		
		if(zaBrisanje.isPresent()) {
			prihodiHotelaRepository.deleteById(id);
			return prihodiHotelaConverter.convertToDTO(zaBrisanje.get());
		}else {
			return new PrihodiHotelaDTO();
		}
		
	}
	
	public PrihodiHotelaDTO change(Long id, PrihodiHotelaDTO dto) {
		
		Optional<PrihodiHotela> zaIzmenu = prihodiHotelaRepository.findById(id);
		
		if(zaIzmenu.isPresent() && dto!=null) {
			
			zaIzmenu.get().setIncome(dto.getIncome());
			zaIzmenu.get().setIncomeDate(dto.getIncomeDate());
			zaIzmenu.get().setBrojIznajmljivanja(dto.getBrojIznajmljivanja());
			zaIzmenu.get().setHotel_prihodiHotela(hotelConverter.convertFromDTO(dto.getHotel_prihodiHotela()));

			prihodiHotelaRepository.save(zaIzmenu.get());
			
			dto.setId(zaIzmenu.get().getId());
			
			return dto;
		}
		
		return new PrihodiHotelaDTO();
		
	}
	
	public int getPrihodiHotela(Long id, Date datumOd, Date datumDo) {
		int suma = 0;
		Optional<List<RezervacijeSobe>> list = Optional.of(rezervacijeRepository.findAll());
		for(RezervacijeSobe ph : list.get()) {
			if(ph.getHotel_rezervacijeSobe().getId() == id) {
				if((ph.getDateFrom().after(datumOd) || ph.getDateFrom().equals(datumOd)) && (ph.getDateFrom().before(datumDo) || ph.getDateFrom().equals(datumDo))) {
					suma = suma + ph.getTotalPrice(); 
				}
			}
		}
		return suma;	
	}
	

	public Double getNedeljniPrihod(Long id, String datumOd) throws ParseException{
		
		String dt = datumOd;
		String dd = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dateOd = sdf.parse(dt);
		Calendar c = Calendar.getInstance();
		c.setTime(dateOd);
		c.add(Calendar.DATE, 7);
		dd = sdf.format(c.getTime());
		Date dateDo = sdf.parse(dd);
		
		Double prihodi = 0.0;
		
		Optional<List<RezervacijeSobe>> rezervacijeList = Optional.of(rezervacijeRepository.findAll());
		
		for(RezervacijeSobe rs : rezervacijeList.get()) {
			if(rs.getHotel_rezervacijeSobe().getId() == id) {
				if((rs.getDateFrom().before(dateOd) && rs.getDateUntil().after(dateOd)) || (rs.getDateUntil().after(dateDo) && rs.getDateFrom().before(dateDo))
						|| (rs.getDateFrom().after(dateOd) && rs.getDateUntil().before(dateDo)) || (rs.getDateFrom().equals(dateOd) && rs.getDateUntil().equals(dateDo))) {
					Double prihod = (double) rs.getTotalPrice();
					prihodi += prihod;
				}	
			}
		}
		
		return prihodi;
		
	}
	
	public Double getMesecniPrihod(Long id, String datumOd) throws ParseException{
		
		String dt = datumOd;
		String dd = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dateOd = sdf.parse(dt);
		Calendar c = Calendar.getInstance();
		c.setTime(dateOd);
		c.add(Calendar.MONTH, 1);
		dd = sdf.format(c.getTime());
		Date dateDo = sdf.parse(dd);
		
		Double prihodi = 0.0;
		
		Optional<List<RezervacijeSobe>> rezervacijeList = Optional.of(rezervacijeRepository.findAll());
		
		for(RezervacijeSobe rs : rezervacijeList.get()) {
			if(rs.getHotel_rezervacijeSobe().getId() == id) {
				if((rs.getDateFrom().before(dateOd) && rs.getDateUntil().after(dateOd)) || (rs.getDateUntil().after(dateDo) && rs.getDateFrom().before(dateDo))
						|| (rs.getDateFrom().after(dateOd) && rs.getDateUntil().before(dateDo)) || (rs.getDateFrom().equals(dateOd) && rs.getDateUntil().equals(dateDo))) {
					Double prihod = (double) rs.getTotalPrice();
					prihodi += prihod;
				}	
			}
		}
		
		return prihodi;
		
	}
	
	public Double getGodisnjiPrihod(Long id, String datumOd) throws ParseException{
		
		String dt = datumOd;
		String dd = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dateOd = sdf.parse(dt);
		Calendar c = Calendar.getInstance();
		c.setTime(dateOd);
		c.add(Calendar.YEAR, 1);
		dd = sdf.format(c.getTime());
		Date dateDo = sdf.parse(dd);
		
		Double prihodi = 0.0;
		
		Optional<List<RezervacijeSobe>> rezervacijeList = Optional.of(rezervacijeRepository.findAll());
		
		for(RezervacijeSobe rs : rezervacijeList.get()) {
			if(rs.getHotel_rezervacijeSobe().getId() == id) {
				if((rs.getDateFrom().before(dateOd) && rs.getDateUntil().after(dateOd)) || (rs.getDateUntil().after(dateDo) && rs.getDateFrom().before(dateDo))
						|| (rs.getDateFrom().after(dateOd) && rs.getDateUntil().before(dateDo)) || (rs.getDateFrom().equals(dateOd) && rs.getDateUntil().equals(dateDo))) {
					Double prihod = (double) rs.getTotalPrice();
					prihodi += prihod;
				}	
			}
		}
		
		return prihodi;
		
	}
	
	
}
