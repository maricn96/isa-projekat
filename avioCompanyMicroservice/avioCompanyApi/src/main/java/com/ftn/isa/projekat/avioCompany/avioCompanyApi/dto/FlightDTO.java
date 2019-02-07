package com.ftn.isa.projekat.avioCompany.avioCompanyApi.dto;

import java.sql.Date;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightDTO 
{
	private Long id;
	private LocalDateTime takeOffTime;
	private LocalDateTime landingTime;
	private Float flightLength;
	private Integer numberOfTransfers; //broj presedanja
	private Integer allTickets;
	private Integer ticketsSold;	
	private String travelType;
	private Float avgRating;
	
	private DestinationDTO destinationTakeOff;
	private DestinationDTO destinationLanding;
	private AvioCompanyDTO avioCompany;
}
