package com.ftn.isa.projekat.hotel.hotelCore.Hotel.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import com.ftn.isa.projekat.hotel.hotelCore.Hotel.service.IHotelService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/hotel/hotel")
@Api(value = "hotel")
@CrossOrigin(origins = "http://localhost:3000")
public class HotelController {
	
	@Autowired
	IHotelService hotelService;
	
	@GetMapping("/{id}")
	@ApiOperation( value = "", notes = "", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "Not Found")})
	public ResponseEntity<HotelDTO> getHotel(@PathVariable("id") Long id){
		
		HotelDTO hotelDTO = hotelService.findOneById(id);
		return (hotelDTO.getId()!=null) ? new ResponseEntity<HotelDTO>(hotelDTO, HttpStatus.OK): new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping("/all")
	@ApiOperation( value = "", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity<List<HotelDTO>> getAllHotels(){
		
		//HttpHeaders head = new HttpHeaders();
		//head.add("Access-Control-Allow-Origin", "*");
		List<HotelDTO> hotels = hotelService.findAll();
		return(!hotels.isEmpty()) ? new ResponseEntity<List<HotelDTO>>(hotels, /*head,*/ HttpStatus.OK) : new ResponseEntity<>(/*head,*/ HttpStatus.NOT_FOUND);
		
	}
	
	@PostMapping("/")
	@ApiOperation( value = "", notes = "", httpMethod="POST", produces = "application/json", consumes = "application/json" )
	@ApiResponses( value = {
					@ApiResponse( code = 201 , message = "Created"),
					@ApiResponse( code = 400, message= "Bad request")})
	public ResponseEntity<HotelDTO> addHotel(@RequestBody HotelDTO hotelDTO){
		HttpHeaders head = new HttpHeaders();
		head.add("Access-Control-Allow-Origin", "*");
		HotelDTO hotelZaSnimanje = hotelService.save(hotelDTO);
		return(hotelZaSnimanje!=null) ? new ResponseEntity<HotelDTO>(hotelZaSnimanje, head, HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation( value = "", notes = "", httpMethod="DELETE")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity<HotelDTO> deleteHotel(@PathVariable("id") Long id){
		
		HotelDTO hotelZaBrisanje = hotelService.deleteById(id);
		return (hotelZaBrisanje.getId()!=null) ? new ResponseEntity<HotelDTO>(hotelZaBrisanje, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@PutMapping("/{id}")
	@ApiOperation( value= "", notes = "", httpMethod="PUT")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 400, message ="Bad Request")})
	public ResponseEntity<HotelDTO> changeHotel(@PathVariable("id") Long id, @RequestBody HotelDTO hotelDTO ){
		HotelDTO hotelZaIzmenu = hotelService.change(id, hotelDTO);
		return (hotelZaIzmenu.getId()!=null) ? new ResponseEntity<HotelDTO>(hotelZaIzmenu, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/filter/{hotelIliAdresa}/{datumDolaska}/{datumOdlaska}/{brojSoba}/{brojGostiju}")
	@ApiOperation( value= "", notes = "", httpMethod="GET")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 404, message ="Not Found")})
	public ResponseEntity<List<HotelDTO>> getFilteredHotels(@PathVariable("hotelIliAdresa") String hotelIliAdresa, /*@PathVariable("cenaMin") String cenaMin, @PathVariable("cenaMax") String cenaMax,*/  @PathVariable("datumDolaska") String datumDolaska,
			@PathVariable("datumOdlaska") String datumOdlaska, @PathVariable("brojSoba") String brojSoba, @PathVariable("brojGostiju") String brojGostiju) throws ParseException{
		
		List<HotelDTO> hotels = hotelService.filterHotel(hotelIliAdresa, /*cenaMin, cenaMax,*/ datumDolaska, datumOdlaska, brojGostiju, brojSoba);
		return(!hotels.isEmpty()) ? new ResponseEntity<List<HotelDTO>>(hotels, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);		
	}
	
	/*@GetMapping("/{id}")
	@ApiOperation( value = "", notes = "", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "Not Found")})
	public ResponseEntity<HotelDTO> getHotelCenovnike(@PathVariable("id") Long id){
		
		HotelDTO hotelDTO = hotelService.findOneById(id);
		return (hotelDTO.getId()!=null) ? new ResponseEntity<HotelDTO>(hotelDTO, HttpStatus.OK): new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}*/
	
}
