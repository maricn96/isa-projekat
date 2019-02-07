package com.ftn.isa.projekat.purchases.purchasesCore.adminLink.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.isa.projekat.purchases.purchasesApi.dto.AdminLinkDTO;
import com.ftn.isa.projekat.purchases.purchasesCore.adminLink.service.IAdminLinkService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/purchases/adminLink")
@Api(value="adminLink")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminLinkController {

	@Autowired
	IAdminLinkService adminLinkService;
	
	
	
	@GetMapping("/{id}")
	@ApiOperation( value = "Finds one link admin-service.", notes = "Returns found link between admin-service.", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "Not Found")})
	public ResponseEntity<AdminLinkDTO> getAdminLinkById (@PathVariable("id") Long id){
		
		AdminLinkDTO adminLinkDTO = adminLinkService.findOneById(id);
		
		return ( adminLinkDTO.getId()!=null)? new ResponseEntity<AdminLinkDTO>(adminLinkDTO,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping("/all")
	@ApiOperation( value = "Returns all links admin-service", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity<List<AdminLinkDTO>> getAllAdminLinks(){
		
		List<AdminLinkDTO> adminLinks = adminLinkService.findAll();
		
		return ( !adminLinks.isEmpty() )? new ResponseEntity<List<AdminLinkDTO>>(adminLinks,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@PostMapping("/")
	@ApiOperation( value = "Create a link between admin-service, or override service id for user with some role.", notes = "Returns the link being saved.", httpMethod="POST", produces = "application/json", consumes = "application/json" )
	@ApiResponses( value = {
					@ApiResponse( code = 201 , message = "Created"),
					@ApiResponse( code = 400, message= "Bad request")
	})
	public ResponseEntity<AdminLinkDTO> addAdminLinks(@RequestBody AdminLinkDTO dto){
		
		AdminLinkDTO savedAdminLink = adminLinkService.save(dto);
		
		return ( savedAdminLink!=null )? new ResponseEntity<AdminLinkDTO>(savedAdminLink,HttpStatus.CREATED): new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation( value = "Delete a link admin-service.", notes = "Returns the link being deleted", httpMethod="DELETE")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity<AdminLinkDTO> deleteAdminLink(@PathVariable("id") Long id){
		
		AdminLinkDTO deletedAdminLinkDTO = adminLinkService.deleteById(id);
		
		return (deletedAdminLinkDTO.getId() != null ) ? new ResponseEntity<AdminLinkDTO>(deletedAdminLinkDTO,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	@GetMapping("/user/{id}")
	@ApiOperation( value = "Finds one link admin-service for explicit user.", notes = "Returns found link between admin-service.", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "Not Found")})
	public ResponseEntity<AdminLinkDTO> getAdminLinkByUserId (@PathVariable("id") Long id){
		
		AdminLinkDTO adminLinkDTO = adminLinkService.findOneByUserId(id);
		
		return ( adminLinkDTO.getId()!=null)? new ResponseEntity<AdminLinkDTO>(adminLinkDTO,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	
}
