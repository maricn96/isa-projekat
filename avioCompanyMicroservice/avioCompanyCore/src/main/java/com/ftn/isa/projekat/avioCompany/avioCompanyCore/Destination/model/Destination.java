package com.ftn.isa.projekat.avioCompany.avioCompanyCore.Destination.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.AvioCompany.model.AvioCompany;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.Flight.model.Flight;

import lombok.Data;

@Entity
@Table (name = "destination")
@Data
public class Destination
{
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id")
	private Long id;
	
	@Column (name = "name", nullable = false)
	private String name;
	
	
	/*
	 * AvioCompany
	 */
	@JsonIgnore
	@OneToMany(mappedBy="destination", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<AvioCompany> companies;
	
	
	/*
	 * List of flights that take-off from these destinations
	 */
	@JsonIgnore
	@OneToMany (mappedBy = "takeOffDestination", cascade = CascadeType.ALL)
	private List<Flight> takeOffFlights;
	
	/*
	 * List of flights that lands there
	 */
	@JsonIgnore
	@OneToMany (mappedBy = "landingDestination", cascade = CascadeType.ALL)
	private List<Flight> landingFlights;
	
	
	/*
	 * Flights that includes transfers
	 */
	@ManyToMany(mappedBy = "destinationsForTransfer")
	private List<Flight> flightsWithTransfers;
	
	
	
	
	
	
	
}
