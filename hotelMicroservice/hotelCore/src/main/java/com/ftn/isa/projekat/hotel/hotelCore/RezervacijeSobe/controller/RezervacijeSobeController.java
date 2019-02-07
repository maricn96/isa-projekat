package com.ftn.isa.projekat.hotel.hotelCore.RezervacijeSobe.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
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
import com.ftn.isa.projekat.hotel.hotelApi.dto.HotelskaSobaDTO;
import com.ftn.isa.projekat.hotel.hotelApi.dto.RezervacijeSobeDTO;
import com.ftn.isa.projekat.hotel.hotelCore.RezervacijeSobe.service.IRezervacijeSobeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/hotel/rezervacije")
@Api(value = "rezervacije")
@CrossOrigin(origins = "http://localhost:3000")
public class RezervacijeSobeController {
	
	@Autowired
	IRezervacijeSobeService rezervacijeSobeService;
	
	@GetMapping("/{id}")
	@ApiOperation( value = "", notes = "", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "Not Found")})
	public ResponseEntity<RezervacijeSobeDTO> getRezervaciju(@PathVariable("id") Long id){
		
		RezervacijeSobeDTO dto = rezervacijeSobeService.findOneById(id);
		return (dto.getId()!=null) ? new ResponseEntity<RezervacijeSobeDTO>(dto, HttpStatus.OK): new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping("/all")
	@ApiOperation( value = "", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity<List<RezervacijeSobeDTO>> getAllRezervacije(){
		
		List<RezervacijeSobeDTO> lista = rezervacijeSobeService.findAll();
		return(!lista.isEmpty()) ? new ResponseEntity<List<RezervacijeSobeDTO>>(lista, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@PostMapping("/")
	@ApiOperation( value = "", notes = "", httpMethod="POST", produces = "application/json", consumes = "application/json" )
	@ApiResponses( value = {
					@ApiResponse( code = 201 , message = "Created"),
					@ApiResponse( code = 400, message= "Bad request")})
	public ResponseEntity<RezervacijeSobeDTO> addRezervaciju(@RequestBody RezervacijeSobeDTO dto){
		RezervacijeSobeDTO zaSnimanje = rezervacijeSobeService.save(dto);
		return(zaSnimanje!=null) ? new ResponseEntity<RezervacijeSobeDTO>(zaSnimanje, HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation( value = "", notes = "", httpMethod="DELETE")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity<RezervacijeSobeDTO> deleteRezervaciju(@PathVariable("id") Long id){
		
		RezervacijeSobeDTO zaBrisanje = rezervacijeSobeService.deleteById(id);
		return (zaBrisanje.getId()!=null) ? new ResponseEntity<RezervacijeSobeDTO>(zaBrisanje, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@PutMapping("/{id}")
	@ApiOperation( value= "", notes = "", httpMethod="PUT")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 400, message ="Bad Request")})
	public ResponseEntity<RezervacijeSobeDTO> changeCenovnik(@PathVariable("id") Long id, @RequestBody RezervacijeSobeDTO dto ){
		RezervacijeSobeDTO zaIzmenu = rezervacijeSobeService.change(id, dto);
		return (zaIzmenu.getId()!=null) ? new ResponseEntity<RezervacijeSobeDTO>(zaIzmenu, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/{id}/{datumOd}/{datumDo}")
	@ApiOperation( value = "", notes = "", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "Not Found")})
	public ResponseEntity<List<HotelskaSobaDTO>> getFreeRooms(@PathVariable("id") Long id, @PathVariable("datumOd") String datumOd, @PathVariable("datumDo") String datumDo) throws ParseException{
		
		Date dateOd = new SimpleDateFormat("yyyy-MM-dd").parse(datumOd);
		Date dateDo = new SimpleDateFormat("yyyy-MM-dd").parse(datumDo);
		List<HotelskaSobaDTO> lista = rezervacijeSobeService.getFreeRooms(id, dateOd, dateDo);
		return(!lista.isEmpty()) ? new ResponseEntity<List<HotelskaSobaDTO>>(lista, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping("/{id}/{datumOd}/{datumDo}/{cenaMin}/{cenaMax}")
	@ApiOperation( value = "", notes = "", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "Not Found")})
	public ResponseEntity<List<HotelskaSobaDTO>> getFreeRoomsPrice(@PathVariable("id") Long id, @PathVariable("datumOd") String datumOd, @PathVariable("datumDo") String datumDo, @PathVariable("cenaMin") String cenaMin, @PathVariable("cenaMax") String cenaMax) throws ParseException{
		
		Date dateOd = new SimpleDateFormat("yyyy-MM-dd").parse(datumOd);
		Date dateDo = new SimpleDateFormat("yyyy-MM-dd").parse(datumDo);
		int minPrice = Integer.parseInt(cenaMin);
		int maxPrice = Integer.parseInt(cenaMax);
		List<HotelskaSobaDTO> lista = rezervacijeSobeService.getFreeRoomsPrice(id, dateOd, dateDo, minPrice, maxPrice);
		return(!lista.isEmpty()) ? new ResponseEntity<List<HotelskaSobaDTO>>(lista, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping("/{id}/{datumOd}")
	@ApiOperation( value = "", notes = "", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "Not Found")})
	public ResponseEntity<Integer> getDnevnuPosecenost(@PathVariable("id") Long id, @PathVariable("datumOd") String datumOd) throws ParseException{
		
		Date dateOd = new SimpleDateFormat("yyyy-MM-dd").parse(datumOd);
		int posecenost = rezervacijeSobeService.getDnevnaPosecenost(id, dateOd);
		return (posecenost!=-1) ? new ResponseEntity<Integer>(posecenost, HttpStatus.OK): new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}

	@GetMapping("/{id}/{datumOd}/nedelja")
	@ApiOperation( value = "", notes = "", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "Not Found")})
	public ResponseEntity<Integer> getNedeljnuPosecenost(@PathVariable("id") Long id, @PathVariable("datumOd") String datumOd) throws ParseException{
		
		int posecenost = rezervacijeSobeService.getNedeljnaPosecenost(id, datumOd);
		return (posecenost!=-1) ? new ResponseEntity<Integer>(posecenost, HttpStatus.OK): new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping("/{id}/{datumOd}/mesec")
	@ApiOperation( value = "", notes = "", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "Not Found")})
	public ResponseEntity<Integer> getMesecnuPosecenost(@PathVariable("id") Long id, @PathVariable("datumOd") String datumOd) throws ParseException{
		
		int posecenost = rezervacijeSobeService.getMesecnaPosecenost(id, datumOd);
		return (posecenost!=-1) ? new ResponseEntity<Integer>(posecenost, HttpStatus.OK): new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
}
