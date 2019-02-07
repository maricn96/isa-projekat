package com.ftn.isa.projekat.avioCompany.avioCompanyCore.Destination.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.isa.projekat.avioCompany.avioCompanyCore.Destination.model.Destination;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long>
{


}
