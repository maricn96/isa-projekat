package com.ftn.isa.projekat.rentACar.rentACarCore.rentACarService.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ftn.isa.projekat.rentACar.rentACarCore.rentACarService.model.RentACarService;


public interface RentACarServiceRepository extends JpaRepository< RentACarService , Long> {
	

	@Query(value="select sum(rent_income) from income  "
			+ "where date between :dateFrom and :dateTo and rent_a_car_service_id = :rentService ;",nativeQuery=true)
	Optional<Integer> findSumOfIncomes(Long rentService, LocalDateTime dateFrom, LocalDateTime dateTo);

	Optional<List<RentACarService>> findAllByName(String name);
		

}
