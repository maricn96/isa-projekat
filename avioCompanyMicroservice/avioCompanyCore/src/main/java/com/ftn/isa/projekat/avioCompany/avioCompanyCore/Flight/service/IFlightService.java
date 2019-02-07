package com.ftn.isa.projekat.avioCompany.avioCompanyCore.Flight.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.avioCompany.avioCompanyApi.dto.FlightDTO;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.Destination.model.Destination;

@Component
public interface IFlightService {

	FlightDTO findOneById(Long id);

	List<FlightDTO> findAll();

	FlightDTO save(FlightDTO dto);

	FlightDTO deleteById(Long id);

	FlightDTO changeFlight(Long id, FlightDTO dto);
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	
	List<FlightDTO> getFlightsByDate(LocalDateTime takeOffTime, LocalDateTime landingTime); //(Y)
	
	Float getAvgRating(Long id);															//(Y)

	List<FlightDTO> getFlightsByDestination(Long fromDest, Long toDest); //

	List<FlightDTO> getFlightsByType(String type);

	List<FlightDTO> getFlightsByTicketNumber(Integer number);

	List<FlightDTO> getFlightsByClass(String klasa);
}
