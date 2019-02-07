package com.ftn.isa.projekat.user.userCore.userRole.controller;

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

import com.ftn.isa.projekat.user.userApi.dto.UserRoleDTO;
import com.ftn.isa.projekat.user.userCore.userRole.service.IUserRoleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/user/userRole")
@Api(value = "userRole")
@CrossOrigin(origins = "http://localhost:3000")
public class UserRoleController {
	
	@Autowired
	IUserRoleService roleService;
	
	
	@GetMapping("/{id}")
	@ApiOperation( value = "Finds one user role.", notes = "Returns found user role.", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "Not Found")})
	public ResponseEntity<UserRoleDTO> getOneUserRoleById (@PathVariable("id") Long id){
		
		UserRoleDTO userDto = roleService.findOneById(id);
		
		return ( userDto.getId()!=null)? new ResponseEntity<UserRoleDTO>(userDto,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping("/all")
	@ApiOperation( value = "Returns all user roles", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity<List<UserRoleDTO>> getAllUserRoles(){
		
		List<UserRoleDTO> roles = roleService.findAll();
		
		return ( !roles.isEmpty() )? new ResponseEntity<List<UserRoleDTO>>(roles,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@PostMapping("/")
	@ApiOperation( value = "Create an user role.", notes = "Returns the user role being saved.", httpMethod="POST", produces = "application/json", consumes = "application/json" )
	@ApiResponses( value = {
					@ApiResponse( code = 201 , message = "Created"),
					@ApiResponse( code = 400, message= "Bad request")
	})
	public ResponseEntity<UserRoleDTO> addUserRole(@RequestBody UserRoleDTO dto){
		
		UserRoleDTO savedRole = roleService.save(dto);
		
		return ( savedRole!=null )? new ResponseEntity<UserRoleDTO>(savedRole,HttpStatus.CREATED): new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation( value = "Delete an user role.", notes = "Returns the user role being deleted", httpMethod="DELETE")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity<UserRoleDTO> deleteUserRole(@PathVariable("id") Long id){
		
		UserRoleDTO deletedRoleDTO = roleService.deleteById(id);
		
		return (deletedRoleDTO.getId() != null ) ? new ResponseEntity<UserRoleDTO>(deletedRoleDTO,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/{id}")
	@ApiOperation( value= "Change an user role", notes = "Returns the user role being changed", httpMethod="PUT")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 400, message ="Bad Request")})
	public ResponseEntity<UserRoleDTO> changeUserRole (@PathVariable("id") Long id, @RequestBody UserRoleDTO roleDto ){
		
		UserRoleDTO roleToEdit = roleService.changeUserRole(id, roleDto);
	
	    return ( roleToEdit.getId() != null )? new ResponseEntity<UserRoleDTO>(roleToEdit,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}


}
