package com.ftn.isa.projekat.purchases.purchasesCore.bonusPoints.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.isa.projekat.purchases.purchasesCore.bonusPoints.model.BonusPoints;

public interface BonusPointsRepository extends JpaRepository<BonusPoints, Long> {

	Optional<BonusPoints> findOneByUserId(Long userId);
	
}
