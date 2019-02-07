package com.ftn.isa.projekat.avioCompany.avioCompanyCore.Destination.controller;

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

import com.ftn.isa.projekat.avioCompany.avioCompanyApi.dto.DestinationDTO;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.Destination.service.IDestinationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/aviocompany/destination")
@Api(value = "destination")
public class DestinationController
{
	@Autowired
	IDestinationService destService;
	
	//READ ONE
	@GetMapping("/{id}")
	@ApiOperation(value = "Finds one destination.", notes = "Return destination.", httpMethod="GET")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
						   @ApiResponse(code = 404, message = "NOT FOUND")
	})
	public ResponseEntity<DestinationDTO> getOneDestination(@PathVariable("id") Long id)
	{
		DestinationDTO dto = destService.findOneById(id);
		
		return (dto.getId() != null) ? new ResponseEntity<DestinationDTO>(dto, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	//READ ALL
	@GetMapping("/all")
	@ApiOperation(value = "Returns list of destinations.", httpMethod = "GET")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
						   @ApiResponse(code = 404, message = "NOT FOUND")
	})
	public ResponseEntity<List<DestinationDTO>> getAllDestinations()
	{
		List<DestinationDTO> dests = destService.findAll();
		
		return (!dests.isEmpty()) ? new ResponseEntity<List<DestinationDTO>>(dests, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	//CREATE
	@PostMapping("/")
	@ApiOperation(value = "Add destination.", notes = "Returns inserted destination.", httpMethod = "POST", produces = "application/json", consumes = "application/json")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "CREATED"),
						   @ApiResponse(code = 400, message = "BAD_REQUEST")			
	})
	public ResponseEntity<DestinationDTO> addDestination(@RequestBody DestinationDTO dto)
	{
		DestinationDTO newDest = destService.save(dto);
		
		return (newDest != null) ? new ResponseEntity<DestinationDTO>(newDest, HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	//DELETE
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Delete a destination.", notes = "Returns deleted destination.", httpMethod = "DELETE")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "DELETED"),
						   @ApiResponse(code = 404, message = "NOT_FOUND")
	})
	public ResponseEntity<DestinationDTO> deleteDestination(@PathVariable("id") Long id)
	{
		DestinationDTO deleted = destService.deleteById(id);
		
		return (deleted.getId() != null) ? new ResponseEntity<DestinationDTO>(deleted, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	//UPDATE
	@PutMapping("{id}")
	@ApiOperation(value = "Update destination.", notes = "Returns updated destination.", httpMethod = "PUT")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
						   @ApiResponse(code = 400, message = "BAD_REQUEST")
	})
	public ResponseEntity<DestinationDTO> updateDestination(@PathVariable("id") Long id, @RequestBody DestinationDTO dto)
	{
		DestinationDTO updateDest = destService.changeDestination(id, dto);
		
		return (updateDest.getId() != null) ? new ResponseEntity<DestinationDTO>(updateDest, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	
	
	
	
	
	
	
}
