package com.ftn.isa.projekat.avioCompany.avioCompanyCore.AvioCompany.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ftn.isa.projekat.avioCompany.avioCompanyCore.AvioCompany.model.AvioCompany;


@Repository
public interface AvioCompanyRepository extends JpaRepository<AvioCompany, Long>
{

	@Query(value = "select avg(f.avg_rating) FROM flight f where aviocompany.id = :id ;", nativeQuery = true)
	Optional<Float> findAverageRating(@Param("id") Long id);
	
}
