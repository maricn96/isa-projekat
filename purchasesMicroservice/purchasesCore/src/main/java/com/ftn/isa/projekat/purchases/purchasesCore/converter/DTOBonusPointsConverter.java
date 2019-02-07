package com.ftn.isa.projekat.purchases.purchasesCore.converter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.purchases.purchasesApi.dto.BonusPointsDTO;
import com.ftn.isa.projekat.purchases.purchasesCore.bonusPoints.model.BonusPoints;
import com.ftn.isa.projekat.purchases.purchasesCore.bonusPoints.repository.BonusPointsRepository;

@Component
public class DTOBonusPointsConverter {

	@Autowired
	private BonusPointsRepository bonusPointsRepository;

	public BonusPointsDTO convertToDTO(BonusPoints bonusPoints) {
		
		BonusPointsDTO dto = new BonusPointsDTO();
		
		dto.setId(bonusPoints.getId());
		dto.setPoints(bonusPoints.getPoints());
		dto.setUserId(bonusPoints.getUserId());
		
		
		return dto;
		
	}
	
	public BonusPoints convertFromDTO (BonusPointsDTO dto) {
		
		Optional<BonusPoints> bonusPoints = bonusPointsRepository.findById(dto.getId());
		
		if(bonusPoints.isPresent()) {
			
			return bonusPoints.get();
			
		}
		
		
		BonusPoints bean = new BonusPoints();
		
		bean.setId(dto.getId());
		bean.setPoints(dto.getPoints());
		bean.setUserId(dto.getUserId());
		
		return bean;
		
	}
}
