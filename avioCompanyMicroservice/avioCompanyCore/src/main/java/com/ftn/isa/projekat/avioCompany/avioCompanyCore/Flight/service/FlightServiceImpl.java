package com.ftn.isa.projekat.avioCompany.avioCompanyCore.Flight.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.isa.projekat.avioCompany.avioCompanyApi.dto.FlightDTO;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.AvioCompany.model.AvioCompany;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.AvioCompany.repository.AvioCompanyRepository;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.Destination.model.Destination;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.Destination.repository.DestinationRepository;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.Flight.model.Flight;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.Flight.repository.FlightRepository;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.Ticket.model.Ticket;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.Ticket.repository.TicketRepository;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.dtoConverter.DTOAvioCompanyConverter;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.dtoConverter.DTODestinationConverter;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.dtoConverter.DTOFlightConverter;

@Service
public class FlightServiceImpl implements IFlightService
{
	@Autowired
	FlightRepository flRepo;
	@Autowired
	AvioCompanyRepository avioRepo;
	@Autowired
	DestinationRepository destRepo;
	@Autowired
	TicketRepository tickRepo;
	
	@Autowired
	DTOFlightConverter flConv;
	@Autowired
	DTOAvioCompanyConverter avioConv;
	@Autowired
	DTODestinationConverter destConv;

	@Override
	public FlightDTO findOneById(Long id)
	{
		Optional<Flight> flight = flRepo.findById(id);
		
		if(flight.isPresent())
			return flConv.convertToDTO(flight.get());
		else
			return new FlightDTO();
	}

	@Override
	public List<FlightDTO> findAll()
	{
		Optional<List<Flight>> list = Optional.of(flRepo.findAll());
		ArrayList<FlightDTO> flightDto = new ArrayList<FlightDTO>();
		
		if(list.isPresent())
		{
			for(Flight fl : list.get())
			{
				flightDto.add(flConv.convertToDTO(fl));
			}
			
			return flightDto;
		}
		
		return Collections.emptyList();
	}

	@Override
	public FlightDTO save(FlightDTO dto) 
	{
		//samo provera za airline, ako to postoji postoji sigurno i ta destinacija, pa ne moram eksplicitno proveravati destinaciju
		Optional<AvioCompany> avio = avioRepo.findById(dto.getAvioCompany().getId());
		Optional<Destination> destTake = destRepo.findById(dto.getDestinationTakeOff().getId());
		Optional<Destination> destLan = destRepo.findById(dto.getDestinationLanding().getId());
		
		
		if(avio.isPresent() && destTake.isPresent() && destLan.isPresent())
		{
			
			flRepo.save(flConv.convertFromDTO(dto));
			
			return dto;
		}
				
		return new FlightDTO();
	}

	@Override
	public FlightDTO deleteById(Long id)
	{
		Optional<Flight> flDel = flRepo.findById(id);
		
		if(flDel.isPresent())
		{
			//sad brisemo sve karte vezane za ovaj let
			Flight flight = new Flight();
			
			for(Ticket tick : flDel.get().getTickets()) //ovaj for baca error
			{
				tick.setFlight(flight);
				System.out.println(tick.getPrice());
				tickRepo.save(tick);
			}
			
			flRepo.deleteById(id);
			return flConv.convertToDTO(flDel.get());
		}

		return null;
	}

	/*
	 * Podesiti da se moze menjati sve osim destinacije poletanja
	 * @see com.ftn.isa.projekat.avioCompany.avioCompanyCore.Flight.service.IFlightService#changeFlight(java.lang.Long, com.ftn.isa.projekat.avioCompany.avioCompanyApi.dto.FlightDTO)
	 */
	@Override
	public FlightDTO changeFlight(Long id, FlightDTO dto)
	{
		Optional<Flight> flUpdate = flRepo.findById(id);
		
		if(flUpdate.isPresent() && dto != null)
		{
			Optional<AvioCompany> avio = avioRepo.findById(dto.getAvioCompany().getId());
			
			if(avio.isPresent())
			{
				
				Optional<Destination> destService = destRepo.findById(dto.getDestinationLanding().getId());
				
				if(destService.isPresent())
				{
					flUpdate.get().setTakeOffTime(dto.getTakeOffTime());
					flUpdate.get().setLandingTime(dto.getLandingTime());
					flUpdate.get().setFlightLength(dto.getFlightLength());
					flUpdate.get().setNumberOfTransfers(dto.getNumberOfTransfers());
					flUpdate.get().setAllTickets(dto.getAllTickets());
					flUpdate.get().setTicketsSold(dto.getTicketsSold());
					flUpdate.get().setTravelType(dto.getTravelType());
					
					flUpdate.get().setLandingDestination(destConv.convertFromDTO(dto.getDestinationLanding()));
					
					flRepo.save(flUpdate.get());
					
					dto.setId(flUpdate.get().getId());
					
					return dto;
				}
				
				
			}
			
			
			
			return new FlightDTO();
		}
		
		return null;
	}

	/**
	 * Pronalazi letove po datumu i vremenu
	 */
	@Override
	public List<FlightDTO> getFlightsByDate(LocalDateTime takeOffTime, LocalDateTime landingTime) 
	{
		Optional<List<Flight>> flights = flRepo.findFlightsByDate(takeOffTime, landingTime);
		
		ArrayList<FlightDTO> flDtos = new ArrayList<FlightDTO>();
		
		if(flights.isPresent())
		{
			for(Flight fl : flights.get())
			{
				flDtos.add(flConv.convertToDTO(fl));
			}
			return flDtos;
		}
		
		return Collections.emptyList();
	}
	
	
	/**
	 * Pronalazi prosecnu ocenu za let
	 */
	@Override
	public Float getAvgRating(Long id)
	{
		Optional<Float> avg = flRepo.findAverageRating(id);
		
		if(avg.isPresent())
		{
			//treba je smestiti u avg_rating u flight tabeli (ovo ne radi najbolje bas)
			flRepo.findById(id).get().setAvgRating(avg.get()); 
			return avg.get();
		}
		
		return null;
	}

	/**
	 * Vrsi pretragu po polaznim i odredisnim destinacijama
	 */
	@Override
	public List<FlightDTO> getFlightsByDestination(Long takeOffDestination, Long landingDestination)
	{
		Optional<List<Flight>> flights = flRepo.findFlightsByDestination(takeOffDestination, landingDestination);
		
		ArrayList<FlightDTO> dtos = new ArrayList<FlightDTO>();
		
		if(flights.isPresent())
		{
			for(Flight fl : flights.get())
			{
				dtos.add(flConv.convertToDTO(fl));
			}
		}
		
		
		
		return dtos;
	}

	/**
	 * Vrsi pretragu po tipu leta
	 */
	@Override
	public List<FlightDTO> getFlightsByType(String type)
	{
		Optional<List<Flight>> flights = flRepo.findFlightsByType(type);
		
		ArrayList<FlightDTO> dtos = new ArrayList<FlightDTO>();
		
		if(flights.isPresent()) //ovo treba da ne baca exc
		{
			for(Flight fl : flights.get())
			{
				dtos.add(flConv.convertToDTO(fl));
			}
		}
		
		return dtos;
	}

	/**
	 * Vrsi pretragu po preostalom broju slobodnih mesta na letu
	 */
	@Override
	public List<FlightDTO> getFlightsByTicketNumber(Integer number)
	{
		Optional<List<Flight>> flights = flRepo.findFlightsByTicketsLeft(number);
		
		ArrayList<FlightDTO> dtos = new ArrayList<FlightDTO>();
		
		if(flights.isPresent())
		{
			for(Flight fl : flights.get())
			{
				dtos.add(flConv.convertToDTO(fl));
			}
		}
		return dtos;
	}

	@Override
	public List<FlightDTO> getFlightsByClass(String klasa)
	{
		Optional<List<Flight>> flights = flRepo.findFlightsByClass(klasa);
		
		ArrayList<FlightDTO> dtos = new ArrayList<FlightDTO>();
		
		if(flights.isPresent())
		{
			for(Flight fl : flights.get())
			{
				dtos.add(flConv.convertToDTO(fl));
			}
		}
		
		return dtos;
	}


	
	
	
	
	

	
	

	
	
}
