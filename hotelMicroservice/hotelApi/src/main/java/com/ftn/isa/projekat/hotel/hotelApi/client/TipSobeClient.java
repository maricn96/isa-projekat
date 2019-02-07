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

import com.ftn.isa.projekat.hotel.hotelApi.dto.TipSobeDTO;

@FeignClient(name="tipSobeClient", url = "http://localhost:8092/api/hotel/tipSobe")
public interface TipSobeClient {
	
	@GetMapping("/{id}")
	public TipSobeDTO getTip(@PathVariable("id") Long id);
	
	@GetMapping("/all")
	public List<TipSobeDTO> getAllTipove();
	
	@PostMapping("/")
	public TipSobeDTO addTip(@RequestBody TipSobeDTO tipDTO);
	
	@DeleteMapping("/{id}")
	public TipSobeDTO deleteTip(@PathVariable("id") Long id);
	
	@PutMapping("/{id}")
	public TipSobeDTO changeTip(@PathVariable("id") Long id, @RequestBody TipSobeDTO tipDTO );
	
	@GetMapping("/all/{id}")
	public List<TipSobeDTO> getAllTipoveHotela(@PathVariable("id") Long id);

}
