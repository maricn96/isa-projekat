package com.ftn.isa.projekat.avioCompany.avioCompanyCore.Ticket.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.Flight.model.Flight;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.Luggage.model.Luggage;

import lombok.Data;

@Entity
@Table (name = "ticket")
@Data
public class Ticket
{
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "fast_reservation")
	private Boolean fastReservation;
	
	@Column (name = "ticket_class")
	private String ticketClass;
	
	@Column (name = "discount") 
	private Integer discount;
	
	@Column(name = "rating")
	private Integer rating;
	
	@Column(name = "price")
	private Float price;
	
	@Column(name = "is_bought")
	private Boolean isBought;
	
	//DODATO
	@Column(name = "is_canceled")
	private Boolean isCanceled;
	
	@Column(name = "grade")
	private String grade;
	
	/*
	 * Flight where this ticket is bought
	 */
	@JsonIgnore
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "flight_id")
	private Flight flight;
	
	
	/*
	 * Luggage for one ticket
	 */
	@JsonIgnore
	@OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
	private List<Luggage> luggage;
	
}
