package com.ftn.isa.projekat.purchases.purchasesCore.converter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.purchases.purchasesApi.dto.BonusPointsDiscountsDTO;
import com.ftn.isa.projekat.purchases.purchasesCore.bonusPointsDiscounts.model.BonusPointsDiscounts;
import com.ftn.isa.projekat.purchases.purchasesCore.bonusPointsDiscounts.repository.BonusPointsDiscountRepository;

@Component
public class DTOBonusPointsDiscountsConverter {
	
	@Autowired
	private BonusPointsDiscountRepository discountsRepository;
	
	
	public BonusPointsDiscountsDTO convertToDTO(BonusPointsDiscounts bonusPoints) {
		
		BonusPointsDiscountsDTO dto = new BonusPointsDiscountsDTO();
		
		dto.setId(bonusPoints.getId());
		dto.setPoints(bonusPoints.getPoints());
		dto.setDiscount(bonusPoints.getDiscount());
		
		
		return dto;
		
	}
	
	public BonusPointsDiscounts convertFromDTO (BonusPointsDiscountsDTO dto) {
		
		Optional<BonusPointsDiscounts> discount = discountsRepository.findById(dto.getId());
		
		if(discount.isPresent()) {
			
			return discount.get();
			
		}
		
		BonusPointsDiscounts bean = new BonusPointsDiscounts();
		
		bean.setId(dto.getId());
		bean.setPoints(dto.getPoints());
		bean.setDiscount(dto.getDiscount());
		
		return bean;
		
	}

}
