package com.ftn.isa.projekat.purchases.purchasesCore.reservation.controller;

import java.time.LocalDateTime;
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

import com.ftn.isa.projekat.purchases.purchasesApi.dto.ReservationDTO;
import com.ftn.isa.projekat.purchases.purchasesCore.reservation.service.IReservationService;
import com.ftn.isa.projekat.rentACar.rentACarApi.dto.CarReservationDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/purchases/reservation")
@Api(value="reservation")
@CrossOrigin(origins = "http://localhost:3000")
public class ReservationController {
	
	@Autowired
	IReservationService reservationService;
	
	@GetMapping("/{id}")
	@ApiOperation( value = "Finds one reservation.", notes = "Returns found reservation.", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "Not Found")})
	public ResponseEntity<ReservationDTO> getOneReservationById (@PathVariable("id") Long id){
		
		ReservationDTO reservationDto = reservationService.findOneById(id);
		
		return ( reservationDto.getId()!=null)? new ResponseEntity<ReservationDTO>(reservationDto,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping("/all")
	@ApiOperation( value = "Returns all reservations", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity<List<ReservationDTO>> getAllReservations(){
		
		List<ReservationDTO> reservations = reservationService.findAll();
		
		return ( !reservations.isEmpty() )? new ResponseEntity<List<ReservationDTO>>(reservations,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping("/allByUser/{id}")
	@ApiOperation( value = "Returns all reservations", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity<List<ReservationDTO>> getAllReservations(@PathVariable("id") Long id){
		
		List<ReservationDTO> reservations = reservationService.findAllByUserId(id);
		
		return ( !reservations.isEmpty() )? new ResponseEntity<List<ReservationDTO>>(reservations,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@PostMapping("/")
	@ApiOperation( value = "Create a reservation.", notes = "Returns the reservation being saved.", httpMethod="POST", produces = "application/json", consumes = "application/json" )
	@ApiResponses( value = {
					@ApiResponse( code = 201 , message = "Created"),
					@ApiResponse( code = 400, message= "Bad request")
	})
	public ResponseEntity<ReservationDTO> addReservation(@RequestBody ReservationDTO dto){
		
		ReservationDTO savedReservation = reservationService.save(dto);
		
		return ( savedReservation!=null )? new ResponseEntity<ReservationDTO>(savedReservation,HttpStatus.CREATED): new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation( value = "Delete a reservation.", notes = "Returns the reservation being deleted", httpMethod="DELETE")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity<ReservationDTO> deleteReservation(@PathVariable("id") Long id){
		ReservationDTO deletedReservationDTO = reservationService.deleteById(id);
		
		return (deletedReservationDTO.getId() != null ) ? new ResponseEntity<ReservationDTO>(deletedReservationDTO,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/{id}")
	@ApiOperation( value= "Change a reservation", notes = "Returns the reservation being changed", httpMethod="PUT")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 400, message ="Bad Request")})
	public ResponseEntity<ReservationDTO> changeReservation (@PathVariable("id") Long id, @RequestBody ReservationDTO reservationDTO ){
		
		ReservationDTO reservationToEdit = reservationService.changeReservation(id, reservationDTO);
	
	    return ( reservationToEdit.getId() != null )? new ResponseEntity<ReservationDTO>(reservationToEdit,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	
	@DeleteMapping("/deleteCarReservation/{id}")
	public ResponseEntity<ReservationDTO> deleteCarReservation(@PathVariable("id") Long id){
		
		ReservationDTO reservationToEdit = reservationService.deleteCarReservation(id);
	
	    return ( reservationToEdit.getId() != null )? new ResponseEntity<ReservationDTO>(reservationToEdit,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}	
		

}
