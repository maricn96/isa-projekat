package com.ftn.isa.projekat.rentACar.rentACarCore.dtoConverter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.rentACar.rentACarApi.dto.RentACarServiceDTO;
import com.ftn.isa.projekat.rentACar.rentACarCore.rentACarService.model.RentACarService;
import com.ftn.isa.projekat.rentACar.rentACarCore.rentACarService.repository.RentACarServiceRepository;
@Component
public class DTORentACarServiceConverter {
	
	@Autowired
	RentACarServiceRepository rentRepository;

	public RentACarServiceDTO convertToDTO (RentACarService service) {
		
		RentACarServiceDTO dto = new RentACarServiceDTO();
		
		dto.setAdress(service.getAdress());
		dto.setDescription(service.getDescription());
		dto.setId(service.getId());
		dto.setName(service.getName());
		
		return dto;
	}
	
	public RentACarService convertFromDTO ( RentACarServiceDTO serviceDTO) {
		
		Optional<RentACarService> service = rentRepository.findById(serviceDTO.getId());
		
		if(service.isPresent()) {
			
			return service.get();
			
		}
		
		
		RentACarService bean = new RentACarService();
		
		bean.setAdress(serviceDTO.getAdress());
		bean.setDescription(serviceDTO.getDescription());
		bean.setName(serviceDTO.getName());
		
		return bean;
	}
}
