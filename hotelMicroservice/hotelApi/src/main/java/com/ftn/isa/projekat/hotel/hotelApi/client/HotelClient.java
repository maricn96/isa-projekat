package com.ftn.isa.projekat.hotel.hotelApi.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ftn.isa.projekat.hotel.hotelApi.dto.HotelDTO;

@FeignClient(name = "hotelClient", url = "http://localhost:8092/api/hotel/hotel")
public interface HotelClient {
	
	@GetMapping("/{id}")
	public HotelDTO getHotel(@PathVariable("id") Long id);
	
	@GetMapping("/all")
	public List<HotelDTO> getAllHotels();
	
	@PostMapping("/")
	public HotelDTO addHotel(@RequestBody HotelDTO hotelDTO);
	
	@DeleteMapping("/{id}")
	public HotelDTO deleteHotel(@PathVariable("id") Long id);
	
	@PutMapping("/{id}")
	public HotelDTO changeHotel(@PathVariable("id") Long id, @RequestBody HotelDTO hotelDTO );
	
	@GetMapping("/filter/{hotelIliAdresa}/{datumDolaska}/{datumOdlaska}/{brojSoba}/{brojGostiju}")
	public List<HotelDTO> getFilteredHotels(@PathVariable("hotelIliAdresa") String hotelIliAdresa, @PathVariable("datumDolaska") String datumDolaska,
			@PathVariable("datumOdlaska") String datumOdlaska, @PathVariable("brojSoba") String brojSoba, @PathVariable("brojGostiju") String brojGostiju);
	
}
