package com.ftn.isa.projekat.purchases.purchasesCore.ticketRating.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.isa.projekat.purchases.purchasesApi.dto.CarRatingDTO;
import com.ftn.isa.projekat.purchases.purchasesApi.dto.TicketRatingDTO;
import com.ftn.isa.projekat.purchases.purchasesCore.ticketRating.service.ITicketRatingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/purchases/ticketRating")
@Api(value="Rating")
@CrossOrigin(origins = "http://localhost:3000")
public class TicketRatingController {

	@Autowired
	ITicketRatingService ticketService;
	
	
	@GetMapping("/{id}")
	@ApiOperation( value = "Finds one rating.", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "Not Found")})
	public ResponseEntity<TicketRatingDTO> getOneTicketRatingById (@PathVariable("id") Long id){
		
		TicketRatingDTO dto = ticketService.findOneById(id);
		
		return ( dto.getId()!=null)? new ResponseEntity<TicketRatingDTO>(dto,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping("/all")
	@ApiOperation( value = "Returns all ratings", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity<List<TicketRatingDTO>> getAllTicketRating(){
		
		List<TicketRatingDTO> ticketRatings = ticketService.findAll();
		
		return ( !ticketRatings.isEmpty() )? new ResponseEntity<List<TicketRatingDTO>>(ticketRatings,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@PostMapping("/")
	@ApiOperation( value = "Create new rating for ticket.", httpMethod="POST", produces = "application/json", consumes = "application/json" )
	@ApiResponses( value = {
					@ApiResponse( code = 201 , message = "Created"),
					@ApiResponse( code = 400, message= "Bad request")
	})
	public ResponseEntity<TicketRatingDTO> addTicketRating(@RequestBody TicketRatingDTO dto){
		
		TicketRatingDTO savedTicketRating = ticketService.save(dto);
		
		return ( savedTicketRating!=null )? new ResponseEntity<TicketRatingDTO>(savedTicketRating,HttpStatus.CREATED): new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation( value = "Delete rating.", httpMethod="DELETE")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity<TicketRatingDTO> deleteTicketRating(@PathVariable("id") Long id){
		TicketRatingDTO deletedCarRatingDTO = ticketService.deleteById(id);
		
		return (deletedCarRatingDTO.getId() != null ) ? new ResponseEntity<TicketRatingDTO>(deletedCarRatingDTO,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/{id}")
	@ApiOperation( value= "Change rating", httpMethod="PUT")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 400, message ="Bad Request")})
	public ResponseEntity<TicketRatingDTO> changeTicketRating (@PathVariable("id") Long id, @RequestBody TicketRatingDTO ticketRatingDto ){
		
		TicketRatingDTO ticketRatingToEdit = ticketService.changeTicketRating(id, ticketRatingDto);
	
	    return ( ticketRatingToEdit.getId() != null )? new ResponseEntity<TicketRatingDTO>(ticketRatingToEdit,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}	
	
	
}
