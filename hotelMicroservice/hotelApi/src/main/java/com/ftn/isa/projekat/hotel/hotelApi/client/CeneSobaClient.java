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

import com.ftn.isa.projekat.hotel.hotelApi.dto.CeneSobaDTO;

@FeignClient(name="hotelskaSobaClient", url = "http://localhost:8092/api/hotel/cene")
public interface CeneSobaClient {
	
	@GetMapping("/{id}")
	public CeneSobaDTO getCene(@PathVariable("id") Long id);
	
	@GetMapping("/all")
	public List<CeneSobaDTO> getAllCene();

	@GetMapping("/soba/{id}")
	public List<CeneSobaDTO> getAllRoomCene(@PathVariable("id") Long id);
	
	@PostMapping("/")
	public CeneSobaDTO addCenu(@RequestBody CeneSobaDTO dto);
	
	@DeleteMapping("/{id}")
	CeneSobaDTO deleteCenu(@PathVariable("id") Long id);
	
	@PutMapping("/{id}")
	public CeneSobaDTO changeCenu(@PathVariable("id") Long id, @RequestBody CeneSobaDTO dto );
	
}
