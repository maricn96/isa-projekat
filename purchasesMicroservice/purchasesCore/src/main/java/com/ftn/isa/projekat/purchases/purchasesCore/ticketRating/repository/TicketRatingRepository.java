package com.ftn.isa.projekat.purchases.purchasesCore.ticketRating.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.isa.projekat.purchases.purchasesCore.carRating.model.CarRating;
import com.ftn.isa.projekat.purchases.purchasesCore.ticketRating.model.TicketRating;

public interface TicketRatingRepository extends JpaRepository<TicketRating, Long>
{
	Optional<CarRating> findByUserId(Long id);
}
