package com.ftn.isa.projekat.hotel.hotelCore.TipSobe.controller;

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

import com.ftn.isa.projekat.hotel.hotelApi.dto.TipSobeDTO;
import com.ftn.isa.projekat.hotel.hotelCore.TipSobe.service.ITipSobeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/hotel/tipSobe")
@Api(value = "tipSobe")
@CrossOrigin(origins = "http://localhost:3000")
public class TipSobeController {
	
	@Autowired
	ITipSobeService tipSobeService;
	
	@GetMapping("/{id}")
	@ApiOperation( value = "", notes = "", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "Not Found")})
	public ResponseEntity<TipSobeDTO> getTip(@PathVariable("id") Long id){
		
		TipSobeDTO tipDTO = tipSobeService.findOneById(id);
		return (tipDTO.getId()!=null) ? new ResponseEntity<TipSobeDTO>(tipDTO, HttpStatus.OK): new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping("/all")
	@ApiOperation( value = "", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity<List<TipSobeDTO>> getAllTipove(){
		
		List<TipSobeDTO> tipovi = tipSobeService.findAll();
		return(!tipovi.isEmpty()) ? new ResponseEntity<List<TipSobeDTO>>(tipovi, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@PostMapping("/")
	@ApiOperation( value = "", notes = "", httpMethod="POST", produces = "application/json", consumes = "application/json" )
	@ApiResponses( value = {
					@ApiResponse( code = 201 , message = "Created"),
					@ApiResponse( code = 400, message= "Bad request")})
	public ResponseEntity<TipSobeDTO> addTip(@RequestBody TipSobeDTO tipDTO){
		TipSobeDTO tipZaSnimanje = tipSobeService.save(tipDTO);
		return(tipZaSnimanje!=null) ? new ResponseEntity<TipSobeDTO>(tipZaSnimanje, HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation( value = "", notes = "", httpMethod="DELETE")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity<TipSobeDTO> deleteTip(@PathVariable("id") Long id){
		
		TipSobeDTO tipZaBrisanje = tipSobeService.deleteById(id);
		return (tipZaBrisanje.getId()!=null) ? new ResponseEntity<TipSobeDTO>(tipZaBrisanje, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@PutMapping("/{id}")
	@ApiOperation( value= "", notes = "", httpMethod="PUT")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 400, message ="Bad Request")})
	public ResponseEntity<TipSobeDTO> changeTip(@PathVariable("id") Long id, @RequestBody TipSobeDTO tipDTO ){
		TipSobeDTO tipZaIzmenu = tipSobeService.change(id, tipDTO);
		return (tipZaIzmenu.getId()!=null) ? new ResponseEntity<TipSobeDTO>(tipZaIzmenu, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/all/{id}")
	@ApiOperation( value = "", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity<List<TipSobeDTO>> getAllTipoveHotela(@PathVariable("id") Long id){
		
		List<TipSobeDTO> tipovi = tipSobeService.findTypeByHotelId(id);
		return(!tipovi.isEmpty()) ? new ResponseEntity<List<TipSobeDTO>>(tipovi, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}

}
