package com.ftn.isa.projekat.avioCompany.avioCompanyCore.AvioCompany.controller;

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

import com.ftn.isa.projekat.avioCompany.avioCompanyApi.dto.AvioCompanyDTO;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.AvioCompany.service.IAvioCompanyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/aviocompany/company")
@Api(value = "company")
@CrossOrigin(origins = "http://localhost:8091")
public class AvioCompanyController
{
	
	@Autowired
	IAvioCompanyService avioCompanyService;
	
	//READ
	@GetMapping("/{id}")
	@ApiOperation( value = "Finds one avio company.", notes = "Returns found avio company.", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "Not Found")})
	public ResponseEntity<AvioCompanyDTO> getOneAvioCompanyById (@PathVariable("id") Long id){
		
		AvioCompanyDTO avioDto = avioCompanyService.findOneById(id);
		
		return ( avioDto.getId() != null) ? new ResponseEntity<AvioCompanyDTO>(avioDto,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	//READ ALL
	@GetMapping("/all")
	@ApiOperation( value = "Returns all avio companies", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 404, message ="Not found")})	
	public ResponseEntity<List<AvioCompanyDTO>> getAllAvioCompanies(){
		
		System.out.println("ISPIS U KONZOLU poziv");
		List<AvioCompanyDTO> companies = avioCompanyService.findAll();
		
		return ( !companies.isEmpty() )? new ResponseEntity<List<AvioCompanyDTO>>(companies,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	//CREATE (ovo je ok)
	@PostMapping("/")
	@ApiOperation( value = "Create a avio company.", notes = "Returns the avio company that has being saved.", httpMethod="POST", produces = "application/json", consumes = "application/json" )
	@ApiResponses( value = {
					@ApiResponse( code = 201 , message = "Created"),
					@ApiResponse( code = 400, message= "Bad request")
	})
	public ResponseEntity<AvioCompanyDTO> addAvioCompany(@RequestBody AvioCompanyDTO dto){
		
		System.out.println("EJ BRE ALO BRE");
		
		AvioCompanyDTO savedDto = avioCompanyService.save(dto);
		
		return ( savedDto!=null )? new ResponseEntity<AvioCompanyDTO>(savedDto,HttpStatus.CREATED): new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	//DELETE
	@DeleteMapping("/{id}")
	@ApiOperation( value = "Delete a avio company.", notes = "Returns the avio company that has being deleted", httpMethod="DELETE")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity<AvioCompanyDTO> deleteAvioCompany(@PathVariable("id") Long id){
		AvioCompanyDTO deletedCompanyDTO = avioCompanyService.deleteById(id);
		
		return (deletedCompanyDTO.getId() != null ) ? new ResponseEntity<AvioCompanyDTO>(deletedCompanyDTO,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	//UPDATE
	@PutMapping("/{id}")
	@ApiOperation( value= "Change avio company.", notes = "Returns the avio company that has being changed", httpMethod="PUT")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 400, message ="Bad Request")})
	public ResponseEntity<AvioCompanyDTO> changeAvioCompany(@PathVariable("id") Long id, @RequestBody AvioCompanyDTO avioDto){
		
		AvioCompanyDTO companyToEdit = avioCompanyService.changeAvioCompany(id, avioDto);
	
	    return ( companyToEdit.getId() != null )? new ResponseEntity<AvioCompanyDTO>(companyToEdit,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	/*
	 * Prosecna ocena kompanije 
	 * Prosledjuje se id kompanije
	 */
	@GetMapping("/getavgrating/{id}")
	@ApiOperation(value = "Get average rating.", notes = "Returns average rating.")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
						   @ApiResponse(code = 404, message = "NOT_FOUND")
	})
	public ResponseEntity<Float> averageRating(@PathVariable("id") Long id)
	{
		Float avg = avioCompanyService.getAvgRating(id);
		
		System.out.println(avg);
		
		return (avg != null) ? new ResponseEntity<Float>(avg, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
}
