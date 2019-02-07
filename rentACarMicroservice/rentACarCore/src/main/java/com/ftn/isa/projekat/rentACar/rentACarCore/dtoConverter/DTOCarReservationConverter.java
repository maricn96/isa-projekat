package com.ftn.isa.projekat.rentACar.rentACarCore.dtoConverter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.rentACar.rentACarApi.dto.CarReservationDTO;
import com.ftn.isa.projekat.rentACar.rentACarCore.reservation.model.CarReservation;
import com.ftn.isa.projekat.rentACar.rentACarCore.reservation.repository.CarReservationRepository;

@Component
public class DTOCarReservationConverter {

	@Autowired
	private DTOBranchOfficeConverter branchOfficeConverter;
	@Autowired
	private DTOCarConverter carConverter;
	@Autowired
	private DTORentACarServiceConverter rentACarConverter;
	
	
	@Autowired
	private CarReservationRepository reservationRepository;
	
	public CarReservationDTO convertToDTO ( CarReservation reservation ) {
		
		CarReservationDTO dto = new CarReservationDTO();
		
		dto.setDateFrom (reservation.getDateFrom());
		dto.setDateTo(reservation.getDateTo());
		dto.setId(reservation.getId());
		dto.setRating(reservation.getRating());
		dto.setCarRating(reservation.getCarRating() );
		dto.setBranchOfficeFrom( branchOfficeConverter.convertToDTO( reservation.getBranchOfficeFrom() ) );
		dto.setBranchOfficeTo( branchOfficeConverter.convertToDTO( reservation.getBranchOfficeTo() ) );
		dto.setService ( rentACarConverter.convertToDTO ( reservation.getReservationRentService() ) );
		dto.setReservedCar( carConverter.convertToDTO( reservation.getReservedCar() ));
		
		
		
		return dto;
	}
	
	public CarReservation convertFromDTO (CarReservationDTO reservationDTO) {
		
		Optional<CarReservation> carReservation = reservationRepository.findById(reservationDTO.getId());
		
		if(carReservation.isPresent()) {
			
			return carReservation.get();
		
		}
		
		CarReservation bean = new CarReservation();
		
		bean.setBranchOfficeFrom( branchOfficeConverter.convertFromDTO( reservationDTO.getBranchOfficeFrom() ));
		bean.setBranchOfficeTo(branchOfficeConverter.convertFromDTO( reservationDTO.getBranchOfficeTo() ));
		bean.setDateFrom(reservationDTO.getDateFrom());
	    bean.setDateTo(reservationDTO.getDateTo());
		bean.setRating(reservationDTO.getRating());
	    bean.setCarRating(reservationDTO.getCarRating());
		bean.setReservationRentService( rentACarConverter.convertFromDTO ( reservationDTO.getService() ) );
		bean.setReservedCar( carConverter.convertFromDTO( reservationDTO.getReservedCar() ) );
	
		
		return bean;
	}
}
