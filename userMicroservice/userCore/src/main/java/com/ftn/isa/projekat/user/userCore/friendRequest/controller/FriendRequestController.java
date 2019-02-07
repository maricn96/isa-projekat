package com.ftn.isa.projekat.user.userCore.friendRequest.controller;

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

import com.ftn.isa.projekat.user.userApi.dto.FriendRequestDTO;
import com.ftn.isa.projekat.user.userCore.friendRequest.service.IFriendRequestService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/user/friendRequest")
@Api(value="friendRequest")
@CrossOrigin(origins = "http://localhost:3000")
public class FriendRequestController {

	@Autowired
	IFriendRequestService requestService;
	
	
	@GetMapping("/{id}")
	@ApiOperation( value = "Finds one friend request.", notes = "Returns found friend request.", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "Not Found")})
	public ResponseEntity<FriendRequestDTO> getOneFriendRequestById (@PathVariable("id") Long id){
		
		FriendRequestDTO requestDto = requestService.findOneById(id);
		
		return ( requestDto.getId()!=null)? new ResponseEntity<FriendRequestDTO>(requestDto,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping("/all")
	@ApiOperation( value = "Returns all friend requests", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity<List<FriendRequestDTO>> getAllRequests(){
		
		List<FriendRequestDTO> requests = requestService.findAll();
		
		return ( !requests.isEmpty() )? new ResponseEntity<List<FriendRequestDTO>>(requests,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@PostMapping("/")
	@ApiOperation( value = "Create a friend request.", notes = "Returns the friend request being saved.", httpMethod="POST", produces = "application/json", consumes = "application/json" )
	@ApiResponses( value = {
					@ApiResponse( code = 201 , message = "Created"),
					@ApiResponse( code = 400, message= "Bad request")
	})
	public ResponseEntity<FriendRequestDTO> addFriendRequest(@RequestBody FriendRequestDTO dto){
		
		FriendRequestDTO savedRequest = requestService.save(dto);
		
		return ( savedRequest.getId()!=null )? new ResponseEntity<FriendRequestDTO>(savedRequest,HttpStatus.CREATED): new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation( value = "Delete a friend request.", notes = "Returns the friend request being deleted", httpMethod="DELETE")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity<FriendRequestDTO> deleteFriendRequest(@PathVariable("id") Long id){
		
		FriendRequestDTO deletedRequestDTO = requestService.deleteById(id);
		
		return (deletedRequestDTO.getId() != null ) ? new ResponseEntity<FriendRequestDTO>(deletedRequestDTO,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/{id}")
	@ApiOperation( value= "Change a friend request", notes = "Returns the request being changed", httpMethod="PUT")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 400, message ="Bad Request")})
	public ResponseEntity<FriendRequestDTO> changeRequest (@PathVariable("id") Long id, @RequestBody FriendRequestDTO requestDto ){
		
		FriendRequestDTO requestToEdit = requestService.changeFriendRequest(id, requestDto);
	
	    return ( requestToEdit.getId() != null )? new ResponseEntity<FriendRequestDTO>(requestToEdit,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/acceptRequest/{id}")
	@ApiOperation( value= "Accept friend request", notes = "Returns accepted request", httpMethod="PUT")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 400, message ="Bad Request")})
	public ResponseEntity<FriendRequestDTO> acceptRequest (@PathVariable("id") Long id){
		
		FriendRequestDTO acceptedRequest = requestService.acceptRequest(id);
		
	    return ( acceptedRequest.getId() != null )? new ResponseEntity<FriendRequestDTO>(acceptedRequest,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);		
		
	}
}
