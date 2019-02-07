package com.ftn.isa.projekat.avioCompany.avioCompanyCore.Luggage.controller;

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

import com.ftn.isa.projekat.avioCompany.avioCompanyApi.dto.LuggageDTO;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.Luggage.service.ILuggageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/aviocompany/luggage")
@Api(value = "luggage")
public class LuggageController
{
	
	@Autowired
	ILuggageService luggageService;
	
	//READ
	@GetMapping("/{id}")
	@ApiOperation( value = "Finds one luggage.", notes = "Returns founded luggage.", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "Not Found")})
	public ResponseEntity<LuggageDTO> getOneLuggageById (@PathVariable("id") Long id){
		
		LuggageDTO avioDto = luggageService.findOneById(id);
		
		return ( avioDto.getId() != null) ? new ResponseEntity<LuggageDTO>(avioDto,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	//READ ALL
	@GetMapping("/all")
	@ApiOperation( value = "Returns all luggage", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 404, message ="Not found")})	
	public ResponseEntity<List<LuggageDTO>> getAllLuggages(){
		
		System.out.println("ISPIS U KONZOLU poziv");
		List<LuggageDTO> companies = luggageService.findAll();
		
		return ( !companies.isEmpty() )? new ResponseEntity<List<LuggageDTO>>(companies,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	//CREATE (ovo je ok)
	@PostMapping("/")
	@ApiOperation( value = "Create a luggage.", notes = "Returns the luggage that has being saved.", httpMethod="POST", produces = "application/json", consumes = "application/json" )
	@ApiResponses( value = {
					@ApiResponse( code = 201 , message = "Created"),
					@ApiResponse( code = 400, message= "Bad request")
	})
	public ResponseEntity<LuggageDTO> addLuggage(@RequestBody LuggageDTO dto){
		
		System.out.println("EJ BRE ALO BRE");
		
		LuggageDTO savedDto = luggageService.save(dto);
		
		return ( savedDto!=null )? new ResponseEntity<LuggageDTO>(savedDto,HttpStatus.CREATED): new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	//DELETE
	@DeleteMapping("/{id}")
	@ApiOperation( value = "Delete luggage.", notes = "Returns the luggage that has being deleted", httpMethod="DELETE")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity<LuggageDTO> deleteLuggage(@PathVariable("id") Long id)
	{
		LuggageDTO deletedCompanyDTO = luggageService.deleteById(id);
		
		return (deletedCompanyDTO.getId() != null ) ? new ResponseEntity<LuggageDTO>(deletedCompanyDTO,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	//UPDATE
	@PutMapping("/{id}")
	@ApiOperation( value= "Change luggage.", notes = "Returns luggage that has being changed", httpMethod="PUT")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 400, message ="Bad Request")})
	public ResponseEntity<LuggageDTO> changeLuggage(@PathVariable("id") Long id, @RequestBody LuggageDTO luggageDto){
		
		LuggageDTO companyToEdit = luggageService.changeLuggage(id, luggageDto);
	
	    return ( companyToEdit.getId() != null )? new ResponseEntity<LuggageDTO>(companyToEdit,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	
}
