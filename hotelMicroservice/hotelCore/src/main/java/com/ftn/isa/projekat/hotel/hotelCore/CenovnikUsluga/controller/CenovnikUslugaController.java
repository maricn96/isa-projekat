package com.ftn.isa.projekat.hotel.hotelCore.CenovnikUsluga.controller;

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

import com.ftn.isa.projekat.hotel.hotelApi.dto.CenovnikUslugaDTO;
import com.ftn.isa.projekat.hotel.hotelApi.dto.HotelDTO;
import com.ftn.isa.projekat.hotel.hotelApi.dto.HotelskaSobaDTO;
import com.ftn.isa.projekat.hotel.hotelCore.CenovnikUsluga.service.CenovnikUslugaService;
import com.ftn.isa.projekat.hotel.hotelCore.CenovnikUsluga.service.ICenovnikUslugaService;
import com.ftn.isa.projekat.hotel.hotelCore.Hotel.service.IHotelService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/hotel/cenovnik")
@Api(value = "cenovnik")
@CrossOrigin(origins = "http://localhost:3000")
public class CenovnikUslugaController {
	
	@Autowired
	ICenovnikUslugaService cenovnikUslugaService;
	
	@Autowired
	IHotelService hotelService;
	
	@GetMapping("/{id}")
	@ApiOperation( value = "", notes = "", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "Not Found")})
	public ResponseEntity<CenovnikUslugaDTO> getCenovnik(@PathVariable("id") Long id){
		
		CenovnikUslugaDTO dto = cenovnikUslugaService.findOneById(id);
		return (dto.getId()!=null) ? new ResponseEntity<CenovnikUslugaDTO>(dto, HttpStatus.OK): new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping("/all")
	@ApiOperation( value = "", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity<List<CenovnikUslugaDTO>> getAllCenovnici(){
		
		List<CenovnikUslugaDTO> lista = cenovnikUslugaService.findAll();
		return(!lista.isEmpty()) ? new ResponseEntity<List<CenovnikUslugaDTO>>(lista, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@PostMapping("/")
	@ApiOperation( value = "", notes = "", httpMethod="POST", produces = "application/json", consumes = "application/json" )
	@ApiResponses( value = {
					@ApiResponse( code = 201 , message = "Created"),
					@ApiResponse( code = 400, message= "Bad request")})
	public ResponseEntity<CenovnikUslugaDTO> addCenovnik(@RequestBody CenovnikUslugaDTO dto){
		CenovnikUslugaDTO zaSnimanje = cenovnikUslugaService.save(dto);
		return(zaSnimanje!=null) ? new ResponseEntity<CenovnikUslugaDTO>(zaSnimanje, HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation( value = "", notes = "", httpMethod="DELETE")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity<CenovnikUslugaDTO> deleteCenovnik(@PathVariable("id") Long id){
		
		CenovnikUslugaDTO zaBrisanje = cenovnikUslugaService.deleteById(id);
		return (zaBrisanje.getId()!=null) ? new ResponseEntity<CenovnikUslugaDTO>(zaBrisanje, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@PutMapping("/{id}")
	@ApiOperation( value= "", notes = "", httpMethod="PUT")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 400, message ="Bad Request")})
	public ResponseEntity<CenovnikUslugaDTO> changeCenovnik(@PathVariable("id") Long id, @RequestBody CenovnikUslugaDTO dto ){
		CenovnikUslugaDTO zaIzmenu = cenovnikUslugaService.change(id, dto);
		return (zaIzmenu.getId()!=null) ? new ResponseEntity<CenovnikUslugaDTO>(zaIzmenu, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/cenovniciHotela/{id}")
	@ApiOperation( value = "", notes = "", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "Not Found")})
	public ResponseEntity<List<CenovnikUslugaDTO>> getCenovnikeHotela(@PathVariable("id") Long id){
		
		List<CenovnikUslugaDTO> cenovnici = cenovnikUslugaService.findCenovnikeHotela(id);
		return(!cenovnici.isEmpty()) ? new ResponseEntity<List<CenovnikUslugaDTO>>(cenovnici, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}

}
