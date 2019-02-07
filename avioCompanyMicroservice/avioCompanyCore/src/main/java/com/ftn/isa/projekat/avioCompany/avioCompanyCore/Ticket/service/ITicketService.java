package com.ftn.isa.projekat.avioCompany.avioCompanyCore.Ticket.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.avioCompany.avioCompanyApi.dto.FlightDTO;
import com.ftn.isa.projekat.avioCompany.avioCompanyApi.dto.TicketDTO;

@Component
public interface ITicketService {

	TicketDTO findOneById(Long id);

	List<TicketDTO> findAll();

	TicketDTO save(TicketDTO dto);

	TicketDTO deleteById(Long id);

	TicketDTO changeTicket(Long id, TicketDTO ticketDTO);

	List<TicketDTO> getTicketsByPrice(Float bottomPrice, Float topPrice);

	Boolean cancelFlight(Long flightId);


}
