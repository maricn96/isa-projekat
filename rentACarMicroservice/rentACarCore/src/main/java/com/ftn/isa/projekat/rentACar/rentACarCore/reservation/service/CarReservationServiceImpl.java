package com.ftn.isa.projekat.rentACar.rentACarCore.reservation.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.rentACar.rentACarApi.dto.CarReservationDTO;
import com.ftn.isa.projekat.rentACar.rentACarCore.branchOffice.model.BranchOffice;
import com.ftn.isa.projekat.rentACar.rentACarCore.branchOffice.repository.BranchOfficeRepository;
import com.ftn.isa.projekat.rentACar.rentACarCore.car.model.Car;
import com.ftn.isa.projekat.rentACar.rentACarCore.car.repository.CarRepository;
import com.ftn.isa.projekat.rentACar.rentACarCore.dtoConverter.DTOBranchOfficeConverter;
import com.ftn.isa.projekat.rentACar.rentACarCore.dtoConverter.DTOCarConverter;
import com.ftn.isa.projekat.rentACar.rentACarCore.dtoConverter.DTOCarReservationConverter;
import com.ftn.isa.projekat.rentACar.rentACarCore.dtoConverter.DTORentACarServiceConverter;
import com.ftn.isa.projekat.rentACar.rentACarCore.rentACarService.model.RentACarService;
import com.ftn.isa.projekat.rentACar.rentACarCore.rentACarService.repository.RentACarServiceRepository;
import com.ftn.isa.projekat.rentACar.rentACarCore.reservation.model.CarReservation;
import com.ftn.isa.projekat.rentACar.rentACarCore.reservation.repository.CarReservationRepository;

@Component
public class CarReservationServiceImpl implements ICarReservationService {

	@Autowired
	CarReservationRepository reservationRepository;
	@Autowired
	CarRepository carRepository;
	@Autowired
	BranchOfficeRepository branchOfficeRepository;
	@Autowired
	RentACarServiceRepository rentACarRepository;
	
	@Autowired
	DTOCarReservationConverter reservationConverter;
	@Autowired
	DTOCarConverter carConverter;
	@Autowired
	DTOBranchOfficeConverter branchOfficeConverter;
	@Autowired
	DTORentACarServiceConverter rentACarConverter;
	

	@Override
	public CarReservationDTO findOneById(Long id) {
		
		Optional <CarReservation> reservation = reservationRepository.findById(id);
		
		
		if (reservation.isPresent()) {
			
			return reservationConverter.convertToDTO(reservation.get());
		
		}
		else {
			
			return new CarReservationDTO();
			
		}	
	}

	@Override
	public List<CarReservationDTO> findAll() {
		
		Optional< List<CarReservation> > list = Optional.of(reservationRepository.findAll());
		ArrayList< CarReservationDTO > reservationsDTO = new ArrayList< CarReservationDTO >();
		
		if ( list.isPresent() ) {
			
			for ( CarReservation reservation : list.get()) {
				
				reservationsDTO.add(reservationConverter.convertToDTO(reservation));
				
			}
			
			return reservationsDTO;
			
		}
		
		return Collections.emptyList();

	}

	@Override
	public CarReservationDTO save(CarReservationDTO reservationToSave) {
		
		//At First we need to check if dateFrom is before dateTo
		//If it is after dateTo, then we will return an empty object
		if(reservationToSave.getDateFrom().isAfter(reservationToSave.getDateTo())) {
			
			return new CarReservationDTO();
			
		}
		
		
		/*
		 * First of all we need to see if there is existing data in data base for car, branch offices and rent a car service.
		 * If there is not for all of them, we are returning empty object.
		 * 
		 *  */
		Optional<BranchOffice> branchFrom = branchOfficeRepository.findById(reservationToSave.getBranchOfficeFrom().getId());
		Optional<BranchOffice> branchTo = branchOfficeRepository.findById(reservationToSave.getBranchOfficeTo().getId());
		Optional<RentACarService> rentService = rentACarRepository.findById(reservationToSave.getService().getId());
		Optional<Car> reservedCar = carRepository.findById(reservationToSave.getReservedCar().getId());
		
		if(branchFrom.isPresent() && branchTo.isPresent() && rentService.isPresent() && reservedCar.isPresent()) {
			
			Long branchFromRentId = branchFrom.get().getBranchRentService().getId();
			Long branchToRentId = branchTo.get().getBranchRentService().getId();
			Long carRentId = reservedCar.get().getCarRentService().getId();
			Long rentId = rentService.get().getId();
			
			// now we need to see if they are from same rent a car service. If not , we are returning empty object.
			if(branchFromRentId == rentId && branchToRentId==rentId && carRentId==rentId) {
				
				CarReservation reservation =reservationRepository.save(reservationConverter.convertFromDTO(reservationToSave));
				
				//setting id
				reservationToSave.setId(reservation.getId());
				
				return reservationToSave;
				
			}
			
			
		}
		
		
		return new CarReservationDTO();

	}

	@Override
	public CarReservationDTO deleteById(Long id) {
		
		Optional<CarReservation> reservationToDelete = reservationRepository.findById(id);
	
		if( reservationToDelete.isPresent() ) {
			
			
		
			reservationRepository.deleteById(id);
			return reservationConverter.convertToDTO(reservationToDelete.get());
		
		}
		
		return new CarReservationDTO();
	}
	
	@Override
	public CarReservationDTO deleteByIdNoConditions(Long id) {
		
		Optional<CarReservation> reservationToDelete = reservationRepository.findById(id);	
		
		if( reservationToDelete.isPresent() ) {
			reservationRepository.deleteById(id);
			return reservationConverter.convertToDTO(reservationToDelete.get());
		
		}
		
		return new CarReservationDTO();
	}

	@Override
	public CarReservationDTO changeReservation(Long id, CarReservationDTO reservation) {
		
		//At First we need to check if dateFrom is before dateTo
		//If it is after dateTo, then we will return an empty object
		if(reservation.getDateFrom().isAfter(reservation.getDateTo())) {
			
			return new CarReservationDTO();
			
		}
		
		Optional<CarReservation> reservationForChange = reservationRepository.findById(id);
		
		if(reservationForChange.isPresent() && reservation != null) {
			
			Optional<BranchOffice> branchFrom = branchOfficeRepository.findById(reservation.getBranchOfficeFrom().getId());
			Optional<BranchOffice> branchTo = branchOfficeRepository.findById(reservation.getBranchOfficeTo().getId());
			Optional<RentACarService> rentService = rentACarRepository.findById(reservation.getService().getId());
			Optional<Car> reservedCar = carRepository.findById(reservation.getReservedCar().getId());
			
			
			
			if(rentService.isPresent() && branchFrom.isPresent() && branchTo.isPresent() && reservedCar.isPresent()) {
				
				
				// now we need to see if they are from same rent a car service. If not , we are returning empty object.

				Long branchFromRentId = branchFrom.get().getBranchRentService().getId();
				Long branchToRentId = branchTo.get().getBranchRentService().getId();
				Long carRentId = reservedCar.get().getCarRentService().getId();
				Long rentId = rentService.get().getId();
				
				
				if(branchFromRentId == rentId && branchToRentId==rentId && carRentId==rentId) {
	
					reservationForChange.get().setBranchOfficeFrom(branchFrom.get());
					reservationForChange.get().setBranchOfficeTo(branchTo.get());
					reservationForChange.get().setReservationRentService(rentService.get());
					reservationForChange.get().setDateFrom(reservation.getDateFrom());
					reservationForChange.get().setDateTo(reservation.getDateTo());
					reservationForChange.get().setRating(reservation.getRating());
					reservationForChange.get().setReservedCar(reservedCar.get());
					
					
					reservationRepository.save(reservationForChange.get());
					
					reservation.setId(reservationForChange.get().getId());
					
					return reservation;
					
				}
			}
		}
		
		return new CarReservationDTO();
	}

	

	
	

	
	
	
	
}
