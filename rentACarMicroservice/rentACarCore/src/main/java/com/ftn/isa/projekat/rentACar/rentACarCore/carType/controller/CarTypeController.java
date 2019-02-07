package com.ftn.isa.projekat.rentACar.rentACarCore.carType.controller;

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

import com.ftn.isa.projekat.rentACar.rentACarApi.dto.CarTypeDTO;
import com.ftn.isa.projekat.rentACar.rentACarCore.carType.service.ICarTypeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/rentacar/carType")
@Api(value="cartype")
@CrossOrigin(origins = "http://localhost:3000")
public class CarTypeController {

	@Autowired
	ICarTypeService carTypeService;
	
	
	@GetMapping("/{id}")
	@ApiOperation( value = "Finds one car type.", notes = "Returns found car type.", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "Not Found")})
	public ResponseEntity<CarTypeDTO> getOneCarTypeById (@PathVariable("id") Long id){
		
		CarTypeDTO carTypeDto = carTypeService.findOneById(id);
		
		return ( carTypeDto.getId()!=null)? new ResponseEntity<CarTypeDTO>(carTypeDto,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping("/all")
	@ApiOperation( value = "Returns all car types", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity<List<CarTypeDTO>> getAllCarTypes(){
		
		List<CarTypeDTO> carTypes = carTypeService.findAll();
		
		return ( !carTypes.isEmpty() )? new ResponseEntity<List<CarTypeDTO>>(carTypes,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@PostMapping("/")
	@ApiOperation( value = "Create a car type.", notes = "Returns the car type being saved.", httpMethod="POST", produces = "application/json", consumes = "application/json" )
	@ApiResponses( value = {
					@ApiResponse( code = 201 , message = "Created"),
					@ApiResponse( code = 400, message= "Bad request")
	})
	public ResponseEntity<CarTypeDTO> addCarType(@RequestBody CarTypeDTO dto){
		
		CarTypeDTO savedCarType = carTypeService.save(dto);
		
		return ( savedCarType!=null )? new ResponseEntity<CarTypeDTO>(savedCarType,HttpStatus.CREATED): new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation( value = "Delete a car type.", notes = "Returns the car type being deleted", httpMethod="DELETE")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity<CarTypeDTO> deleteCarType(@PathVariable("id") Long id){
		CarTypeDTO deletedcarTypeDTO = carTypeService.deleteById(id);
		
		return (deletedcarTypeDTO.getId() != null ) ? new ResponseEntity<CarTypeDTO>(deletedcarTypeDTO,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/{id}")
	@ApiOperation( value= "Change a car type", notes = "Returns the car type being changed", httpMethod="PUT")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 400, message ="Bad Request")})
	public ResponseEntity<CarTypeDTO> changeCarType (@PathVariable("id") Long id, @RequestBody CarTypeDTO carTypeDto ){
		
		CarTypeDTO carTypeToEdit = carTypeService.changeCarType(id, carTypeDto);
	
	    return ( carTypeToEdit.getId() != null )? new ResponseEntity<CarTypeDTO>(carTypeToEdit,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
}
