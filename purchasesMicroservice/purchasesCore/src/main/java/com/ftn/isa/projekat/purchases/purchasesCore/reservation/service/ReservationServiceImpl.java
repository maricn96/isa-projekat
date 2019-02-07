package com.ftn.isa.projekat.purchases.purchasesCore.reservation.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.hotel.hotelApi.dto.CenovnikUslugaDTO;
import com.ftn.isa.projekat.hotel.hotelApi.dto.DodatneUslugeDTO;
import com.ftn.isa.projekat.hotel.hotelApi.dto.RezervacijeSobeDTO;
import com.ftn.isa.projekat.purchases.purchasesApi.dto.ReservationDTO;
import com.ftn.isa.projekat.purchases.purchasesCore.converter.DTOReservationConverter;
import com.ftn.isa.projekat.purchases.purchasesCore.reservation.model.Reservation;
import com.ftn.isa.projekat.purchases.purchasesCore.reservation.repository.ReservationRepository;
import com.ftn.isa.projekat.purchases.purchasesCore.utils.DatasFromOtherMicroservices;
import com.ftn.isa.projekat.rentACar.rentACarApi.dto.CarReservationDTO;
import com.ftn.isa.projekat.user.userApi.dto.UserDTO;


@Component
public class ReservationServiceImpl implements IReservationService {
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	DTOReservationConverter reservationConverter;
	
	@Autowired
	DatasFromOtherMicroservices servicesProxy;

	@Override
	public ReservationDTO findOneById(Long id) {
		
		Optional <Reservation> reservation = reservationRepository.findById(id);
		
		
		if (reservation.isPresent()) {
			
			return reservationConverter.convertToDTO(reservation.get());
		
		}
		else {
			
			return new ReservationDTO();
			
		}	
	}

	@Override
	public List<ReservationDTO> findAll() {
		
		Optional< List<Reservation> > list = Optional.of(reservationRepository.findAll());
		ArrayList< ReservationDTO > reservationsDTO = new ArrayList< ReservationDTO >();
		
		if ( list.isPresent() ) {
			
			for ( Reservation reservation : list.get()) {
				
				reservationsDTO.add(reservationConverter.convertToDTO(reservation));
				
			}
			
			return reservationsDTO;
			
		}
		
		return Collections.emptyList();

	}

	@Override
	public ReservationDTO save(ReservationDTO reservationToSave) {
	
		/*
		 * 
		 *  First checking if there are user and other sub reservations if they exits..
		 * 
		 *  */
		
		UserDTO user = servicesProxy.getUserById(reservationToSave.getUserId());
		
		if(user.getId()!=null) {
			
			if(reservationToSave.getCarReservationId()!=null) {
		
				CarReservationDTO carReservation = servicesProxy.getCarReservationById(reservationToSave.getCarReservationId());
				
				if(carReservation.getId()==null) {
					return new ReservationDTO();
				}
				
			}
			
			if(reservationToSave.getRoomReservationId()!=null) {
				
				RezervacijeSobeDTO roomReservation = servicesProxy.getRoomReservationById(reservationToSave.getRoomReservationId());
				
				if(roomReservation.getId()==null) {
					return new ReservationDTO();
				}
				
			}
			
			if(reservationToSave.getUslugaReservationId()!=null) {
				
				DodatneUslugeDTO uslugaReservation = servicesProxy.getUslugaReservationById(reservationToSave.getUslugaReservationId());
				
				if(uslugaReservation.getId()==null) {
					return new ReservationDTO();
				}
				
			}
			
			if(reservationToSave.getCenovnikReservationId()!=null) {
				
				CenovnikUslugaDTO uslugaReservation = servicesProxy.getCenovnikReservationById(reservationToSave.getCenovnikReservationId());
				
				if(uslugaReservation.getId()==null) {
					return new ReservationDTO();
				}
				
			}
			
			reservationRepository.save(reservationConverter.convertFromDTO(reservationToSave));
			
			return reservationToSave;
		}

		return new ReservationDTO();
	}

	@Override
	public ReservationDTO deleteById(Long id) {
		
		Optional<Reservation> reservationToDelete = reservationRepository.findById(id);
		
		if( reservationToDelete.isPresent() ) {
			
			//also we need to delete Car reservation if it exits in reservation
			if(reservationToDelete.get().getCarReservationId() !=null) {
				
				servicesProxy.deleteCarReservation(reservationToDelete.get().getCarReservationId());
				
			}
			
			if(reservationToDelete.get().getRoomReservationId() !=null) {
				
				servicesProxy.deleteRoomReservation(reservationToDelete.get().getRoomReservationId());
				
			}			
			
			reservationRepository.deleteById(id);
			return reservationConverter.convertToDTO(reservationToDelete.get());
		
		}
		
		return new ReservationDTO();
	}

	@Override
	public ReservationDTO changeReservation(Long id, ReservationDTO reservation) {
		
		Optional<Reservation> reservationForChange = reservationRepository.findById(id);
		
		if(reservationForChange.isPresent() && reservation != null) {
			
			/*
			 * 
			 *  First checking if there are user and other sub reservations if they exits..
			 * 
			 *  */
			
			UserDTO user = servicesProxy.getUserById(reservation.getUserId());
			
			if(user.getId()!=null) {
				
				if(reservation.getCarReservationId()!=null) {
			
					CarReservationDTO carReservation = servicesProxy.getCarReservationById(reservation.getCarReservationId());
					
					if(carReservation.getId()==null) {
						return new ReservationDTO();
					}
					
				}
				
				if(reservation.getRoomReservationId()!=null) {
					
					RezervacijeSobeDTO roomReservation = servicesProxy.getRoomReservationById(reservation.getRoomReservationId());
					
					if(roomReservation.getId()==null) {
						return new ReservationDTO();
					}
					
				}
				
				
				//if car reservation changed then we need to delete old one
				if(reservationForChange.get().getCarReservationId() != reservation.getCarReservationId()) {
					
					servicesProxy.deleteCarReservation(reservation.getCarReservationId());
					
				}
				
				if(reservationForChange.get().getRoomReservationId() != reservation.getRoomReservationId()) {
					
					servicesProxy.deleteRoomReservation(reservation.getRoomReservationId());
					
				}
				
				reservationForChange.get().setCarReservationId(reservation.getCarReservationId());
				reservationForChange.get().setRoomReservationId(reservation.getRoomReservationId());
				reservationForChange.get().setUserId(reservation.getUserId());
				reservationForChange.get().setPrice(reservation.getPrice());
				
				
				reservationRepository.save(reservationForChange.get());
				
				reservation.setId(reservationForChange.get().getId());
				
				return reservation;
			
			}
			
		}
		
		return new ReservationDTO();
	}

	@Override
	public List<ReservationDTO> findAllByUserId(Long id) {
		
		Optional< List<Reservation> > list = reservationRepository.findAllByUserId(id);
		ArrayList< ReservationDTO > reservationsDTO = new ArrayList< ReservationDTO >();
		
		if ( list.isPresent() ) {
			
			for ( Reservation reservation : list.get()) {
				
				reservationsDTO.add(reservationConverter.convertToDTO(reservation));
				
			}
			
			return reservationsDTO;
			
		}
		
		return Collections.emptyList();

	}

	@Override
	public ReservationDTO deleteCarReservation(Long id) {
		
		Optional <Reservation> reservation = reservationRepository.findById(id);
		
		
		if (reservation.isPresent()) {
			
			CarReservationDTO carReservation = servicesProxy.getCarReservationById(reservation.get().getCarReservationId());
			
			//Preventing user to delete reservation if reservation starts in less than 2 days
			if(LocalDateTime.now().isAfter(carReservation.getDateFrom().minusDays(2))) {
				
				return new ReservationDTO();
				
			}
			
			reservation.get().setCarReservationId(null);
			
			reservationRepository.save(reservation.get());
			
			return reservationConverter.convertToDTO(reservation.get());
		
		}
		else {
			
			return new ReservationDTO();
			
		}	
	}
	
	public ReservationDTO deleteRoomReservation(Long id) {
		
		Optional <Reservation> reservation = reservationRepository.findById(id);
		
		
		if (reservation.isPresent()) {
			
			RezervacijeSobeDTO roomReservation = servicesProxy.getRoomReservationById(reservation.get().getRoomReservationId());
			
			reservation.get().setRoomReservationId(null);
			
			reservationRepository.save(reservation.get());
			
			return reservationConverter.convertToDTO(reservation.get());
		
		}
		else {
			
			return new ReservationDTO();
			
		}	
	}
}