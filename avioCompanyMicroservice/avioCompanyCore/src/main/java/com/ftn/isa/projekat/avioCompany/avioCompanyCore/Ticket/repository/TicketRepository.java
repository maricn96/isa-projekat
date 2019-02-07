package com.ftn.isa.projekat.avioCompany.avioCompanyCore.Ticket.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ftn.isa.projekat.avioCompany.avioCompanyCore.Ticket.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> 
{	
	/*
	 * Pretraga karti po OD-DO ceni
	 */
	@Query(value = "select id, price, fast_reservation, ticket_class, discount, rating, is_bought, isCanceled, flight_id from ticket where price > :bottomPrice and price < :topPrice ;", nativeQuery = true)
	Optional<List<Ticket>> findTicketsByPrice(@Param("bottomPrice") Float bottomPrice, @Param("topPrice") Float topPrice);
	
	
}
