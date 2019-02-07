package com.ftn.isa.projekat.avioCompany.avioCompanyApi.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ftn.isa.projekat.avioCompany.avioCompanyApi.dto.TicketDTO;

@FeignClient(name="destinationClient", url = "http://localhost:8091/api/aviocompany/ticket")
public interface TicketClient
{
	@GetMapping("/{id}")
	public TicketDTO getOneTicketById(@PathVariable("id") Long id);
	
	@GetMapping("/all")
	public List<TicketDTO> getAllTickets();
	
	@PostMapping("/")
	public TicketDTO addTicket(@RequestBody TicketDTO dto);
	
	@DeleteMapping("/{id}")
	public TicketDTO deleteTicket(@PathVariable("id") Long id);
	
	@PutMapping("/{id}")
	public TicketDTO changeTicket(@PathVariable("id") Long id, @RequestBody TicketDTO tdto);
}
