package com.ftn.isa.projekat.avioCompany.avioCompanyApi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDTO
{
	private Long id;
	private Boolean fastReservation;
	private String ticketClass;
	private Integer discount;
	private Integer rating;
	private Float price;
	private Boolean isBought;
	private Boolean isCanceled;
	private String grade;
	
	private FlightDTO flight;
}
