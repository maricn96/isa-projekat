package com.ftn.isa.projekat.purchases.purchasesCore.converter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.purchases.purchasesApi.dto.ReservationDTO;
import com.ftn.isa.projekat.purchases.purchasesCore.reservation.model.Reservation;
import com.ftn.isa.projekat.purchases.purchasesCore.reservation.repository.ReservationRepository;
@Component
public class DTOReservationConverter {
	
	@Autowired
	private DTOInvitationCardConverter invitationConverter;
	
	@Autowired
	private ReservationRepository reservationRepository;
	

	public ReservationDTO convertToDTO (Reservation bean) {
		
		ReservationDTO dto = new ReservationDTO();
		
		dto.setCarReservationId(bean.getCarReservationId());
		dto.setRoomReservationId(bean.getRoomReservationId());
		dto.setUslugaReservationId(bean.getUslugaReservationId());
		dto.setCenovnikReservationId(bean.getCenovnikReservationId());
		dto.setId(bean.getId());
		dto.setUserId(bean.getUserId());
		dto.setPrice(bean.getPrice());
		if(bean.getInvitation() != null) {
			dto.setInvitationCard(invitationConverter.convertToDTO(bean.getInvitation()));
		}
		
		return dto;
		
	}
	
	public Reservation convertFromDTO (ReservationDTO dto) {
		
		Optional<Reservation> reservation = reservationRepository.findById(dto.getId());
		
		if(reservation.isPresent()) {
			
			return reservation.get();
			
		}
		
		Reservation bean = new Reservation();
		
		bean.setCarReservationId(dto.getCarReservationId());
		bean.setRoomReservationId(dto.getRoomReservationId());
		bean.setUslugaReservationId(dto.getUslugaReservationId());
		bean.setCenovnikReservationId(dto.getCenovnikReservationId());
		bean.setId(dto.getId());
		bean.setUserId(dto.getUserId());
		bean.setPrice(dto.getPrice());
		if(dto.getInvitationCard() != null) {
			
			bean.setInvitation(invitationConverter.convertFromDTO(dto.getInvitationCard()));
			
		}
		
		return bean;
	}
	
}
