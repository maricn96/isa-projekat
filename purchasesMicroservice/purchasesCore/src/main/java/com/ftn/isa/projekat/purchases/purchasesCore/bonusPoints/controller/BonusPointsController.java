package com.ftn.isa.projekat.purchases.purchasesCore.bonusPoints.controller;

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

import com.ftn.isa.projekat.purchases.purchasesApi.dto.BonusPointsDTO;
import com.ftn.isa.projekat.purchases.purchasesCore.bonusPoints.service.IBonusPointsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/purchases/bonusPoints")
@Api(value="bonusPoints")
@CrossOrigin(origins = "http://localhost:3000")
public class BonusPointsController {

	@Autowired
	IBonusPointsService bonusPointsService;
	
	@GetMapping("/{id}")
	@ApiOperation( value = "Finds one link user-bonus points.", notes = "Returns found link between user id and bonus points.", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "Not Found")})
	public ResponseEntity<BonusPointsDTO> getBonusPointById (@PathVariable("id") Long id){
		
		BonusPointsDTO bonusPointsDto = bonusPointsService.findOneById(id);
		
		return ( bonusPointsDto.getId()!=null)? new ResponseEntity<BonusPointsDTO>(bonusPointsDto,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping("/all")
	@ApiOperation( value = "Returns all links userid-bonus points", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity<List<BonusPointsDTO>> getAllBonusPoints(){
		
		List<BonusPointsDTO> bonusPoints = bonusPointsService.findAll();
		
		return ( !bonusPoints.isEmpty() )? new ResponseEntity<List<BonusPointsDTO>>(bonusPoints,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@PostMapping("/")
	@ApiOperation( value = "Create a link between user id and bonus points, or add points to already existing link.", notes = "Returns the link being saved.", httpMethod="POST", produces = "application/json", consumes = "application/json" )
	@ApiResponses( value = {
					@ApiResponse( code = 201 , message = "Created"),
					@ApiResponse( code = 400, message= "Bad request")
	})
	public ResponseEntity<BonusPointsDTO> addBonusPoints(@RequestBody BonusPointsDTO dto){
		
		BonusPointsDTO savedBonusPoints = bonusPointsService.save(dto);
		
		return ( savedBonusPoints!=null )? new ResponseEntity<BonusPointsDTO>(savedBonusPoints,HttpStatus.CREATED): new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation( value = "Delete a link userId-bonus points.", notes = "Returns the link deleted", httpMethod="DELETE")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity<BonusPointsDTO> deleteBonusPoints(@PathVariable("id") Long id){
		BonusPointsDTO deletedBonusPointsDTO = bonusPointsService.deleteById(id);
		
		return (deletedBonusPointsDTO.getId() != null ) ? new ResponseEntity<BonusPointsDTO>(deletedBonusPointsDTO,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/{id}")
	@ApiOperation( value= "Change a link between userId-bonusPoints", notes = "Returns the link changed", httpMethod="PUT")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 400, message ="Bad Request")})
	public ResponseEntity<BonusPointsDTO> changeBonusPoints (@PathVariable("id") Long id, @RequestBody BonusPointsDTO BonusPointsDto ){
		
		BonusPointsDTO bonusPointsToEdit = bonusPointsService.changeBonusPoints(id, BonusPointsDto);
	
	    return ( bonusPointsToEdit.getId() != null )? new ResponseEntity<BonusPointsDTO>(bonusPointsToEdit,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}	
	
}
