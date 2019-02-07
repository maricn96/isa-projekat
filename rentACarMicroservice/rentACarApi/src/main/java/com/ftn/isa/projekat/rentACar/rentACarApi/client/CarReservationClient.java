package com.ftn.isa.projekat.rentACar.rentACarApi.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ftn.isa.projekat.rentACar.rentACarApi.dto.CarReservationDTO;



@FeignClient(name="reservationClient", url = "http://localhost:8090/api/rentacar/reservation")
public interface CarReservationClient {

	
	@GetMapping("/{id}")
	public CarReservationDTO getOneReservationById (@PathVariable("id") Long id);
	
	@GetMapping("/all")
	public List<CarReservationDTO> getAllReservations();
	
	@PostMapping("/")
	public CarReservationDTO addReservation(@RequestBody CarReservationDTO dto);
	
	@DeleteMapping("/{id}")
	public CarReservationDTO deleteReservation(@PathVariable("id") Long id);
	
	@PutMapping("/{id}")
	public CarReservationDTO changeReservation (@PathVariable("id") Long id, @RequestBody CarReservationDTO reservationDTO );
	
	@PutMapping("/rate/{id}/{rating}")
	public CarReservationDTO rateReservation (@PathVariable("id") Long id, @PathVariable("rating") int rating);
	
	@PutMapping("/rateCar/{id}/{rating}")
	public CarReservationDTO rateCar (@PathVariable("id") Long id, @PathVariable("rating") int rating);
	
}
