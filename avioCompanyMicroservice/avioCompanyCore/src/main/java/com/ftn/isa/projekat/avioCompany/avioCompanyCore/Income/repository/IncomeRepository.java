package com.ftn.isa.projekat.avioCompany.avioCompanyCore.Income.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ftn.isa.projekat.avioCompany.avioCompanyCore.Income.model.Income;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long>
{
	/*
	 * Vrati prihod kompanije za odredjeni vremenski period (pocetni datum, krajnji datum, id kompanije)
	 */
	@Query(value = "select sum(value) from income where avio_company_id=:companyId and income_date>:startDate and income_date<:endDate ;", nativeQuery=true)
	Optional<Float> getIncomeByDate(Long companyId, LocalDateTime startDate, LocalDateTime endDate);
}
