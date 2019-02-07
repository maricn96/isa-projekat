package com.ftn.isa.projekat.avioCompany.avioCompanyCore.Flight.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.AvioCompany.model.AvioCompany;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.Destination.model.Destination;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.Ticket.model.Ticket;

import lombok.Data;

@Entity
@Table (name = "flight")
@Data
public class Flight
{
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id")
	private Long id;
	
	@Column (name = "take_off_time")
	private LocalDateTime takeOffTime; 
	
	@Column (name = "landing_time")
	private LocalDateTime landingTime; 
	
	@Column (name = "flight_length")
	private Float flightLength;
	
	@Column (name = "number_of_transfers") 
	private Integer numberOfTransfers;
	
	@Column(name = "allTickets") //jedna karta = jedno mesto za sedenje
	private Integer allTickets;
	
	@Column(name = "tickets_sold")
	private Integer ticketsSold;
	
	@Column(name = "travel_type")
	private String travelType;
		
	@Column(name = "avg_rating")
	private Float avgRating;
	
	/*
	 * Destinacija za poletanje
	 */
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "takeOffDestinationId", nullable = false)
	private Destination takeOffDestination;
	
	/*
	 * Destinacija za sletanje
	 */
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "landingDestinationId", nullable = false)
	private Destination landingDestination;
	
	/*
	 * List of locations where transfer is possible
	 */
	@JsonIgnore
	@ManyToMany (fetch = FetchType.LAZY, cascade = {
			CascadeType.PERSIST, CascadeType.MERGE
	})
	@JoinTable(name = "destinations_for_transfer", joinColumns = {
			@JoinColumn(name = "flight_id")
	}, inverseJoinColumns = {
			@JoinColumn(name = "destination_id")
	})
	private List<Destination> destinationsForTransfer;
	
	
	/*
	 * Attached airline for concrete flight (dodato)
	 */
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn (name = "avio_company_id", nullable = false) //ne moze da postoji let bez aviokompanije
	private AvioCompany avioCompany;
	
	
	/*
	 * List of bought tickets
	 */
	@JsonIgnore
	@OneToMany (mappedBy = "flight", cascade = CascadeType.ALL)
	private List<Ticket> tickets;
	

	
}
