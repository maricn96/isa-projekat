package com.ftn.isa.projekat.purchases.purchasesCore.carRating.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ftn.isa.projekat.purchases.purchasesCore.carRating.model.CarRating;


public interface CarRatingRepository extends JpaRepository < CarRating , Long > {
	
	Optional<CarRating> findByUserId(Long id);

	Optional<CarRating> findByUserIdAndCarId(Long id, Long id2);

	@Query(value="select avg(rating) from car_rating where car_id=:id ;",nativeQuery=true)
	Optional<Float> findAverageRatingById(Long id);

}
