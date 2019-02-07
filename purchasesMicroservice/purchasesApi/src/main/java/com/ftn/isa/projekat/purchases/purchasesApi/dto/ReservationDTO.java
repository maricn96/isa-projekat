package com.ftn.isa.projekat.purchases.purchasesApi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {

	private Long id;
	
	private Long userId;
	
	private Long carReservationId;
	
	private Long roomReservationId;
	
	private Long uslugaReservationId;
	
	private Long cenovnikReservationId;
	
	private Double price;
	
	private InvitationCardDTO invitationCard;
}
