package com.ftn.isa.projekat.purchases.purchasesCore.bonusPointsDiscounts.controller;

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

import com.ftn.isa.projekat.purchases.purchasesApi.dto.BonusPointsDiscountsDTO;
import com.ftn.isa.projekat.purchases.purchasesCore.bonusPointsDiscounts.service.IBonusPointsDiscountsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/purchases/bonusPoitsDiscounts")
@Api(value="bonusPointsDiscounts")
@CrossOrigin(origins = "http://localhost:3000")
public class BonusPointsDiscountsController {

	@Autowired
	IBonusPointsDiscountsService discountService;
	
	@GetMapping("/{id}")
	@ApiOperation( value = "Finds one discount by bonus points.", notes = "Returns found discount by bonus points.", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "Not Found")})
	public ResponseEntity<BonusPointsDiscountsDTO> getDiscountById (@PathVariable("id") Long id){
		
		BonusPointsDiscountsDTO bonusPointsDiscountsDTO = discountService.findOneById(id);
		
		return ( bonusPointsDiscountsDTO.getId()!=null)? new ResponseEntity<BonusPointsDiscountsDTO>(bonusPointsDiscountsDTO,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping("/all")
	@ApiOperation( value = "Returns all discounts by bonus points", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity<List<BonusPointsDiscountsDTO>> getAllDiscounts(){
		
		List<BonusPointsDiscountsDTO> discounts = discountService.findAll();
		
		return ( !discounts.isEmpty() )? new ResponseEntity<List<BonusPointsDiscountsDTO>>(discounts,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@PostMapping("/")
	@ApiOperation( value = "Create a discount by bonus points, or change discount to already existing discount.", notes = "Returns the discount by bonus points being saved.", httpMethod="POST", produces = "application/json", consumes = "application/json" )
	@ApiResponses( value = {
					@ApiResponse( code = 201 , message = "Created"),
					@ApiResponse( code = 400, message= "Bad request")
	})
	public ResponseEntity<BonusPointsDiscountsDTO> addDiscount(@RequestBody BonusPointsDiscountsDTO dto){
		
		BonusPointsDiscountsDTO savedDiscount = discountService.save(dto);
		
		return ( savedDiscount!=null )? new ResponseEntity<BonusPointsDiscountsDTO>(savedDiscount,HttpStatus.CREATED): new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation( value = "Delete a discount by bonus points.", notes = "Returns the discount by bonus points deleted", httpMethod="DELETE")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity<BonusPointsDiscountsDTO> deleteDiscount(@PathVariable("id") Long id){
		
		BonusPointsDiscountsDTO deletedDiscountsDTO = discountService.deleteById(id);
		
		return (deletedDiscountsDTO.getId() != null ) ? new ResponseEntity<BonusPointsDiscountsDTO>(deletedDiscountsDTO,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/{id}")
	@ApiOperation( value= "Change a discount by bonus points", notes = "Returns the discount by bonus points changed", httpMethod="PUT")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 400, message ="Bad Request")})
	public ResponseEntity<BonusPointsDiscountsDTO> changeDiscount (@PathVariable("id") Long id, @RequestBody BonusPointsDiscountsDTO BonusPointsDiscountsDTO ){
		
		BonusPointsDiscountsDTO discountToEdit = discountService.changeBonusPoints(id, BonusPointsDiscountsDTO);
	
	    return ( discountToEdit.getId() != null )? new ResponseEntity<BonusPointsDiscountsDTO>(discountToEdit,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}	
}
