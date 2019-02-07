package com.ftn.isa.projekat.avioCompany.avioCompanyApi.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ftn.isa.projekat.avioCompany.avioCompanyApi.dto.DestinationDTO;

@FeignClient(name="destinationClient", url = "http://localhost:8091/api/aviocompany/destination")
public interface DestinationClient
{
	@GetMapping("/{id}")
	public DestinationDTO getOneDestination(@PathVariable("id") Long id);
	
	@GetMapping("/all")
	public List<DestinationDTO> getAllDestinations();
	
	@PostMapping("/")
	public DestinationDTO addDestination(@RequestBody DestinationDTO dto);
	
	@DeleteMapping("/{id}")
	public DestinationDTO deleteDestination(@PathVariable("id") Long id);
	
	@PutMapping("/{id}")
	public DestinationDTO updateDestination(@PathVariable("id") Long id, @RequestBody DestinationDTO dto);
}
