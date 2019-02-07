package com.ftn.isa.projekat.purchases.purchasesCore.invitationCard.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ftn.isa.projekat.purchases.purchasesCore.reservation.model.Reservation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name= "invitation_card")
public class InvitationCard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id")
	private Long id;
	
	@Column (name = "user_who_created_id",nullable = false)
	private Long userWhoCreatedId;
	
	@Column (name="invited_user_id", nullable = false)
	private Long invitedUserId;
	
	@Column (name ="status", nullable= false)
	private String status;

	@OneToOne()
	@JoinColumn(name="reservation_id" , nullable = true)
    private Reservation reservation;
	
	
}
