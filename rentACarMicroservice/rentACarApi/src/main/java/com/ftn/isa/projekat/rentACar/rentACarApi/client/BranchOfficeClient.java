package com.ftn.isa.projekat.rentACar.rentACarApi.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ftn.isa.projekat.rentACar.rentACarApi.dto.BranchOfficeDTO;


@FeignClient(name="branchOfficeClient", url = "http://localhost:8090/api/rentacar/branchOffice")
public interface BranchOfficeClient {


	@GetMapping("/{id}")
	public BranchOfficeDTO getOneBranchOfficeById (@PathVariable("id") Long id);
	
	@GetMapping("/all")
	public List<BranchOfficeDTO> getAllBranches();
	
	@PostMapping("/")
	public BranchOfficeDTO addBranchOffice(@RequestBody BranchOfficeDTO dto);
	
	@DeleteMapping("/{id}")
	public BranchOfficeDTO deleteBranchOffice(@PathVariable("id") Long id);
	
	@PutMapping("/{id}")
	public BranchOfficeDTO changeBranch (@PathVariable("id") Long id, @RequestBody BranchOfficeDTO branchDto );
	
}
