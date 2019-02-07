package com.ftn.isa.projekat.purchases.purchasesCore.invitationCard.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.purchases.purchasesApi.dto.InvitationCardDTO;
import com.ftn.isa.projekat.purchases.purchasesCore.converter.DTOInvitationCardConverter;
import com.ftn.isa.projekat.purchases.purchasesCore.converter.DTOReservationConverter;
import com.ftn.isa.projekat.purchases.purchasesCore.invitationCard.model.InvitationCard;
import com.ftn.isa.projekat.purchases.purchasesCore.invitationCard.repository.InvitationCardRepository;
import com.ftn.isa.projekat.purchases.purchasesCore.reservation.model.Reservation;
import com.ftn.isa.projekat.purchases.purchasesCore.reservation.repository.ReservationRepository;
import com.ftn.isa.projekat.purchases.purchasesCore.reservation.service.IReservationService;
import com.ftn.isa.projekat.purchases.purchasesCore.utils.DatasFromOtherMicroservices;
import com.ftn.isa.projekat.user.userApi.dto.UserDTO;

@Component
public class InvitationCardServiceImpl implements IInvitationCardService{
	
	@Autowired
	IReservationService reservationService;

	@Autowired
	InvitationCardRepository invitationRepository;
	@Autowired
	ReservationRepository reservationRepository;
	
	
	@Autowired
	DTOInvitationCardConverter invitationConverter;
	@Autowired
	DTOReservationConverter reservationConverterr;
	
	@Autowired
	DatasFromOtherMicroservices servicesProxy;
	
	@Override
	public InvitationCardDTO findOneById(Long id) {
		
		Optional <InvitationCard> invitation = invitationRepository.findById(id);
		
		
		if (invitation.isPresent()) {
			
			return invitationConverter.convertToDTO(invitation.get());
		
		}
		else {
			
			return new InvitationCardDTO();
			
		}	
	}

	@Override
	public List<InvitationCardDTO> findAll() {
		
		Optional< List<InvitationCard> > list = Optional.of(invitationRepository.findAll());
		ArrayList< InvitationCardDTO > invitationsDTO = new ArrayList< InvitationCardDTO >();
		
		if ( list.isPresent() ) {
			
			for ( InvitationCard invitation : list.get()) {
				
				invitationsDTO.add(invitationConverter.convertToDTO(invitation));
				
			}
			
			return invitationsDTO;
			
		}
		
		return Collections.emptyList();

	}

	@Override
	public InvitationCardDTO save(InvitationCardDTO invitationToSave) {
		
		/* 
		 * First we need to see if both of users exits and them cannot be same person
		 * */
		UserDTO user1 = servicesProxy.getUserById(invitationToSave.getUserWhoCreatedId());
		UserDTO user2 = servicesProxy.getUserById(invitationToSave.getInvitedUserId());
		
		if(user1.getId()!=null && user1.getId()!=user2.getId() && user2.getId()!=null) {
			
			
			invitationToSave.setStatus("pending");
			
			invitationRepository.save(invitationConverter.convertFromDTO(invitationToSave));
			
			return invitationToSave;
			
		}
		
		return new InvitationCardDTO();

	}

	@Override
	public InvitationCardDTO deleteById(Long id) {
		
		Optional<InvitationCard> invitationToDelete = invitationRepository.findById(id);
		
		if( invitationToDelete.isPresent() ) {
			
			invitationRepository.deleteById(id);
			return invitationConverter.convertToDTO(invitationToDelete.get());
		
		}
		
		return new InvitationCardDTO();
	}

	@Override
	public InvitationCardDTO changeInvitation(Long id, InvitationCardDTO invitation) {
		
		Optional<InvitationCard> invitationForChange = invitationRepository.findById(id);
		
		if(invitationForChange.isPresent() && invitation !=null) {
			
			Optional<Reservation> invitationReservation = reservationRepository.findById(invitation.getReservation().getId());
			
			if(invitationReservation.isPresent()) {
				
				//User who sent invitation and invited user cannot be the same person.
				if(invitation.getInvitedUserId() != invitation.getUserWhoCreatedId()) {
					
					invitationForChange.get().setInvitedUserId(invitation.getInvitedUserId());
					invitationForChange.get().setReservation(invitationReservation.get());
					
					
					invitationForChange.get().setUserWhoCreatedId(invitation.getUserWhoCreatedId());
					
					invitationRepository.save(invitationForChange.get());
					
					invitation.setId(invitationForChange.get().getId());
					
					/*
					 * Changing status may resulting deleting or adding reservation
					 *  */
					if(!invitationForChange.get().getStatus().equals(invitation.getStatus())) {
						
						if(invitation.getStatus().equals("accepted")) {
							
							invitationForChange.get().setStatus("pending");
							//first we need to save invitation, because acceptInvitation is getting invitatio directly from database
							acceptInvitation( invitationRepository.save(invitationForChange.get()).getId() );
							
						}
						else if(invitation.getStatus().equals("declined")) {
							
							invitationForChange.get().setStatus("pending");
							
							declineInvitation( invitationRepository.save(invitationForChange.get()).getId() );
						
							//If previus status was accepted we need to delete reservation and remove bonus points for both users
							if( invitationForChange.get().getStatus().equals("accepted") ) {
								
								reservationService.deleteById(invitationForChange.get().getReservation().getId());
								
								/*
								 * Ovde se uklanjaju bonus poeni u zavisnosti od destinacije leta...
								 * 
								 *  */
							}
						
						}
						else if( invitation.getStatus().equals("pending") ) {
							
							invitationForChange.get().setStatus("pending");
							
							//If previus status was accepted we need to delete reservation and remove bonus points for both users
							if( invitationForChange.get().getStatus().equals("accepted") ) {
								
								reservationService.deleteById(invitationForChange.get().getReservation().getId());
								
								/*
								 * Ovde se uklanjaju bonus poeni u zavisnosti od destinacije leta...
								 * 
								 *  */
							}
							
							
						}
					}
					
					return invitation;
				}
				
			}
			
			
			
		}
		
		return new InvitationCardDTO();
	}

	@Override
	public InvitationCardDTO acceptInvitation(Long id) {
		
		Optional<InvitationCard> invitationForAccept = invitationRepository.findById(id);
		
		if(invitationForAccept.isPresent() && invitationForAccept.get().getStatus().equals("pending")) {
			
			invitationForAccept.get().setStatus("accepted");
			
			invitationRepository.save(invitationForAccept.get());
			
			/*
			 * Ovde bi trebali dodeliti korisnicima bonus poene u zavisnosti od udaljenosti
			 * avio karte...
			 *  */
			
		}
		
		return new InvitationCardDTO();
	}

	@Override
	public InvitationCardDTO declineInvitation(Long id) {
		
		Optional<InvitationCard> invitationForDecline = invitationRepository.findById(id);
		
		if(invitationForDecline.isPresent() && invitationForDecline.get().getStatus().equals("pending")) {
			
			invitationForDecline.get().setStatus("declined");
			
			invitationForDecline.get().setReservation(null);
			
			reservationService.deleteById(invitationForDecline.get().getReservation().getId());
			
			invitationRepository.save(invitationForDecline.get());
			
		}
		
		return new InvitationCardDTO();
	}

}
