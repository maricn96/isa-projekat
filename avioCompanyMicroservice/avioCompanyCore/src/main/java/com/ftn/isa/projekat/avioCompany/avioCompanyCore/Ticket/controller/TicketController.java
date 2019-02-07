package com.ftn.isa.projekat.avioCompany.avioCompanyCore.Ticket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.isa.projekat.avioCompany.avioCompanyApi.dto.FlightDTO;
import com.ftn.isa.projekat.avioCompany.avioCompanyApi.dto.TicketDTO;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.Ticket.service.ITicketService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/aviocompany/ticket")
@Api("ticket")
public class TicketController
{
	
	@Autowired
	ITicketService service;
	
	@GetMapping("/{id}")
	@ApiOperation( value = "Finds one ticket.", notes = "Returns found ticket.", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "Not Found")})
	public ResponseEntity<TicketDTO> getOneTicketById (@PathVariable("id") Long id){
		
		TicketDTO dto = service.findOneById(id);
		
		return ( dto.getId()!=null)? new ResponseEntity<TicketDTO>(dto,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping("/all")
	@ApiOperation( value = "Returns all tickets.", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity<List<TicketDTO>> getAllTickets(){
		
		List<TicketDTO> dto = service.findAll();
		
		return ( !dto.isEmpty() )? new ResponseEntity<List<TicketDTO>>(dto,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@PostMapping("/")
	@ApiOperation( value = "Create a ticket.", notes = "Returns the ticket being saved.", httpMethod="POST", produces = "application/json", consumes = "application/json" )
	@ApiResponses( value = {
					@ApiResponse( code = 201 , message = "Created"),
					@ApiResponse( code = 400, message= "Bad request")
	})
	public ResponseEntity<TicketDTO> addTicket(@RequestBody TicketDTO dto){
		
		TicketDTO ticket = service.save(dto);
		
		return ( ticket!=null )? new ResponseEntity<TicketDTO>(ticket,HttpStatus.CREATED): new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation( value = "Delete a ticket.", notes = "Returns the ticket being deleted", httpMethod="DELETE")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity<TicketDTO> deleteTicket(@PathVariable("id") Long id)
	{
		TicketDTO dto = service.deleteById(id);
		
		return (dto.getId() != null ) ? new ResponseEntity<TicketDTO>(dto,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/{id}")
	@ApiOperation( value= "Change a ticket", notes = "Returns the ticket being changed", httpMethod="PUT")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 400, message ="Bad Request")})
	public ResponseEntity<TicketDTO> changeTicket (@PathVariable("id") Long id, @RequestBody TicketDTO tdto ){
		
		TicketDTO dto = service.changeTicket(id, tdto);
	
	    return ( dto.getId() != null )? new ResponseEntity<TicketDTO>(dto,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	
	/* (Y)
	 * Search tickets by price
	 */
	@GetMapping("/getbyprice/{bottomPrice}/{topPrice}")
	public ResponseEntity<List<TicketDTO>> getTicketByPrice(@PathVariable("bottomPrice") Float bottomPrice, @PathVariable("topPrice") Float topPrice)
	{
		List<TicketDTO> tickets = service.getTicketsByPrice(bottomPrice, topPrice);
		
		return (!tickets.isEmpty()) ? new ResponseEntity<List<TicketDTO>>(tickets, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	/*
	 * Cancel flight (returns true if operation is allowed)
	 */
	@GetMapping("/cancelflight/{id}")
	@ApiOperation(value = "Cancel flight.", notes = "Returns true if cancel is possible.", httpMethod = "GET")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
						   @ApiResponse(code = 404, message = "NOT_FOUND")
	})
	public ResponseEntity<Boolean> isFlightCanceled(@PathVariable("id") Long flightId)
	{
		Boolean canceled = service.cancelFlight(flightId);
		
		return (canceled) ? new ResponseEntity<Boolean>(canceled, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
	}
	
}
