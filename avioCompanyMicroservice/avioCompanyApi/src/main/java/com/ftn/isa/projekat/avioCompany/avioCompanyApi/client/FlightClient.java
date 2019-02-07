package com.ftn.isa.projekat.avioCompany.avioCompanyApi.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ftn.isa.projekat.avioCompany.avioCompanyApi.dto.FlightDTO;

@FeignClient(name="destinationClient", url = "http://localhost:8091/api/aviocompany/flight")
public interface FlightClient 
{
	@GetMapping("/{id}")
	public FlightDTO getSingleFlight(@PathVariable("id") Long id);
	
	@GetMapping("/all")
	public List<FlightDTO> getAllFlights();
	
	@PostMapping("/")
	public FlightDTO addNewFlight(@RequestBody FlightDTO dto);
	
	@DeleteMapping("/{id}")
	public FlightDTO deleteFlight(@PathVariable("id") Long id);
	
	@PutMapping("/{id}")
	public FlightDTO updateFlight(@PathVariable("id") Long id, @RequestBody FlightDTO dto);
}
