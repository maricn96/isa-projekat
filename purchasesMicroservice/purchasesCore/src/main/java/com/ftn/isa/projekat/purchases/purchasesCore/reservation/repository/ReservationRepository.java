package com.ftn.isa.projekat.purchases.purchasesCore.reservation.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.isa.projekat.purchases.purchasesCore.reservation.model.Reservation;


public interface ReservationRepository extends JpaRepository < Reservation , Long > {

	Optional<List<Reservation>> findAllByUserId(Long id);
	
	

}
