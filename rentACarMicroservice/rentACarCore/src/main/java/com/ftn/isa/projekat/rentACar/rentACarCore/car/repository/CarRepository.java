package com.ftn.isa.projekat.rentACar.rentACarCore.car.repository;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ftn.isa.projekat.rentACar.rentACarApi.dto.RentACarOnDiscountDTO;
import com.ftn.isa.projekat.rentACar.rentACarCore.car.model.Car;

public interface CarRepository extends JpaRepository < Car, Long > {

	@Query(value =
			"select * from car c,car_reservation r  where c.id = r.reserved_car and r.date_from > :dateFrom AND r.date_to < :dateTo ;",
			nativeQuery = true)
	Optional< List<Car> > findReservedCars(@Param("dateFrom") LocalDateTime dateFrom, @Param("dateTo") LocalDateTime dateTo);

	
	@Query(value = "select * from car c where c.id not in (select c.id from car c,car_reservation r  where c.id = r.reserved_car and r.date_from > :dateFrom AND r.date_to < :dateTo);", nativeQuery = true)
	Optional< List<Car> > findFreeCars(@Param("dateFrom") LocalDateTime dateFrom, @Param("dateTo") LocalDateTime dateTo);


	Optional< List<Car> > findAllByCarRentServiceId(Long rentId);

	@Query(value="select car.id,rent_price,branch_office_id,rent_a_car_service_id,car_type_id"
			+ " from car inner join car_discounts on car.id=car_discounts.car_on_discount_id "
			+ "where (date_from >= :dateFrom and date_from <= :dateTo) "
			+ "or (date_to>=:dateFrom and date_to <= :dateTo);\r\n" + 
			"" 
			,nativeQuery=true)
	Optional<List<Car>> findAllCurrentlyOnDiscount(LocalDateTime dateFrom, LocalDateTime dateTo);
	
	
	@Query(value="select * from car where id not in"
			+ " (select car_on_discount_id from car_discounts "
			+ "where date_from <= :date and date_to >= :date);" 
			,nativeQuery=true)
	Optional< List<Car> > findAllNotOnDiscount(LocalDate date);

	
	
	@Query(value="select * from car where id in"
			+ " (select car_on_discount_id from car_discounts);"
			,nativeQuery=true)
	Optional<List<Car>> findAllOnDiscount();



	
}
