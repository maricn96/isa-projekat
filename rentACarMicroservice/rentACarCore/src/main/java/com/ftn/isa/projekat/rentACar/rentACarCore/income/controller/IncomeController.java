package com.ftn.isa.projekat.rentACar.rentACarCore.income.controller;

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

import com.ftn.isa.projekat.rentACar.rentACarApi.dto.IncomeDTO;
import com.ftn.isa.projekat.rentACar.rentACarCore.income.service.IIncomeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/rentacar/income")
@Api( value = "income")
@CrossOrigin(origins = "http://localhost:3000")
public class IncomeController {

	@Autowired
	IIncomeService incomeService;
	
	@GetMapping("/{id}")
	@ApiOperation( value = "Finds one income.", notes = "Returns found income.", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "Not Found")})
	public ResponseEntity<IncomeDTO> getOneIncomeById (@PathVariable("id") Long id){
		
		IncomeDTO incomeDto = incomeService.findOneById(id);
		
		return ( incomeDto.getId()!=null)? new ResponseEntity<IncomeDTO>(incomeDto,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping("/all")
	@ApiOperation( value = "Returns all incomes.", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity<List<IncomeDTO>> getAllIncomes(){
		
		List<IncomeDTO> incomes = incomeService.findAll();
		
		return ( !incomes.isEmpty() )? new ResponseEntity<List<IncomeDTO>>(incomes,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@PostMapping("/")
	@ApiOperation( value = "Create a income.", notes = "Returns the income being saved.", httpMethod="POST", produces = "application/json", consumes = "application/json" )
	@ApiResponses( value = {
					@ApiResponse( code = 201 , message = "Created"),
					@ApiResponse( code = 400, message= "Bad request")
	})
	public ResponseEntity<IncomeDTO> addIncome(@RequestBody IncomeDTO dto){
		
		IncomeDTO savedIncome = incomeService.save(dto);
		
		return ( savedIncome!=null )? new ResponseEntity<IncomeDTO>(savedIncome,HttpStatus.CREATED): new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation( value = "Delete a income.", notes = "Returns the income being deleted", httpMethod="DELETE")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity<IncomeDTO> deleteIncome(@PathVariable("id") Long id){
		IncomeDTO deletedIncomeDTO = incomeService.deleteById(id);
		
		return (deletedIncomeDTO.getId() != null ) ? new ResponseEntity<IncomeDTO>(deletedIncomeDTO,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/{id}")
	@ApiOperation( value= "Change a income", notes = "Returns the income being changed", httpMethod="PUT")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 400, message ="Bad Request")})
	public ResponseEntity<IncomeDTO> changeBranch (@PathVariable("id") Long id, @RequestBody IncomeDTO incomeDto ){
		
		IncomeDTO incomeToEdit = incomeService.changeIncome(id, incomeDto);
	
	    return ( incomeToEdit.getId() != null )? new ResponseEntity<IncomeDTO>(incomeToEdit,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	

}
