package com.ftn.isa.projekat.purchases.purchasesCore.converter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.purchases.purchasesApi.dto.InvitationCardDTO;
import com.ftn.isa.projekat.purchases.purchasesCore.invitationCard.model.InvitationCard;
import com.ftn.isa.projekat.purchases.purchasesCore.invitationCard.repository.InvitationCardRepository;

@Component
public class DTOInvitationCardConverter {
	
	@Autowired
	private DTOReservationConverter reservationConverter;
	
	
	@Autowired
	private InvitationCardRepository cardRepository;
	
	
	
	public InvitationCardDTO convertToDTO (InvitationCard bean) {
		
		InvitationCardDTO dto = new InvitationCardDTO();
		
		dto.setId(bean.getId());
		dto.setInvitedUserId(bean.getInvitedUserId());
		dto.setReservation(reservationConverter.convertToDTO(bean.getReservation()));
		dto.setStatus(bean.getStatus());
		dto.setUserWhoCreatedId(bean.getUserWhoCreatedId());
		
		
		
		return dto;
		
	}
	
	public InvitationCard convertFromDTO (InvitationCardDTO dto) {
		
		Optional<InvitationCard> card = cardRepository.findById(dto.getId());
		
		if(card.isPresent()) {
			
			return card.get();
			
		}
		
		InvitationCard bean = new InvitationCard();
		
		bean.setId(dto.getId());
		bean.setInvitedUserId(dto.getInvitedUserId());
		bean.setReservation(reservationConverter.convertFromDTO(dto.getReservation()));
		bean.setStatus(dto.getStatus());
		bean.setUserWhoCreatedId(dto.getUserWhoCreatedId());
		
		return bean;
	}

}
