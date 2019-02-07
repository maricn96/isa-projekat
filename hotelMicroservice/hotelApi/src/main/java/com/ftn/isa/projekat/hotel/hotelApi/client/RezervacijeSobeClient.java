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

import com.ftn.isa.projekat.hotel.hotelApi.dto.HotelskaSobaDTO;
import com.ftn.isa.projekat.hotel.hotelApi.dto.RezervacijeSobeDTO;

@FeignClient(name="rezervacijeSobeClient", url = "http://localhost:8092/api/hotel/rezervacije")
public interface RezervacijeSobeClient {
	
	@GetMapping("/{id}")
	public RezervacijeSobeDTO getRezervaciju(@PathVariable("id") Long id);
	
	@GetMapping("/all")
	public List<RezervacijeSobeDTO> getAllRezervacije();
	
	@PostMapping("/")
	public RezervacijeSobeDTO addRezervaciju(@RequestBody RezervacijeSobeDTO dto);
	
	@DeleteMapping("/{id}")
	public RezervacijeSobeDTO deleteRezervaciju(@PathVariable("id") Long id);
	
	@PutMapping("/{id}")
	public RezervacijeSobeDTO changeCenovnik(@PathVariable("id") Long id, @RequestBody RezervacijeSobeDTO dto );
	
	@GetMapping("/{id}/{datumOd}/{datumDo}")
	public HotelskaSobaDTO getFreeRooms(@PathVariable("id") Long id, @PathVariable("datumOd") String datumOd, @PathVariable("datumDo") String datumDo);
	
	@GetMapping("/{id}/{datumOd}")
	public Integer getDnevnuPosecenost(@PathVariable("id") Long id, @PathVariable("datumOd") String datumOd);
	
	@GetMapping("/{id}/{datumOd}/nedelja")
	public Integer getNedeljnuPosecenost(@PathVariable("id") Long id, @PathVariable("datumOd") String datumOd);
	
	@GetMapping("/{id}/{datumOd}/mesec")
	public Integer getMesecnuPosecenost(@PathVariable("id") Long id, @PathVariable("datumOd") String datumOd);
	
	
}