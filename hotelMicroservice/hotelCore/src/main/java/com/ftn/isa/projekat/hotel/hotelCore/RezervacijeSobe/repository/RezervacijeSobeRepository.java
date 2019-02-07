package com.ftn.isa.projekat.hotel.hotelCore.RezervacijeSobe.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ftn.isa.projekat.hotel.hotelApi.dto.HotelskaSobaDTO;
import com.ftn.isa.projekat.hotel.hotelCore.RezervacijeSobe.model.RezervacijeSobe;

public interface RezervacijeSobeRepository extends JpaRepository<RezervacijeSobe, Long>{

	//@Query(value = "SELECT * FROM rezervacija_sobe WHERE sobaId = :rezervisanaSoba AND dateFrom < :dateOd AND dateUntil < :dateDo")
	//public HotelskaSobaDTO getFreeRoom(Long rezervisanaSoba, Date dateOd, Date dateDo);
	
}
