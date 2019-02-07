package com.ftn.isa.projekat.hotel.hotelCore.PrihodiHotela.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ftn.isa.projekat.hotel.hotelCore.PrihodiHotela.model.PrihodiHotela;

public interface PrihodiHotelaRepository extends JpaRepository <PrihodiHotela, Long>{

}
