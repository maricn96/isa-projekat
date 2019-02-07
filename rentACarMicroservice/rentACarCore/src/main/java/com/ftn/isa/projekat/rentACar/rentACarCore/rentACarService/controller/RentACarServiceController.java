package com.ftn.isa.projekat.rentACar.rentACarCore.rentACarService.controller;

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

import com.ftn.isa.projekat.rentACar.rentACarApi.dto.RentACarServiceDTO;
import com.ftn.isa.projekat.rentACar.rentACarCore.rentACarService.service.IRentACarServiceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/rentacar/rentACarService")
@Api(value="rentacarservice")
@CrossOrigin(origins = "http://localhost:3000")
public class RentACarServiceController {
	
	@Autowired
	IRentACarServiceService rentACarService;

	
	@GetMapping("/{id}")
	@ApiOperation( value = "Finds one rent a car service.", notes = "Returns found rent a car service.", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "Not Found")})
	public ResponseEntity<RentACarServiceDTO> getOneRentACarServiceById (@PathVariable("id") Long id){
		
		RentACarServiceDTO rentACarDto = rentACarService.findOneById(id);
		
		return ( rentACarDto.getId()!=null)? new ResponseEntity<RentACarServiceDTO>(rentACarDto,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping("/all")
	@ApiOperation( value = "Returns all rent a car services", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity<List<RentACarServiceDTO>> getAllRentACarServices(){
		
		List<RentACarServiceDTO> rentACarServices = rentACarService.findAll();
		
		return ( !rentACarServices.isEmpty() )? new ResponseEntity<List<RentACarServiceDTO>>(rentACarServices,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@PostMapping("/")
	@ApiOperation( value = "Create a rent a car service.", notes = "Returns the rent a car service being saved.", httpMethod="POST", produces = "application/json", consumes = "application/json" )
	@ApiResponses( value = {
					@ApiResponse( code = 201 , message = "Created"),
					@ApiResponse( code = 400, message= "Bad request")
	})
	public ResponseEntity<RentACarServiceDTO> addBranchOffice(@RequestBody RentACarServiceDTO dto){
		
		RentACarServiceDTO savedRentACarService = rentACarService.save(dto);
		
		return ( savedRentACarService!=null )? new ResponseEntity<RentACarServiceDTO>(savedRentACarService,HttpStatus.CREATED): new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation( value = "Delete a rent a car service.", notes = "Returns the rent a car service being deleted", httpMethod="DELETE")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity<RentACarServiceDTO> deleteRentACarService(@PathVariable("id") Long id){
		RentACarServiceDTO deletedRentACarDTO = rentACarService.deleteById(id);
		
		return (deletedRentACarDTO.getId() != null ) ? new ResponseEntity<RentACarServiceDTO>(deletedRentACarDTO,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/{id}")
	@ApiOperation( value= "Change a rent a car service", notes = "Returns the rent a car service being changed", httpMethod="PUT")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 400, message ="Bad Request")})
	public ResponseEntity<RentACarServiceDTO> changeRentACarService (@PathVariable("id") Long id, @RequestBody RentACarServiceDTO rentACarDto ){
		
		RentACarServiceDTO rentACarToEdit = rentACarService.changeRentACarService(id, rentACarDto);
	
	    return ( rentACarToEdit.getId() != null )? new ResponseEntity<RentACarServiceDTO>(rentACarToEdit,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/getSumOfIncomes/{id}/{dateFrom}/{dateTo}")
	public ResponseEntity<Integer> getSumOfIncomes(@PathVariable("id") Long rentService,@PathVariable("dateFrom") String dateFrom , @PathVariable("dateTo") String dateTo){
		
		Integer sumOfIncomes = rentACarService.getSumOfIncomes(rentService,LocalDateTime.parse(dateFrom), LocalDateTime.parse(dateTo));
		
		return (sumOfIncomes != -1) ? new ResponseEntity<Integer>(sumOfIncomes,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/getAllServices/{name}/{city1}/{city2}")
	public ResponseEntity<List<RentACarServiceDTO>> getAllRentACarServicesByFilter(@PathVariable("name") String name, @PathVariable("city1") String city1, @PathVariable("city2") String city2){
		
		List<RentACarServiceDTO> rentACarServices = rentACarService.findAllFilter(name,city1,city2);
		
		return ( !rentACarServices.isEmpty() )? new ResponseEntity<List<RentACarServiceDTO>>(rentACarServices,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	
}
