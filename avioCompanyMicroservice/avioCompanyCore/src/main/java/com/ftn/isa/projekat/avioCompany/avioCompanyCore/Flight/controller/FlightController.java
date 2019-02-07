package com.ftn.isa.projekat.avioCompany.avioCompanyCore.Flight.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.isa.projekat.avioCompany.avioCompanyApi.dto.FlightDTO;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.Destination.model.Destination;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.Flight.service.IFlightService;

import feign.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/aviocompany/flight")
@Api(value = "flight")
public class FlightController
{
	@Autowired
	IFlightService service;
	
	//READ ONE
	@GetMapping("/{id}")
	@ApiOperation(value = "Find single flight.", notes = "Returns single flight.", httpMethod = "GET")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
						   @ApiResponse(code = 404, message = "NOT_FOUND")
	})
	public ResponseEntity<FlightDTO> getSingleFlight(@PathVariable("id") Long id)
	{
		FlightDTO flight = service.findOneById(id);
		
		return (flight != null) ? new ResponseEntity<FlightDTO>(flight, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND); 
	}
	
	//READ ALL
	@GetMapping("/all")
	@ApiOperation(value = "Find all flights.", notes = "Returns list of flights.", httpMethod = "GET")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
						   @ApiResponse(code = 404, message = "NOT_FOUND")
	})
	public ResponseEntity<List<FlightDTO>> getAllFlights()
	{
		List<FlightDTO> flights = service.findAll();
		
		return (!flights.isEmpty()) ? new ResponseEntity<List<FlightDTO>>(flights, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	//CREATE
	@PostMapping("/")
	@ApiOperation(value = "Insert new flight.", notes = "Returns inserted flight.", httpMethod = "POST", produces = "application/json", consumes = "application/json")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
						   @ApiResponse(code = 400, message = "BAD_REQUEST")
	})
	public ResponseEntity<FlightDTO> addNewFlight(@RequestBody FlightDTO dto)
	{
		FlightDTO crFlight = service.save(dto);
		
		return (crFlight != null) ? new ResponseEntity<FlightDTO>(crFlight, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);				
	}
	
	//DELETE
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Delete one flight.", notes = "Returns deleted flight.", httpMethod = "DELETE")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "DELETED"),
						   @ApiResponse(code = 404, message = "NOT_FOUND")
	})
	public ResponseEntity<FlightDTO> deleteFlight(@PathVariable("id") Long id)
	{
		FlightDTO delFlight = service.deleteById(id);
		
		return (delFlight.getId() != null) ? new ResponseEntity<FlightDTO>(delFlight, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	//UPDATE
	@PutMapping("/{id}")
	@ApiOperation(value = "Update one flight.", notes = "Returns updated flight.", httpMethod = "PUT")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
						   @ApiResponse(code = 404, message = "NOT_FOUND")
	})	
	public ResponseEntity<FlightDTO> updateFlight(@PathVariable("id") Long id, @RequestBody FlightDTO dto) //ovo je ondaj prosledjeni objekat na koji se menja ovaj nas (vrv)
	{
		FlightDTO changedFlight = service.changeFlight(id, dto);
		
		return (changedFlight.getId() != null) ? new ResponseEntity<FlightDTO>(changedFlight, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	/* (Y)
	 * Search by takeoff time and landing time
	 */
	@GetMapping("/getbydate/{takeOffTime}/{landingTime}")
	public ResponseEntity<List<FlightDTO>> getFlightsByDate(@PathVariable("takeOffTime") String takeOffTime, @PathVariable("landingTime") String landingTime)
	{
		DateTimeFormatter format = DateTimeFormatter.ISO_DATE_TIME;
		
		String take = LocalDateTime.parse(takeOffTime).format(format);
		String land = LocalDateTime.parse(landingTime).format(format);
		List<FlightDTO> flights = service.getFlightsByDate(LocalDateTime.parse(take), LocalDateTime.parse(land));
	
		return (!flights.isEmpty()) ? new ResponseEntity<List<FlightDTO>>(flights, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	/* (Y)
	 * Trazimo prosecnu ocenu za jedan let
	 * Prosledjujemo id leta
	 */
	@GetMapping("/getavgrating/{id}")
	@ApiOperation(value = "Get average rating for one flight.", notes = "Returns average.", httpMethod = "GET")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
						   @ApiResponse(code = 404, message = "NOT_FOUND")
	})
	public ResponseEntity<Float> getAverageRating(@PathVariable("id") Long id)
	{
		Float avg = service.getAvgRating(id);
		
		return(avg != null) ? new ResponseEntity<Float>(avg, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	
	/*
	 * Pretraga letova po destinaciji poletanja i sletanja (preko id-jeva)
	 */
	@GetMapping("/getbydest/{fromDest}/{toDest}")
	@ApiOperation(value = "Get flights by destination.", httpMethod = "GET")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
						   @ApiResponse(code = 400, message = "BAD_REQUEST")
	})
	public ResponseEntity<List<FlightDTO>> getFlightsByDestination(@PathVariable("fromDest") Long takeOffDestination, @PathVariable("toDest") Long landingDestination)
	{
//		Destination fromDest = new Destination();
//		fromDest.setName(takeOffDestination);
//		Destination toDest = new Destination();
//		toDest.setName(landingDestination);
		
		List<FlightDTO> flights = service.getFlightsByDestination(takeOffDestination, landingDestination);
		
		return(!flights.isEmpty()) ? new ResponseEntity<List<FlightDTO>>(flights, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
	}
	
	/*
	 * Pretraga letova po tipu leta
	 */
	@GetMapping("/getbytype/{type}")
	@ApiOperation(value = "Get flights by type.", httpMethod = "GET")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
						   @ApiResponse(code = 400, message = "BAD_REQUEST")
	})
	public ResponseEntity<List<FlightDTO>> getFlightsByType(@PathVariable("type") String type)
	{		
		List<FlightDTO> flights = service.getFlightsByType(type);
		
		return(!flights.isEmpty()) ? new ResponseEntity<List<FlightDTO>>(flights, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
	}
	
	/*
	 * Pretraga letova po broju preostalih karata (broju osoba) -> npr ako korisnik hoce da rezervise za jos {broj} ljudi
	 */
	@GetMapping("/getbyticketnum/{number}")
	@ApiOperation(value = "Get flights by tickets left.", httpMethod = "GET")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
						   @ApiResponse(code = 400, message = "BAD_REQUEST")
	})
	public ResponseEntity<List<FlightDTO>> getFlightsByTicketNumber(@PathVariable("number") String number)
	{		
		Integer num = Integer.parseInt(number);
		List<FlightDTO> flights = service.getFlightsByTicketNumber(num);
		
		return(!flights.isEmpty()) ? new ResponseEntity<List<FlightDTO>>(flights, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
	}
	
	/*
	 * Pretraga letova po klasi koji podrzava, odredjuje se na osnovu karata koje se rezervisu za konkretan let
	 */
	@GetMapping("/getbyclass/{klasa}")
	@ApiOperation(value = "Get flights by type.", httpMethod = "GET")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
						   @ApiResponse(code = 400, message = "BAD_REQUEST")
	})
	public ResponseEntity<List<FlightDTO>> getFlightsByClass(@PathVariable("klasa") String klasa)
	{		
		List<FlightDTO> flights = service.getFlightsByClass(klasa);
		
		return(!flights.isEmpty()) ? new ResponseEntity<List<FlightDTO>>(flights, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
	}
	
}
