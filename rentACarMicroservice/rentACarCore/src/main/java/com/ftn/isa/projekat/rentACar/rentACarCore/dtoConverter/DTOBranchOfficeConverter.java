package com.ftn.isa.projekat.rentACar.rentACarCore.dtoConverter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.rentACar.rentACarApi.dto.BranchOfficeDTO;
import com.ftn.isa.projekat.rentACar.rentACarCore.branchOffice.model.BranchOffice;
import com.ftn.isa.projekat.rentACar.rentACarCore.branchOffice.repository.BranchOfficeRepository;

@Component
public class DTOBranchOfficeConverter {
	
	
	@Autowired
	DTORentACarServiceConverter rentACarConverter;
	
	@Autowired
	BranchOfficeRepository branchRepository;
	
	
	public BranchOfficeDTO convertToDTO (BranchOffice branchOffice) {
	
		BranchOfficeDTO dto = new BranchOfficeDTO();
	
		
		dto.setAdress(branchOffice.getAdress());
		dto.setId(branchOffice.getId());
		dto.setName(branchOffice.getName());
		dto.setRentServiceDTO( rentACarConverter.convertToDTO( branchOffice.getBranchRentService() ) );
		dto.setCity( branchOffice.getCity() );
		
		
		
		return dto;
	}
	
	public BranchOffice convertFromDTO ( BranchOfficeDTO branchOfficeDTO ) {
		
		Optional<BranchOffice> branch = branchRepository.findById(branchOfficeDTO.getId());
		
		if(branch.isPresent()) {
			
			return branch.get();
			
		}
		
		BranchOffice bean = new BranchOffice();	
		
		
		bean.setAdress(branchOfficeDTO.getAdress());
		bean.setName(branchOfficeDTO.getName());
		bean.setBranchRentService( rentACarConverter.convertFromDTO( branchOfficeDTO.getRentServiceDTO() ) );
	    bean.setCity( branchOfficeDTO.getCity() );
		
		
		return bean;
	}
}
