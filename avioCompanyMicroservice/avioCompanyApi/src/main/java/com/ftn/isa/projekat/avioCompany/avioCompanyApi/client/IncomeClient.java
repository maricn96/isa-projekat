package com.ftn.isa.projekat.avioCompany.avioCompanyApi.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ftn.isa.projekat.avioCompany.avioCompanyApi.dto.IncomeDTO;

@FeignClient(name="destinationClient", url = "http://localhost:8091/api/aviocompany/income")
public interface IncomeClient
{
	@GetMapping("/{id}")
	public IncomeDTO getOneIncomeById(@PathVariable("id") Long id);
	
	@GetMapping("/all")
	public List<IncomeDTO> getAllIncomes();
	
	@PostMapping("/")
	public IncomeDTO addIncome(@RequestBody IncomeDTO dto);
	
	@DeleteMapping("/{id}")
	public IncomeDTO deleteIncome(@PathVariable("id") Long id);
	
	@PutMapping("/{id}")
	public IncomeDTO changeIncome(@PathVariable("id") Long id, @RequestBody IncomeDTO dto);
}
