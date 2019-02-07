package com.ftn.isa.projekat.purchases.purchasesCore.ticketRating.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name="ticket_rating")
public class TicketRating {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id")
	private Long id;
	
	@Column (name = "user_id",nullable = false)
	private Long userId;
	
	@Column (name = "ticket_id",nullable = false)
	private Long ticketId;
	
	@Column (name = "rating",nullable = false)
	private Integer rating;
	
	@Column (name = "date",nullable = false)
	private LocalDateTime ratingDate;

}
