package com.ftn.isa.projekat.purchases.purchasesCore.avioCompanyRating.controller;

import java.time.LocalDate;
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

import com.ftn.isa.projekat.purchases.purchasesApi.dto.AvioCompanyRatingDTO;
import com.ftn.isa.projekat.purchases.purchasesCore.avioCompanyRating.service.IAvioCompanyRatingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/purchases/avioCompanyRating")
@Api(value="avioCompanyRating")
@CrossOrigin(origins = "http://localhost:3000")
public class AvioCompanyRatingController {

	@Autowired
	IAvioCompanyRatingService service;

	@GetMapping("/{id}")
	@ApiOperation( value = "Finds one avio company rating.", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "Not Found")})
	public ResponseEntity<AvioCompanyRatingDTO> getOneAvioCompanyRatingById(@PathVariable("id") Long id){
		
		AvioCompanyRatingDTO dto = service.findOneById(id);
		
		return ( dto.getId()!=null)? new ResponseEntity<AvioCompanyRatingDTO>(dto,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping("/all")
	@ApiOperation( value = "Returns all ratings", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity<List<AvioCompanyRatingDTO>> getAllRentACarRatings(){
		
		List<AvioCompanyRatingDTO> dtos = service.findAll();
		
		return ( !dtos.isEmpty() )? new ResponseEntity<List<AvioCompanyRatingDTO>>(dtos,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@PostMapping("/")
	@ApiOperation( value = "Create new rating.", httpMethod="POST", produces = "application/json", consumes = "application/json" )
	@ApiResponses( value = {
					@ApiResponse( code = 201 , message = "Created"),
					@ApiResponse( code = 400, message= "Bad request")
	})
	public ResponseEntity<AvioCompanyRatingDTO> addAvioCompanyRating(@RequestBody AvioCompanyRatingDTO dto){
		
		AvioCompanyRatingDTO saved = service.save(dto);
		
		return ( saved!=null )? new ResponseEntity<AvioCompanyRatingDTO>(saved,HttpStatus.CREATED): new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation( value = "Delete one rating.", httpMethod="DELETE")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity<AvioCompanyRatingDTO> deleteAvioCompanyRating(@PathVariable("id") Long id){
		AvioCompanyRatingDTO dto = service.deleteById(id);
		
		return (dto.getId() != null ) ? new ResponseEntity<AvioCompanyRatingDTO>(dto,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/{id}")
	@ApiOperation( value= "Change rating", httpMethod="PUT")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 400, message ="Bad Request")})
	public ResponseEntity<AvioCompanyRatingDTO> changeAvioCompanyRating(@PathVariable("id") Long id, @RequestBody AvioCompanyRatingDTO ratingDto ){
		
		AvioCompanyRatingDTO dto = service.changeAvioCompanyRating(id, ratingDto);
	
	    return ( dto.getId() != null )? new ResponseEntity<AvioCompanyRatingDTO>(dto,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	
	@GetMapping("/getAverageRating/{id}/{dateFrom}/{dateTo}")
	public ResponseEntity<Double> getAverageRating(@PathVariable("id")Long avioService, @PathVariable("dateFrom") String dateFrom, @PathVariable("dateTo") String dateTo){
		
		Double averageRating = service.getAverageRating(avioService, LocalDate.parse(dateFrom), LocalDate.parse(dateTo));
		
		return (averageRating != -1.0 )? new ResponseEntity<Double>(averageRating, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
}