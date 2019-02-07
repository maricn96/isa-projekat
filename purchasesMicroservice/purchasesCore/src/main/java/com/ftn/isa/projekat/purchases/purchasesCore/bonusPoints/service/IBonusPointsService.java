package com.ftn.isa.projekat.purchases.purchasesCore.bonusPoints.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ftn.isa.projekat.purchases.purchasesApi.dto.BonusPointsDTO;

@Service
public interface IBonusPointsService {

	public BonusPointsDTO findOneById ( Long id );
	
	public List<BonusPointsDTO> findAll();
	
	public BonusPointsDTO save (BonusPointsDTO bonusPointsToSave);
	
	public BonusPointsDTO deleteById ( Long id );
	
	public BonusPointsDTO changeBonusPoints ( Long id, BonusPointsDTO bonusPoints );
	
}
