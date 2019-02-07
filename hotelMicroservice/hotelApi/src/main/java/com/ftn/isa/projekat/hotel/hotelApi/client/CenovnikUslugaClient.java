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

import com.ftn.isa.projekat.hotel.hotelApi.dto.CenovnikUslugaDTO;

@FeignClient(name="hotelskaSobaClient", url = "http://localhost:8092/api/hotel/cenovnik")
public interface CenovnikUslugaClient {
	
	@GetMapping("/{id}")
	public CenovnikUslugaDTO getCenovnik(@PathVariable("id") Long id);
	
	@GetMapping("/all")
	public List<CenovnikUslugaDTO> getAllCenovnici();
	
	@PostMapping("/")
	public CenovnikUslugaDTO addCenovnik(@RequestBody CenovnikUslugaDTO dto);
	
	@DeleteMapping("/{id}")
	public CenovnikUslugaDTO deleteCenovnik(@PathVariable("id") Long id);
	
	@PutMapping("/{id}")
	public CenovnikUslugaDTO changeCenovnik(@PathVariable("id") Long id, @RequestBody CenovnikUslugaDTO dto );
	
	@GetMapping("/cenovniciHotela/{id}")
	public List<CenovnikUslugaDTO> getCenovnikeHotela(@PathVariable("id") Long id);

}