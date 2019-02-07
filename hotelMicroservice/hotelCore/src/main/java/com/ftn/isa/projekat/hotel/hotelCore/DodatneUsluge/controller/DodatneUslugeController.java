package com.ftn.isa.projekat.hotel.hotelCore.DodatneUsluge.controller;

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
import com.ftn.isa.projekat.hotel.hotelApi.dto.DodatneUslugeDTO;
import com.ftn.isa.projekat.hotel.hotelCore.DodatneUsluge.service.IDodatneUslugeService;
import com.ftn.isa.projekat.hotel.hotelCore.Hotel.service.IHotelService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/hotel/dodatneUsluge")
@Api(value = "dodatneUsluge")
@CrossOrigin(origins = "http://localhost:3000")
public class DodatneUslugeController {
	
	@Autowired
	IDodatneUslugeService dodatneUslugeService;
	
	@Autowired
	IHotelService hotelService;
	
	@GetMapping("/{id}")
	@ApiOperation( value = "", notes = "", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "Not Found")})
	public ResponseEntity<DodatneUslugeDTO> getUsluga(@PathVariable("id") Long id){
		
		DodatneUslugeDTO dto = dodatneUslugeService.findOneById(id);
		return (dto.getId()!=null) ? new ResponseEntity<DodatneUslugeDTO>(dto, HttpStatus.OK): new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping("/all")
	@ApiOperation( value = "", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity<List<DodatneUslugeDTO>> getAllUsluge(){
		
		List<DodatneUslugeDTO> lista = dodatneUslugeService.findAll();
		return(!lista.isEmpty()) ? new ResponseEntity<List<DodatneUslugeDTO>>(lista, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@PostMapping("/")
	@ApiOperation( value = "", notes = "", httpMethod="POST", produces = "application/json", consumes = "application/json" )
	@ApiResponses( value = {
					@ApiResponse( code = 201 , message = "Created"),
					@ApiResponse( code = 400, message= "Bad request")})
	public ResponseEntity<DodatneUslugeDTO> addUsluga(@RequestBody DodatneUslugeDTO dto){
		DodatneUslugeDTO zaSnimanje = dodatneUslugeService.save(dto);
		return(zaSnimanje!=null) ? new ResponseEntity<DodatneUslugeDTO>(zaSnimanje, HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation( value = "", notes = "", httpMethod="DELETE")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity<DodatneUslugeDTO> deleteUsluge(@PathVariable("id") Long id){
		
		DodatneUslugeDTO zaBrisanje = dodatneUslugeService.deleteById(id);
		return (zaBrisanje.getId()!=null) ? new ResponseEntity<DodatneUslugeDTO>(zaBrisanje, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@PutMapping("/{id}")
	@ApiOperation( value= "", notes = "", httpMethod="PUT")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 400, message ="Bad Request")})
	public ResponseEntity<DodatneUslugeDTO> changeCenovnik(@PathVariable("id") Long id, @RequestBody DodatneUslugeDTO dto ){
		DodatneUslugeDTO zaIzmenu = dodatneUslugeService.change(id, dto);
		return (zaIzmenu.getId()!=null) ? new ResponseEntity<DodatneUslugeDTO>(zaIzmenu, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/uslugeHotela/{id}")
	@ApiOperation( value = "", notes = "", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "Not Found")})
	public ResponseEntity<List<DodatneUslugeDTO>> getUslugeHotela(@PathVariable("id") Long id){
		
		List<DodatneUslugeDTO> usluge = dodatneUslugeService.findDodatneUslugeHotela(id);
		return(!usluge.isEmpty()) ? new ResponseEntity<List<DodatneUslugeDTO>>(usluge, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}

}
