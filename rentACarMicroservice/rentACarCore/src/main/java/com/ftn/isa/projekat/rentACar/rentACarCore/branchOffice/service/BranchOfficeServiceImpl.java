package com.ftn.isa.projekat.rentACar.rentACarCore.branchOffice.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.rentACar.rentACarApi.dto.BranchOfficeDTO;
import com.ftn.isa.projekat.rentACar.rentACarCore.branchOffice.model.BranchOffice;
import com.ftn.isa.projekat.rentACar.rentACarCore.branchOffice.repository.BranchOfficeRepository;
import com.ftn.isa.projekat.rentACar.rentACarCore.car.model.Car;
import com.ftn.isa.projekat.rentACar.rentACarCore.car.repository.CarRepository;
import com.ftn.isa.projekat.rentACar.rentACarCore.dtoConverter.DTOBranchOfficeConverter;
import com.ftn.isa.projekat.rentACar.rentACarCore.dtoConverter.DTORentACarServiceConverter;
import com.ftn.isa.projekat.rentACar.rentACarCore.rentACarService.model.RentACarService;
import com.ftn.isa.projekat.rentACar.rentACarCore.rentACarService.repository.RentACarServiceRepository;
import com.ftn.isa.projekat.rentACar.rentACarCore.reservation.model.CarReservation;
import com.ftn.isa.projekat.rentACar.rentACarCore.reservation.repository.CarReservationRepository;

@Component
public class BranchOfficeServiceImpl implements IBranchOfficeService {

	
	@Autowired
	BranchOfficeRepository branchOfficeRepository;
	@Autowired
	RentACarServiceRepository rentACarServiceRepository;
	@Autowired
	CarRepository carRepository;
	@Autowired
	CarReservationRepository reservationRepository;
	
	@Autowired
	DTOBranchOfficeConverter branchOfficeConverter;
	@Autowired
	DTORentACarServiceConverter rentServiceConverter;
	
	
	public BranchOfficeDTO findOneById(Long id) {
		
		Optional <BranchOffice> branchOffice = branchOfficeRepository.findById(id);
		
		
		if (branchOffice.isPresent()) {
			
			return branchOfficeConverter.convertToDTO(branchOffice.get());
		
		}
		else {
			
			return new BranchOfficeDTO();
			
		}
		
	}

	
	public List<BranchOfficeDTO> findAll() {

		Optional< List<BranchOffice> > list = Optional.of(branchOfficeRepository.findAll());
		ArrayList< BranchOfficeDTO > branchOfficesDTO = new ArrayList< BranchOfficeDTO >();
		
		if ( list.isPresent() ) {
			
			for ( BranchOffice branch : list.get()) {
				
				branchOfficesDTO.add(branchOfficeConverter.convertToDTO(branch));
				
			}
			
			return branchOfficesDTO;
			
		}
		
		return Collections.emptyList();
		
	}

	
	public BranchOfficeDTO save(BranchOfficeDTO branchOfficeToSave) {
		
		/*
		 * First we need to find rent a car service. If it exist then we will make branch office.
		 * 
		 *  */
		
		Optional<RentACarService> rentService = rentACarServiceRepository.findById(branchOfficeToSave.getRentServiceDTO().getId());
		
		if(rentService.isPresent()) {
		
			branchOfficeRepository.save(branchOfficeConverter.convertFromDTO(branchOfficeToSave));
			
			return branchOfficeToSave;
		}
		
		return new BranchOfficeDTO();
	}

	
	public BranchOfficeDTO deleteById(Long id) {
		
		Optional<BranchOffice> branchOfficeToDelete = branchOfficeRepository.findById(id);
		
		if( branchOfficeToDelete.isPresent() ) {
			
			/*
			 * Now we need to free all cars from this branch office to empty branch office object
			 *  */
			
			//finding another branch office
			BranchOffice branchOffice = new BranchOffice();
			
			for(Car car : branchOfficeToDelete.get().getCars()) {
				
				car.setCarBranchOffice(branchOffice);
				
				carRepository.save(car);
				
			}
			
			
			//also from all reservations we need to swap this branch office with empty object
			for(CarReservation reservation : branchOfficeToDelete.get().getReservationFromBranchOffice()) {
				
				reservation.setBranchOfficeFrom(branchOffice);
				
				reservationRepository.save(reservation);
				
			}
			
			for(CarReservation reservation : branchOfficeToDelete.get().getReservationToBranchOffice()) {
				
				reservation.setBranchOfficeTo(branchOffice);
				
				reservationRepository.save(reservation);
				
			}
			
		
			branchOfficeRepository.deleteById(id);
			
			return branchOfficeConverter.convertToDTO(branchOfficeToDelete.get());
		
		}
		
		return new BranchOfficeDTO();
		
		
	}

	
	public BranchOfficeDTO changeBranchOffice(Long id, BranchOfficeDTO branchOffice) {
		
		Optional<BranchOffice> branchForChange = branchOfficeRepository.findById(id);
		
		if( branchForChange.isPresent() && branchOffice!=null) {
					
				Optional<RentACarService> rentService = rentACarServiceRepository.findById(branchOffice.getRentServiceDTO().getId());	
				
				if(rentService.isPresent()) {
					
					branchForChange.get().setAdress(branchOffice.getAdress());
					branchForChange.get().setName(branchOffice.getName());
					branchForChange.get().setBranchRentService( rentService.get());
					branchForChange.get().setCity( branchOffice.getCity() );
					
					branchOfficeRepository.save(branchForChange.get());
					
					//after branchOffice was saved , we need to put branchs id into dto object
					branchOffice.setId(branchForChange.get().getId());
					
					
					return branchOffice;
				
				}
			 
			
		}
		
		return new BranchOfficeDTO();
	}


	@Override
	public List<BranchOfficeDTO> findAllByRentServiceId(Long rentId) {

		Optional< List<BranchOffice> > list = branchOfficeRepository.findAllByBranchRentServiceId(rentId);
		ArrayList< BranchOfficeDTO > branchOfficesDTO = new ArrayList< BranchOfficeDTO >();
		
		if ( list.isPresent() ) {
			
			for ( BranchOffice branch : list.get()) {
				
				branchOfficesDTO.add(branchOfficeConverter.convertToDTO(branch));
				
			}
			
			return branchOfficesDTO;
			
		}
		
		return Collections.emptyList();
		
	}
	
	
}
