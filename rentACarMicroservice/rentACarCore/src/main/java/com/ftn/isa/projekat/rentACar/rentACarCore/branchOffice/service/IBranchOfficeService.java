package com.ftn.isa.projekat.rentACar.rentACarCore.branchOffice.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.ftn.isa.projekat.rentACar.rentACarApi.dto.BranchOfficeDTO;


@Service
public interface IBranchOfficeService {

	
	
	public BranchOfficeDTO findOneById ( Long id );
	
	public List<BranchOfficeDTO> findAll();
	
	public BranchOfficeDTO save (BranchOfficeDTO branchOfficeToSave);
	
	public BranchOfficeDTO deleteById ( Long id );
	
	public BranchOfficeDTO changeBranchOffice ( Long id, BranchOfficeDTO branchOffice );

	public List<BranchOfficeDTO> findAllByRentServiceId(Long rentId);
	
	
	
	
	
}
