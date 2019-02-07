package com.ftn.isa.projekat.hotel.hotelApi.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ftn.isa.projekat.hotel.hotelApi.dto.DodatneUslugeDTO;

@FeignClient(name="dodatnaUslugaClient", url = "http://localhost:8092/api/hotel/dodatneUsluge")
public interface DodatneUslugeClient {

	@GetMapping("/{id}")
	public DodatneUslugeDTO getUsluga(@PathVariable("id") Long id);
	
	@GetMapping("/all")
	public List<DodatneUslugeDTO> getAllUsluge();
	
	@PostMapping("/")
	public DodatneUslugeDTO addUsluga(@RequestBody DodatneUslugeDTO dto);
	
	@DeleteMapping("/{id}")
	public DodatneUslugeDTO deleteUsluge(@PathVariable("id") Long id);
	
	@PutMapping("/{id}")
	public DodatneUslugeDTO changeCenovnik(@PathVariable("id") Long id, @RequestBody DodatneUslugeDTO dto );
	
	@GetMapping("/uslugeHotela/{id}")
	public List<DodatneUslugeDTO> getUslugeHotela(@PathVariable("id") Long id);
	
}
