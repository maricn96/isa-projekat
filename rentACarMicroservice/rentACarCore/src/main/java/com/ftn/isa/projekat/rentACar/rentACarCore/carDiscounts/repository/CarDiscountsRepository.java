package com.ftn.isa.projekat.rentACar.rentACarCore.carDiscounts.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ftn.isa.projekat.rentACar.rentACarCore.carDiscounts.model.CarDiscounts;

public interface CarDiscountsRepository extends JpaRepository<CarDiscounts, Long>  {

	@Query(value="select * from car_discounts where car_on_discount_id = :idCar AND" + 
			" ((date_from between :dateFrom AND :dateTo) OR (date_to between :dateFrom AND :dateTo)" + 
			");",nativeQuery=true)
	Optional<List<CarDiscounts>> findCarDiscountByDate(Long idCar, LocalDateTime dateFrom, LocalDateTime dateTo);

	@Query(value="select * from car_discounts where car_on_discount_id in (select id from car where rent_a_car_service_id = :rentId)",nativeQuery=true)
	Optional<List<CarDiscounts>> findAllByCarCarRentServiceId(Long rentId);

	@Query(value="select * from car_discounts where car_on_discount_id=:id and (date_from >= :dateFrom and date_from <= :dateTo) or (date_to>=:dateFrom and date_to <= :dateTo);", nativeQuery =true)
	Optional<List<CarDiscounts>> findAllForCarOnDiscount(Long id, LocalDateTime dateFrom, LocalDateTime dateTo);
	
}
