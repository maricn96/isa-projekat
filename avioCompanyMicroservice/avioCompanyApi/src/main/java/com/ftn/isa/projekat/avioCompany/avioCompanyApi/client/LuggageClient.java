package com.ftn.isa.projekat.avioCompany.avioCompanyApi.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ftn.isa.projekat.avioCompany.avioCompanyApi.dto.LuggageDTO;

@FeignClient(name="destinationClient", url = "http://localhost:8091/api/aviocompany/luggage")
public interface LuggageClient 
{
	@GetMapping("/{id}")
	public LuggageDTO getOneLuggageById(@PathVariable("id") Long id);
	
	@GetMapping("/all")
	public List<LuggageDTO> getAllLuggages();
	
	@PostMapping("/")
	public LuggageDTO addLuggage(@RequestBody LuggageDTO dto);
	
	@DeleteMapping("/{id}")
	public LuggageDTO deleteLuggage(@PathVariable("id") Long id);
	
	@PutMapping("/{id}")
	public LuggageDTO changeLuggage(@PathVariable("id") Long id, @RequestBody LuggageDTO luggageDto);
}
