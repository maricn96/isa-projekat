package com.ftn.isa.projekat.purchases.purchasesCore.bonusPointsDiscounts.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ftn.isa.projekat.purchases.purchasesApi.dto.BonusPointsDiscountsDTO;

@Service
public interface IBonusPointsDiscountsService {

	public BonusPointsDiscountsDTO findOneById ( Long id );
	
	public List<BonusPointsDiscountsDTO> findAll();
	
	public BonusPointsDiscountsDTO save (BonusPointsDiscountsDTO bonusPointsDiscountToSave);
	
	public BonusPointsDiscountsDTO deleteById ( Long id );
	
	public BonusPointsDiscountsDTO changeBonusPoints ( Long id, BonusPointsDiscountsDTO bonusPointsDiscount );
	
}
