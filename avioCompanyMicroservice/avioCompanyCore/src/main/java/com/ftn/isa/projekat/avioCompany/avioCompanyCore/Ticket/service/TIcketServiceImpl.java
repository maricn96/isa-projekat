package com.ftn.isa.projekat.avioCompany.avioCompanyCore.Ticket.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.isa.projekat.avioCompany.avioCompanyApi.dto.FlightDTO;
import com.ftn.isa.projekat.avioCompany.avioCompanyApi.dto.TicketDTO;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.Flight.model.Flight;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.Flight.repository.FlightRepository;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.Luggage.model.Luggage;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.Luggage.repository.LuggageRepository;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.Ticket.model.Ticket;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.Ticket.repository.TicketRepository;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.dtoConverter.DTOFlightConverter;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.dtoConverter.DTOTicketConverter;

@Service
public class TicketServiceImpl implements ITicketService
{
	@Autowired
	TicketRepository tickRepo;;
	@Autowired
	DTOTicketConverter tickConv;
	
	@Autowired
	LuggageRepository lugRepo;
	
	@Autowired
	FlightRepository flRepo;
	@Autowired
	DTOFlightConverter flConv;
	

	@Override
	public TicketDTO findOneById(Long id)
	{
		Optional<Ticket> ticket = tickRepo.findById(id);
		
		if(ticket.isPresent())
			return tickConv.convertToDto(ticket.get());
		else
			return new TicketDTO();
	}

	@Override
	public List<TicketDTO> findAll()
	{
		Optional<List<Ticket>> list = Optional.of(tickRepo.findAll());
		ArrayList<TicketDTO> dtos = new ArrayList<TicketDTO>();
		
		if(list.isPresent())
		{
			for(Ticket tick : list.get())
			{
				dtos.add(tickConv.convertToDto(tick));
			}
			
			return dtos;
		}
		
		return Collections.emptyList();
	}

	@Override
	public TicketDTO save(TicketDTO dto) 
	{
		Optional<Flight> flight = flRepo.findById(dto.getFlight().getId());
		
		if(flight.isPresent())
		{
			tickRepo.save(tickConv.convertFromDto(dto));
			
			return dto;
		}
		return null;
	}

	@Override
	public TicketDTO deleteById(Long id)
	{
		Optional<Ticket> toDel = tickRepo.findById(id);
		
		if(toDel.isPresent())
		{
			//moramo sav prtljag da obrisemo koji je vezan za tu kartu
			Ticket ticket = new Ticket();
			
			for(Luggage lug : toDel.get().getLuggage())
			{
				lug.setTicket(ticket);
				
				lugRepo.save(lug);
			}
			
			tickRepo.deleteById(id);
			return tickConv.convertToDto(toDel.get());
		}
		
		return null;
	}

	
	@Override
	public TicketDTO changeTicket(Long id, TicketDTO dto)
	{
		Optional<Ticket> change = tickRepo.findById(id);
		
		if(change.isPresent() && dto != null)
		{
			Optional<Flight> flight = flRepo.findById(dto.getFlight().getId());
			
			if(flight.isPresent())
			{
				change.get().setFastReservation(dto.getFastReservation());
				change.get().setTicketClass(dto.getTicketClass());
				change.get().setDiscount(dto.getDiscount());
				change.get().setRating(dto.getRating());
				change.get().setPrice(dto.getPrice());
				change.get().setIsBought(dto.getIsBought());
				
				//ne treba dozvoliti da se moze menjati let
				
				tickRepo.save(change.get());
				
				dto.setId(change.get().getId());
				
				return dto;
			}
			
			return new TicketDTO();
		}
		
		return new TicketDTO();
	}

	/**
	 * Pronalazi karte za let u cenovnom rangu
	 */
	@Override
	public List<TicketDTO> getTicketsByPrice(Float bottomPrice, Float topPrice) 
	{
		Optional<List<Ticket>> tickets = tickRepo.findTicketsByPrice(bottomPrice, topPrice);
		
		ArrayList<TicketDTO> tickDto = new ArrayList<TicketDTO>();
		
		if(tickets.isPresent())
		{
			for(Ticket tick : tickets.get())
			{
				tickDto.add(tickConv.convertToDto(tick));
			}
			return tickDto;
		}
		
		return Collections.emptyList();
	}
	
	
	/*
	 * Cancel is possible 3 hours before flight began.
	 * @see com.ftn.isa.projekat.avioCompany.avioCompanyCore.Flight.service.IFlightService#cancelFlight(java.lang.Long)
	 */
	@Override
	public Boolean cancelFlight(Long flightId)
	{
		Boolean cancel = false;
		
		Optional<Flight> flight = flRepo.findById(flightId);
		
		LocalDateTime currentTime = LocalDateTime.now();
		
		if(flight.isPresent())
		{
			LocalDateTime takeOff = flight.get().getTakeOffTime();
			int hourTakeOff = takeOff.getHour();
			
			int currentHour = currentTime.getHour();
			
			if(currentTime.getYear() <= takeOff.getYear())
			{
				if(currentTime.getMonthValue() <= takeOff.getMonthValue())
				{
					if(currentTime.getDayOfMonth() <= takeOff.getDayOfMonth())
					{
						
						if(currentTime.getHour() > 20)
						{
							currentHour -= 4;
							hourTakeOff -= 4;
							if((currentHour + 3) <= hourTakeOff)
							{
								cancel = true;
							}
						}
						else
						{
							if((currentHour + 3) <= hourTakeOff)
							{
								cancel = true;
							}
						}  
					}
					else
						System.out.println("CANCEL_IMPOSSIBLE(DAY)");
				}
				else
					System.out.println("CANCEL_IMPOSSIBLE(MONTH)");
			}
			else
				System.out.println("CANCEL_IMPOSSIBLE(YEAR)");
		}
		else
			System.out.println("Flight doesn't exists.");
		
		System.out.println(cancel);
		
		return cancel;
	}
	

}
