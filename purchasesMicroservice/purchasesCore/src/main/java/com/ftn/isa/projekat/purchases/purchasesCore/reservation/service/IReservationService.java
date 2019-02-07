package com.ftn.isa.projekat.purchases.purchasesCore.reservation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ftn.isa.projekat.purchases.purchasesApi.dto.ReservationDTO;

@Service
public interface IReservationService {
	
	public ReservationDTO findOneById ( Long id );
	
	public List<ReservationDTO> findAll();
	
	public ReservationDTO save (ReservationDTO reservationToSave);
	
	public ReservationDTO deleteById ( Long id );
	
	public ReservationDTO changeReservation ( Long id, ReservationDTO reservation );

	public List<ReservationDTO> findAllByUserId(Long id);

	public ReservationDTO deleteCarReservation(Long id);

}
