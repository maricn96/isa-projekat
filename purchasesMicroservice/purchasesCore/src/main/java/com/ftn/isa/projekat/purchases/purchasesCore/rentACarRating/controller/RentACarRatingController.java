package com.ftn.isa.projekat.purchases.purchasesCore.rentACarRating.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.websocket.server.PathParam;

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

import com.ftn.isa.projekat.purchases.purchasesApi.dto.RentACarRatingDTO;
import com.ftn.isa.projekat.purchases.purchasesCore.rentACarRating.service.IRentACarRatingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/purchases/rentACarRating")
@Api(value="rentACarRating")
@CrossOrigin(origins = "http://localhost:3000")
public class RentACarRatingController {

	@Autowired
	IRentACarRatingService rentACarRatingService;

	@GetMapping("/{id}")
	@ApiOperation( value = "Finds one rent a car rating.", notes = "Returns found rent a car rating.", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "Not Found")})
	public ResponseEntity<RentACarRatingDTO> getOneRentACarRatingById (@PathVariable("id") Long id){
		
		RentACarRatingDTO rentRatingDto = rentACarRatingService.findOneById(id);
		
		return ( rentRatingDto.getId()!=null)? new ResponseEntity<RentACarRatingDTO>(rentRatingDto,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping("/all")
	@ApiOperation( value = "Returns all rent a car ratings", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity<List<RentACarRatingDTO>> getAllRentACarRatings(){
		
		List<RentACarRatingDTO> rentRatings = rentACarRatingService.findAll();
		
		return ( !rentRatings.isEmpty() )? new ResponseEntity<List<RentACarRatingDTO>>(rentRatings,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@PostMapping("/{date}")
	@ApiOperation( value = "Create a rent a car rating.", notes = "Returns the rent a car rating being saved.", httpMethod="POST", produces = "application/json", consumes = "application/json" )
	@ApiResponses( value = {
					@ApiResponse( code = 201 , message = "Created"),
					@ApiResponse( code = 400, message= "Bad request")
	})
	public ResponseEntity<RentACarRatingDTO> addRentACarRating(@PathVariable("date") String date, @RequestBody RentACarRatingDTO dto){
		
		RentACarRatingDTO savedRating = rentACarRatingService.save(dto,LocalDateTime.parse(date));
		
		return ( savedRating!=null )? new ResponseEntity<RentACarRatingDTO>(savedRating,HttpStatus.CREATED): new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation( value = "Delete a rent a car rating.", notes = "Returns the rent a car rating being deleted", httpMethod="DELETE")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity<RentACarRatingDTO> deleteRentACarRating(@PathVariable("id") Long id){
		RentACarRatingDTO deletedRentACarRatingDTO = rentACarRatingService.deleteById(id);
		
		return (deletedRentACarRatingDTO.getId() != null ) ? new ResponseEntity<RentACarRatingDTO>(deletedRentACarRatingDTO,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/{id}")
	@ApiOperation( value= "Change a rent a car rating", notes = "Returns the rent a car rating being changed", httpMethod="PUT")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 400, message ="Bad Request")})
	public ResponseEntity<RentACarRatingDTO> changeRentACarRating (@PathVariable("id") Long id, @RequestBody RentACarRatingDTO ratingDto ){
		
		RentACarRatingDTO ratingToEdit = rentACarRatingService.changeRentACarRating(id, ratingDto);
	
	    return ( ratingToEdit.getId() != null )? new ResponseEntity<RentACarRatingDTO>(ratingToEdit,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	
	@GetMapping("/getAverageRating/{id}")
	public ResponseEntity<Double> getAverageRating (@PathVariable("id")Long rentService){
		
		Double averageRating = rentACarRatingService.getAverageRating(rentService);
		
		return (averageRating != -1.0 )? new ResponseEntity<Double>(averageRating, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
}
