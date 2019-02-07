package com.ftn.isa.projekat.hotel.hotelCore.CeneSoba.controller;

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

import com.ftn.isa.projekat.hotel.hotelApi.dto.CeneSobaDTO;
import com.ftn.isa.projekat.hotel.hotelApi.dto.CenovnikUslugaDTO;
import com.ftn.isa.projekat.hotel.hotelCore.CeneSoba.service.ICeneSobaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/hotel/cene")
@Api(value = "cene")
@CrossOrigin(origins = "http://localhost:3000")
public class CeneSobaController {
	
	@Autowired
	ICeneSobaService ceneSobaService;
	
	@GetMapping("/{id}")
	@ApiOperation( value = "", notes = "", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "Not Found")})
	public ResponseEntity<CeneSobaDTO> getCene(@PathVariable("id") Long id){
		
		CeneSobaDTO dto = ceneSobaService.findOneById(id);
		return (dto.getId()!=null) ? new ResponseEntity<CeneSobaDTO>(dto, HttpStatus.OK): new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping("/all")
	@ApiOperation( value = "", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity<List<CeneSobaDTO>> getAllCene(){
		
		List<CeneSobaDTO> lista = ceneSobaService.findAll();
		return(!lista.isEmpty()) ? new ResponseEntity<List<CeneSobaDTO>>(lista, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping("/soba/{id}")
	@ApiOperation( value = "", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity<List<CeneSobaDTO>> getAllRoomCene(@PathVariable("id") Long id){
		
		List<CeneSobaDTO> lista = ceneSobaService.findByRoomId(id);
		return(!lista.isEmpty()) ? new ResponseEntity<List<CeneSobaDTO>>(lista, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@PostMapping("/")
	@ApiOperation( value = "", notes = "", httpMethod="POST", produces = "application/json", consumes = "application/json" )
	@ApiResponses( value = {
					@ApiResponse( code = 201 , message = "Created"),
					@ApiResponse( code = 400, message= "Bad request")})
	public ResponseEntity<CeneSobaDTO> addCenu(@RequestBody CeneSobaDTO dto){
		CeneSobaDTO zaSnimanje = ceneSobaService.save(dto);
		return(zaSnimanje!=null) ? new ResponseEntity<CeneSobaDTO>(zaSnimanje, HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation( value = "", notes = "", httpMethod="DELETE")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity<CeneSobaDTO> deleteCenu(@PathVariable("id") Long id){
		
		CeneSobaDTO zaBrisanje = ceneSobaService.deleteById(id);
		return (zaBrisanje.getId()!=null) ? new ResponseEntity<CeneSobaDTO>(zaBrisanje, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@PutMapping("/{id}")
	@ApiOperation( value= "", notes = "", httpMethod="PUT")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 400, message ="Bad Request")})
	public ResponseEntity<CeneSobaDTO> changeCenu(@PathVariable("id") Long id, @RequestBody CeneSobaDTO dto ){
		CeneSobaDTO zaIzmenu = ceneSobaService.change(id, dto);
		return (zaIzmenu.getId()!=null) ? new ResponseEntity<CeneSobaDTO>(zaIzmenu, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
