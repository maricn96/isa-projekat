package com.ftn.isa.projekat.rentACar.rentACarCore.rentACarService.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.isa.projekat.rentACar.rentACarApi.dto.RentACarServiceDTO;
import com.ftn.isa.projekat.rentACar.rentACarCore.branchOffice.model.BranchOffice;
import com.ftn.isa.projekat.rentACar.rentACarCore.dtoConverter.DTORentACarServiceConverter;
import com.ftn.isa.projekat.rentACar.rentACarCore.rentACarService.model.RentACarService;
import com.ftn.isa.projekat.rentACar.rentACarCore.rentACarService.repository.RentACarServiceRepository;

@Service
public class RentACarServiceServiceImpl implements IRentACarServiceService {

	@Autowired
	RentACarServiceRepository rentACarServiceRepository;
	
	@Autowired
	DTORentACarServiceConverter rentACarServiceConverter;

	@Override
	public RentACarServiceDTO findOneById(Long id) {

		Optional <RentACarService> rentACarService = rentACarServiceRepository.findById(id);
		
		
		if (rentACarService.isPresent()) {
			
			return rentACarServiceConverter.convertToDTO(rentACarService.get());
		
		}
		else {
			
			return new RentACarServiceDTO();
			
		}

		
	}

	@Override
	public List<RentACarServiceDTO> findAll() {

		Optional< List<RentACarService> > list = Optional.of(rentACarServiceRepository.findAll());
		ArrayList< RentACarServiceDTO > rentACarServicesDTO = new ArrayList< RentACarServiceDTO >();
		
		if ( list.isPresent() ) {
			
			for ( RentACarService rentACarService : list.get()) {
				
				rentACarServicesDTO.add(rentACarServiceConverter.convertToDTO(rentACarService));
				
			}
			
			return rentACarServicesDTO;
			
		}
		
		return Collections.emptyList();

		
	}

	@Override
	public RentACarServiceDTO save(RentACarServiceDTO rentACarServiceToSave) {
		
		rentACarServiceRepository.save(rentACarServiceConverter.convertFromDTO(rentACarServiceToSave));
		
		return rentACarServiceToSave;

	}

	@Override
	public RentACarServiceDTO deleteById(Long id) {

		Optional<RentACarService> rentACarServiceToDelete = rentACarServiceRepository.findById(id);
		
		if( rentACarServiceToDelete.isPresent() ) {
		
			rentACarServiceRepository.deleteById(id);
			
			return rentACarServiceConverter.convertToDTO(rentACarServiceToDelete.get());
		
		}
		
		return new RentACarServiceDTO();

		
	}

	@Override
	public RentACarServiceDTO changeRentACarService(Long id, RentACarServiceDTO rentACarService) {
		
		Optional<RentACarService> rentServiceForChange = rentACarServiceRepository.findById(id);
		
		if(rentServiceForChange.isPresent() && rentACarService != null) {
			
			rentServiceForChange.get().setAdress(rentACarService.getAdress());
			rentServiceForChange.get().setDescription(rentACarService.getDescription());
			rentServiceForChange.get().setName(rentACarService.getName());
		
			rentACarServiceRepository.save(rentServiceForChange.get());
			
			rentACarService.setId(rentServiceForChange.get().getId());
			
			return rentACarService;
		}
		
		return new RentACarServiceDTO();
	}
	
	@Override
	public Integer getSumOfIncomes(Long rentService, LocalDateTime dateFrom, LocalDateTime dateTo) {
		
		Optional<Integer> sumOfIncomes = rentACarServiceRepository.findSumOfIncomes(rentService,dateFrom,dateTo);
		
		if( sumOfIncomes.isPresent() ) {
			
			return sumOfIncomes.get();
			
		}
		
		return -1;
		
	}

	@Override
	public List<RentACarServiceDTO> findAllFilter(String name, String city1, String city2) {

		Optional< List<RentACarService> > list;
		if(name.equals("nema")) {
			list = Optional.of(rentACarServiceRepository.findAll());
		}
		else {
			list = rentACarServiceRepository.findAllByName(name);
		}
		
		ArrayList< RentACarServiceDTO > rentACarServicesDTO = new ArrayList< RentACarServiceDTO >();
		
		if ( list.isPresent() ) {
			
			for ( RentACarService rentACarService : list.get()) {
		
				if(!city1.equals("nema") &&  !city2.equals("nema")) {
					//znaci da moramo da trazimo servise koje imaju filijale na datim gradovima
					boolean nasaoCity1 = false;
					boolean nasaoCity2 = false;
					
					for(BranchOffice branch : rentACarService.getBranchOffices()) {
						if(branch.getCity().equals(city1)) {
							nasaoCity1 = true;
						}
						if(branch.getCity().equals(city2)) {
							nasaoCity2 = true;
						}
					}
					
					if(nasaoCity1 && nasaoCity2) {
						rentACarServicesDTO.add(rentACarServiceConverter.convertToDTO(rentACarService));

					}
				}
				else {
					rentACarServicesDTO.add(rentACarServiceConverter.convertToDTO(rentACarService));

				}
				
			}
			
			return rentACarServicesDTO;
			
		}
		
		return Collections.emptyList();

		
	}
	
}
