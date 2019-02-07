package com.ftn.isa.projekat.hotel.hotelCore.VanredneCeneNocenja.controller;

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

import com.ftn.isa.projekat.hotel.hotelApi.dto.HotelDTO;
import com.ftn.isa.projekat.hotel.hotelApi.dto.VanredneCeneNocenjaDTO;
import com.ftn.isa.projekat.hotel.hotelCore.VanredneCeneNocenja.service.IVanredneCeneNocenjaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/hotel/vanredneCene")
@Api(value = "vanredneCene")
@CrossOrigin(origins = "http://localhost:3000")
public class VanredneCeneNocenjaController {
	
	@Autowired
	IVanredneCeneNocenjaService vanredneCeneNocenjaService;
	
	@GetMapping("/{id}")
	@ApiOperation( value = "", notes = "", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "Not Found")})
	public ResponseEntity<VanredneCeneNocenjaDTO> getCene(@PathVariable("id") Long id){
		
		VanredneCeneNocenjaDTO ceneDTO = vanredneCeneNocenjaService.findOneById(id);
		return (ceneDTO.getId()!=null) ? new ResponseEntity<VanredneCeneNocenjaDTO>(ceneDTO, HttpStatus.OK): new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping("/all")
	@ApiOperation( value = "", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity<List<VanredneCeneNocenjaDTO>> getAllCene(){
		
		List<VanredneCeneNocenjaDTO> cene = vanredneCeneNocenjaService.findAll();
		return(!cene.isEmpty()) ? new ResponseEntity<List<VanredneCeneNocenjaDTO>>(cene, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@PostMapping("/")
	@ApiOperation( value = "", notes = "", httpMethod="POST", produces = "application/json", consumes = "application/json" )
	@ApiResponses( value = {
					@ApiResponse( code = 201 , message = "Created"),
					@ApiResponse( code = 400, message= "Bad request")})
	public ResponseEntity<VanredneCeneNocenjaDTO> addCenu(@RequestBody VanredneCeneNocenjaDTO cenaDTO){
		VanredneCeneNocenjaDTO cenaZaSnimanje = vanredneCeneNocenjaService.save(cenaDTO);
		return(cenaZaSnimanje!=null) ? new ResponseEntity<VanredneCeneNocenjaDTO>(cenaZaSnimanje, HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation( value = "", notes = "", httpMethod="DELETE")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity<VanredneCeneNocenjaDTO> deleteCena(@PathVariable("id") Long id){
		
		VanredneCeneNocenjaDTO cenaZaBrisanje = vanredneCeneNocenjaService.deleteById(id);
		return (cenaZaBrisanje.getId()!=null) ? new ResponseEntity<VanredneCeneNocenjaDTO>(cenaZaBrisanje, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@PutMapping("/{id}")
	@ApiOperation( value= "", notes = "", httpMethod="PUT")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 400, message ="Bad Request")})
	public ResponseEntity<VanredneCeneNocenjaDTO> changeCenu(@PathVariable("id") Long id, @RequestBody VanredneCeneNocenjaDTO cenaDTO ){
		VanredneCeneNocenjaDTO cenaZaIzmenu = vanredneCeneNocenjaService.change(id, cenaDTO);
		return (cenaZaIzmenu.getId()!=null) ? new ResponseEntity<VanredneCeneNocenjaDTO>(cenaZaIzmenu, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
