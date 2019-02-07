package com.ftn.isa.projekat.hotel.hotelApi.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ftn.isa.projekat.hotel.hotelApi.dto.HotelskaSobaDTO;



@FeignClient(name="hotelskaSobaClient", url = "http://localhost:8092/api/hotel/hotelskaSoba")
public interface HotelskaSobaClient {
	
		@GetMapping("/{id}")
		public HotelskaSobaDTO getHotelskaSoba(@PathVariable("id") Long id);
		
		@GetMapping("/all")
		public List<HotelskaSobaDTO> getAllHotelRooms();
		
		@PostMapping("/")
		public HotelskaSobaDTO addHotelRoom(@RequestBody HotelskaSobaDTO sobaDTO);
		
		@DeleteMapping("/{id}")
		public HotelskaSobaDTO deleteHotelRoom(@PathVariable("id") Long id);
		
		@PutMapping("/{id}")
		public HotelskaSobaDTO changeHotelRoom(@PathVariable("id") Long id, @RequestBody HotelskaSobaDTO sobaDTO );
		
		@GetMapping("/all/{id}")
		public List<HotelskaSobaDTO> getAllHotelRoomsByHotelId(@PathVariable("id") Long id);
		
		@GetMapping("/discount/{id}")
		public List<HotelskaSobaDTO> getAllDiscountedHotelRooms(@PathVariable("id") Long id);
}
