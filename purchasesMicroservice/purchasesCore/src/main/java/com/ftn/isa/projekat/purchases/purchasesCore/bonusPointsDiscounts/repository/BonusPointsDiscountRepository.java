package com.ftn.isa.projekat.purchases.purchasesCore.bonusPointsDiscounts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.isa.projekat.purchases.purchasesCore.bonusPointsDiscounts.model.BonusPointsDiscounts;

public interface BonusPointsDiscountRepository extends JpaRepository<BonusPointsDiscounts, Long> {

	Optional<BonusPointsDiscounts> findOneByPoints(int points);
}
