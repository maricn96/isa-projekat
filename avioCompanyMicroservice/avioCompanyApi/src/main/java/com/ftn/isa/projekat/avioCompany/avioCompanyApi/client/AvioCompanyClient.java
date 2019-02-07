package com.ftn.isa.projekat.avioCompany.avioCompanyApi.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ftn.isa.projekat.avioCompany.avioCompanyApi.dto.AvioCompanyDTO;

@FeignClient(name="avioCompanyClient", url = "http://localhost:8091/api/aviocompany/company")
public interface AvioCompanyClient
{
	@GetMapping("/{id}")
	public AvioCompanyDTO getOneAvioCompanyById(@PathVariable("id") Long id);
	
	@GetMapping("/all")
	public List<AvioCompanyDTO> getAllAvioCompanies();
	
	@PostMapping("/")
	public AvioCompanyDTO addAvioCompany(@RequestBody AvioCompanyDTO dto);
	
	@DeleteMapping("/{id}")
	public AvioCompanyDTO deleteAvioCompany(@PathVariable("id") Long id);
	
	@PutMapping("/{id}")
	public AvioCompanyDTO changeAvioCompany(@PathVariable("id") Long id, @RequestBody AvioCompanyDTO avioDto);
}
