package com.ftn.isa.projekat.avioCompany.avioCompanyCore.Flight.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ftn.isa.projekat.avioCompany.avioCompanyCore.Destination.model.Destination;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.Flight.model.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long>
{
	//ovde za vrednosti kolona idu onako kako se nazivaju u modelu, ne u DTO
	
	/*
	 * Pretraga letova OD-DO po datumu
	 */
	@Query(value = "select f.id, f.avio_company_id, f.take_off_destination_id, f.all_tickets, f.avg_rating, f.tickets_sold, f.landing_destination_id, f.landing_time, f.take_off_time, f.flight_length, f.travel_type, f.number_of_transfers from flight f, ticket t"
		+ " where f.take_off_time > :takeOffTime and f.landing_time < :landingTime ;", nativeQuery=true)
	Optional<List<Flight>> findFlightsByDate(@Param("takeOffTime") LocalDateTime takeOffTime, @Param("landingTime") LocalDateTime landingTime);
	
	
	/*
	 * Find average rating for one flight
	 */
	@Query(value = "select avg(t.rating) FROM ticket t, flight f where f.id = :id ;", nativeQuery = true)
	Optional<Float> findAverageRating(@Param("id") Long id);
	
	/*
	 * Search flights by destinations
	 */
	@Query(value = "select distinct f.id, f.avio_company_id, f.take_off_destination_id, f.all_tickets, f.avg_rating, f.tickets_sold, f.landing_destination_id, f.landing_time, f.take_off_time, f.flight_length, f.travel_type, f.number_of_transfers from flight f, destination d where take_off_destination_id = :fromId and landing_destination_id = :toId ;", nativeQuery = true)
	Optional<List<Flight>> findFlightsByDestination(@Param("fromId") Long fromId, @Param("toId") Long toId);

	/*
	 * Search flights by type (round-trip, one-way, multi-city)
	 */
	@Query(value = "select distinct f.id, f.avio_company_id, f.take_off_destination_id, f.all_tickets, f.avg_rating, f.tickets_sold, f.landing_destination_id, f.landing_time, f.take_off_time, f.flight_length, f.travel_type, f.number_of_transfers from flight f where f.travel_type = :type ;", nativeQuery = true)
	Optional<List<Flight>> findFlightsByType(@Param("type") String type);


	/*
	 * Search flights by tickets left
	 */
	@Query(value = "select distinct f.id, f.avio_company_id, f.take_off_destination_id, f.all_tickets, f.avg_rating, f.tickets_sold, f.landing_destination_id, f.landing_time, f.take_off_time, f.flight_length, f.travel_type, f.number_of_transfers from flight f where (f.all_tickets - f.tickets_sold) >= :number ;", nativeQuery = true)
	Optional<List<Flight>> findFlightsByTicketsLeft(@Param("number") Integer number);
	
	/*
	 * Search flights by class that is included in specific flight
	 */
	@Query(value = "select distinct f.id, f.avio_company_id, f.take_off_destination_id, f.all_tickets, f.avg_rating, f.tickets_sold, f.landing_destination_id, f.landing_time, f.take_off_time, f.flight_length, f.travel_type, f.number_of_transfers from flight f where f.id in (select id from ticket t where grade = :klasa) ;", nativeQuery = true)
	Optional<List<Flight>> findFlightsByClass(@Param("klasa") String klasa);
	
	
	
	
	
	
	
	
	
	
	
}
