package com.ftn.isa.projekat.purchases.purchasesCore.hotelskaSobaRating.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ftn.isa.projekat.purchases.purchasesCore.hotelskaSobaRating.model.HotelskaSobaRating;

public interface HotelskaSobaRatingRepository extends JpaRepository<HotelskaSobaRating, Long>{

	/*@Query(value = "SELECT AVG(hotel_room_rating) FROM hotel_soba_rating WHERE hotelska_soba_id = :sobaId;", nativeQuery=true)
	Optional<Double> getRoomAverageRating(Long sobaId);*/
	
}
