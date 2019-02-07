package com.ftn.isa.projekat.purchases.purchasesCore.hotelRating.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ftn.isa.projekat.purchases.purchasesCore.hotelRating.model.HotelRating;

public interface HotelRatingRepository extends JpaRepository<HotelRating, Long>{
	
	/*@Query(value = "SELECT AVG(hotel_rating) FROM hotel_rating_table WHERE hotel_id = :hotelId;", nativeQuery=true)
	Optional<Double> getHotelAverageRating(Long hotelId);*/

}
