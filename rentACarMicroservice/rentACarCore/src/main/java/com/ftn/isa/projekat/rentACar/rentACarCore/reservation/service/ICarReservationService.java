package com.ftn.isa.projekat.rentACar.rentACarCore.reservation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ftn.isa.projekat.rentACar.rentACarApi.dto.CarReservationDTO;

@Service
public interface ICarReservationService {
	
	

	public CarReservationDTO findOneById ( Long id );
	
	public List<CarReservationDTO> findAll();
	
	public CarReservationDTO save (CarReservationDTO reservationToSave);
	
	public CarReservationDTO deleteById ( Long id );
	
	public CarReservationDTO changeReservation ( Long id, CarReservationDTO reservation );

	public CarReservationDTO deleteByIdNoConditions(Long id);


	

	

	
}
