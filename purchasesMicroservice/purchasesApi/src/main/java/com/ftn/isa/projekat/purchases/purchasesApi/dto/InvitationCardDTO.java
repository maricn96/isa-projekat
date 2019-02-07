package com.ftn.isa.projekat.purchases.purchasesApi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvitationCardDTO {
	
	private Long id;
	
	private Long userWhoCreatedId;
	
	private Long invitedUserId;
	
	private String status;
	
    private ReservationDTO reservation;


}
