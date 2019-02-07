package com.ftn.isa.projekat.purchases.purchasesCore.invitationCard.controller;

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

import com.ftn.isa.projekat.purchases.purchasesApi.dto.InvitationCardDTO;
import com.ftn.isa.projekat.purchases.purchasesCore.invitationCard.service.IInvitationCardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/purchases/invitationCard")
@Api(value="invitationCard")
@CrossOrigin(origins = "http://localhost:3000")

public class InvitationCardController {

	@Autowired
	IInvitationCardService invitationService;
	
	@GetMapping("/{id}")
	@ApiOperation( value = "Finds one invitation card.", notes = "Returns found invitation card.", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "Not Found")})
	public ResponseEntity<InvitationCardDTO> getOneInvitationCardById (@PathVariable("id") Long id){
		
		InvitationCardDTO invitationCardDTO = invitationService.findOneById(id);
		
		return ( invitationCardDTO.getId()!=null)? new ResponseEntity<InvitationCardDTO>(invitationCardDTO,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping("/all")
	@ApiOperation( value = "Returns all invitation cards", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity<List<InvitationCardDTO>> getAllInvitations(){
		
		List<InvitationCardDTO> invitations = invitationService.findAll();
		
		return ( !invitations.isEmpty() )? new ResponseEntity<List<InvitationCardDTO>>(invitations,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@PostMapping("/")
	@ApiOperation( value = "Create a invitation card.", notes = "Returns the invitation being saved.", httpMethod="POST", produces = "application/json", consumes = "application/json" )
	@ApiResponses( value = {
					@ApiResponse( code = 201 , message = "Created"),
					@ApiResponse( code = 400, message= "Bad request")
	})
	public ResponseEntity<InvitationCardDTO> addInvitation(@RequestBody InvitationCardDTO dto){
		
		InvitationCardDTO savedInvitation = invitationService.save(dto);
		
		return ( savedInvitation!=null )? new ResponseEntity<InvitationCardDTO>(savedInvitation,HttpStatus.CREATED): new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation( value = "Delete a invitation card.", notes = "Returns the invitation being deleted", httpMethod="DELETE")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity<InvitationCardDTO> deleteInvitation(@PathVariable("id") Long id){
		InvitationCardDTO deletedInvitationCardDTO = invitationService.deleteById(id);
		
		return (deletedInvitationCardDTO.getId() != null ) ? new ResponseEntity<InvitationCardDTO>(deletedInvitationCardDTO,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/{id}")
	@ApiOperation( value= "Change a invitation card", notes = "Returns the invitation card being changed", httpMethod="PUT")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 400, message ="Bad Request")})
	public ResponseEntity<InvitationCardDTO> changeInvitation (@PathVariable("id") Long id, @RequestBody InvitationCardDTO invitationCardDTO ){
		
		InvitationCardDTO invitationToEdit = invitationService.changeInvitation(id, invitationCardDTO);
	
	    return ( invitationToEdit.getId() != null )? new ResponseEntity<InvitationCardDTO>(invitationToEdit,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	
	@PutMapping("/accept/{id}")
	@ApiOperation( value= "Accept a invitation card", notes = "Returns the invitation card being accepted", httpMethod="PUT")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 400, message ="Bad Request")})
	public ResponseEntity<InvitationCardDTO> acceptInvitation (@PathVariable("id") Long id){
		
		InvitationCardDTO invitationToAccept = invitationService.acceptInvitation(id);
		
		return ( invitationToAccept.getId() != null) ? new ResponseEntity<InvitationCardDTO>(invitationToAccept,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);		
	}
	
	
	
	@PutMapping("/decline/{id}")
	@ApiOperation( value= "Decline a invitation card", notes = "Returns the invitation card being declined", httpMethod="PUT")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 400, message ="Bad Request")})
	public ResponseEntity<InvitationCardDTO> declineInvitation (@PathVariable("id") Long id){
		
		InvitationCardDTO invitationToDecline = invitationService.declineInvitation(id);
		
		return ( invitationToDecline.getId() != null) ? new ResponseEntity<InvitationCardDTO>(invitationToDecline,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);		
	}
	
	
}
