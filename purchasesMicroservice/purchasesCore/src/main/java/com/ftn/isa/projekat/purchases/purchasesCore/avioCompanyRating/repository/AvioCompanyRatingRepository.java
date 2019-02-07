package com.ftn.isa.projekat.purchases.purchasesCore.avioCompanyRating.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ftn.isa.projekat.purchases.purchasesCore.avioCompanyRating.model.AvioCompanyRating;
import com.ftn.isa.projekat.purchases.purchasesCore.rentACarRating.model.RentACarRating;

public interface AvioCompanyRatingRepository extends JpaRepository<AvioCompanyRating, Long>
{
	Optional<RentACarRating> findByUserId(Long userId);

	@Query(value="select avg(rating) from avio_company_rating "
			+ "where date > :dateFrom and date < :dateTo and avio_company_id = :avioCompanyId ;\r\n",nativeQuery=true)
	Optional<Double> getAverageRating(Long avioCompanyId, LocalDate dateFrom, LocalDate dateTo);
}
