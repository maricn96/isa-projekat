package com.ftn.isa.projekat.purchases.purchasesCore.invitationCard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ftn.isa.projekat.purchases.purchasesApi.dto.InvitationCardDTO;

@Service
public interface IInvitationCardService {

	public InvitationCardDTO findOneById ( Long id );
	
	public List<InvitationCardDTO> findAll();
	
	public InvitationCardDTO save (InvitationCardDTO invitationToSave);
	
	public InvitationCardDTO deleteById ( Long id );
	
	public InvitationCardDTO changeInvitation ( Long id, InvitationCardDTO invitation );

	public InvitationCardDTO acceptInvitation(Long id);

	public InvitationCardDTO declineInvitation(Long id);
}
