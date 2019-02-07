package com.ftn.isa.projekat.purchases.purchasesCore.reservation.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ftn.isa.projekat.purchases.purchasesCore.invitationCard.model.InvitationCard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name ="reservation")
public class Reservation {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name="id")
	private Long id;
	

	@Column (name = "user_id",nullable = false)
	private Long userId;
	
	
	@Column (name = "carReservation_id", nullable= true)
	private Long carReservationId;
	
	@Column (name = "roomReservation_id", nullable= true)
	private Long roomReservationId;	
	
	@Column (name = "uslugaReservation_id", nullable= true)
	private Long uslugaReservationId;	
	
	@Column (name = "cenovnikReservation_id", nullable= true)
	private Long cenovnikReservationId;	
	
	@Column (name = "price" , nullable = false)
	private Double price;
	
	@OneToOne( cascade = CascadeType.ALL,
			   mappedBy = "reservation")
    private InvitationCard invitation;
	
	
}
