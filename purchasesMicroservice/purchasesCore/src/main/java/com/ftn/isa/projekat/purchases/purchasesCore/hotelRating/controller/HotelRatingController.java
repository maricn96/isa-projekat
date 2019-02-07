package com.ftn.isa.projekat.purchases.purchasesCore.hotelRating.controller;

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

import com.ftn.isa.projekat.purchases.purchasesApi.dto.HotelRatingDTO;
import com.ftn.isa.projekat.purchases.purchasesCore.hotelRating.service.IHotelRatingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/hotel/hotelRating")
@Api(value = "hotelRating")
@CrossOrigin(origins = "http://localhost:3000")
public class HotelRatingController {
	
	@Autowired
	IHotelRatingService hotelRatingService;
	
	@GetMapping("/{id}")
	@ApiOperation( value = "", notes = "", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "Not Found")})
	public ResponseEntity<HotelRatingDTO> getRating(@PathVariable("id") Long id){
		
		HotelRatingDTO dto = hotelRatingService.findOneById(id);
		return (dto.getId()!=null) ? new ResponseEntity<HotelRatingDTO>(dto, HttpStatus.OK): new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping("/all")
	@ApiOperation( value = "", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity<List<HotelRatingDTO>> getAllRatings(){
		
		List<HotelRatingDTO> lista = hotelRatingService.findAll();
		return(!lista.isEmpty()) ? new ResponseEntity<List<HotelRatingDTO>>(lista, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@PostMapping("/")
	@ApiOperation( value = "", notes = "", httpMethod="POST", produces = "application/json", consumes = "application/json" )
	@ApiResponses( value = {
					@ApiResponse( code = 201 , message = "Created"),
					@ApiResponse( code = 400, message= "Bad request")})
	public ResponseEntity<HotelRatingDTO> addRating(@RequestBody HotelRatingDTO dto){
		HotelRatingDTO zaSnimanje = hotelRatingService.save(dto);
		return(zaSnimanje!=null) ? new ResponseEntity<HotelRatingDTO>(zaSnimanje, HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation( value = "", notes = "", httpMethod="DELETE")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity<HotelRatingDTO> deleteRating(@PathVariable("id") Long id){
		
		HotelRatingDTO zaBrisanje = hotelRatingService.deleteById(id);
		return (zaBrisanje.getId()!=null) ? new ResponseEntity<HotelRatingDTO>(zaBrisanje, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@PutMapping("/{id}")
	@ApiOperation( value= "", notes = "", httpMethod="PUT")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 400, message ="Bad Request")})
	public ResponseEntity<HotelRatingDTO> changeRating(@PathVariable("id") Long id, @RequestBody HotelRatingDTO dto ){
		HotelRatingDTO zaIzmenu = hotelRatingService.change(id, dto);
		return (zaIzmenu.getId()!=null) ? new ResponseEntity<HotelRatingDTO>(zaIzmenu, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("average/{id}")
	@ApiOperation( value = "", notes = "", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "Not Found")})
	public ResponseEntity<Double> getHotelAverageRating(@PathVariable("id") Long id){
		
		Double rating = hotelRatingService.getHotelAverageRating(id);
		
		if(rating != null) {
			return new ResponseEntity<Double>(rating, HttpStatus.OK);
		}else {
			return new ResponseEntity<Double>(HttpStatus.NOT_FOUND);
		}
	}
}
