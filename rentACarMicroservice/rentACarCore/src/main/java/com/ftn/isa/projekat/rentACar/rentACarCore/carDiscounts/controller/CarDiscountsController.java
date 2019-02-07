package com.ftn.isa.projekat.rentACar.rentACarCore.carDiscounts.controller;

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

import com.ftn.isa.projekat.rentACar.rentACarApi.dto.CarDiscountsDTO;
import com.ftn.isa.projekat.rentACar.rentACarCore.carDiscounts.service.ICarDiscountsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/rentacar/carDiscounts")
@Api(value="Discounts")
@CrossOrigin(origins = "http://localhost:3000")
public class CarDiscountsController {

	@Autowired
	ICarDiscountsService discountService;
	
	
	
	
	@GetMapping("/{id}")
	@ApiOperation( value = "Finds one car discount.", notes = "Returns found discount.", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "Not Found")})
	public ResponseEntity<CarDiscountsDTO> getOneDiscountById (@PathVariable("id") Long id){
		
		CarDiscountsDTO DiscountDto = discountService.findOneById(id);
		
		return ( DiscountDto.getId()!=null)? new ResponseEntity<CarDiscountsDTO>(DiscountDto,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping("/all")
	@ApiOperation( value = "Returns all Discounts", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity<List<CarDiscountsDTO>> getAllDiscounts(){
		
		List<CarDiscountsDTO> Discounts = discountService.findAll();
		
		return ( !Discounts.isEmpty() )? new ResponseEntity<List<CarDiscountsDTO>>(Discounts,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@PostMapping("/")
	@ApiOperation( value = "Create a Discount.", notes = "Returns the Discount being saved.", httpMethod="POST", produces = "application/json", consumes = "application/json" )
	@ApiResponses( value = {
					@ApiResponse( code = 201 , message = "Created"),
					@ApiResponse( code = 400, message= "Bad request")
	})
	public ResponseEntity<CarDiscountsDTO> addDiscount(@RequestBody CarDiscountsDTO dto){
		
		CarDiscountsDTO savedDiscount = discountService.save(dto);
		
		return ( savedDiscount.getId() != null )? new ResponseEntity<CarDiscountsDTO>(savedDiscount,HttpStatus.CREATED): new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation( value = "Delete a car discount.", notes = "Returns the car discount being deleted", httpMethod="DELETE")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity<CarDiscountsDTO> deleteDiscount(@PathVariable("id") Long id){
		CarDiscountsDTO deletedDiscountDTO = discountService.deleteById(id);
		
		return (deletedDiscountDTO.getId() != null ) ? new ResponseEntity<CarDiscountsDTO>(deletedDiscountDTO,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
		
	
	@PutMapping("/{id}")
	@ApiOperation( value= "Change a car discount", notes = "Returns the discount being changed", httpMethod="PUT")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 400, message ="Bad Request")})
	public ResponseEntity<CarDiscountsDTO> changeDiscount (@PathVariable("id") Long id, @RequestBody CarDiscountsDTO discountDTO ){
		
		CarDiscountsDTO discountToEdit = discountService.changeDiscount(id, discountDTO);
	
	    return ( discountToEdit.getId() != null )? new ResponseEntity<CarDiscountsDTO>(discountToEdit,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	

	@GetMapping("/rentService/{id}")
	@ApiOperation( value = "Returns all Discounts", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity<List<CarDiscountsDTO>> getAllCarDiscountsByRentService(@PathVariable("id") Long rentId){
		
		List<CarDiscountsDTO> Discounts = discountService.findAllByRentService(rentId);
		
		return ( !Discounts.isEmpty() )? new ResponseEntity<List<CarDiscountsDTO>>(Discounts,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	
}
