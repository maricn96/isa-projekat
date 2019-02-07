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

import com.ftn.isa.projekat.hotel.hotelApi.dto.PrihodiHotelaDTO;

@FeignClient(name="prihodiHotelaClient", url = "http://localhost:8092/api/hotel/prihodi")
public interface PrihodiHotelaClient {

	@GetMapping("/{id}")
	public PrihodiHotelaDTO getPrihod(@PathVariable("id") Long id);
	
	@GetMapping("/all")
	public List<PrihodiHotelaDTO> getAllPrihode();
	
	@PostMapping("/")
	public PrihodiHotelaDTO addCenovnik(@RequestBody PrihodiHotelaDTO dto);
	
	@DeleteMapping("/{id}")
	public PrihodiHotelaDTO deleteCenovnik(@PathVariable("id") Long id);
	
	@PutMapping("/{id}")
	public PrihodiHotelaDTO changeCenovnik(@PathVariable("id") Long id, @RequestBody PrihodiHotelaDTO dto );
	
	@GetMapping("/{id}/{datumOd}/{datumDo}")
	public ResponseEntity<Integer> getPrihodiHotela(@PathVariable("id") Long id, @PathVariable("datumOd") String datumOd, @PathVariable("datumDo") String datumDo);
	
	
	
	
}