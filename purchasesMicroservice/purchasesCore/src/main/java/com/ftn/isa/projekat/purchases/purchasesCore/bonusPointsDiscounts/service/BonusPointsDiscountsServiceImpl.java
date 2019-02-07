package com.ftn.isa.projekat.purchases.purchasesCore.bonusPointsDiscounts.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.purchases.purchasesApi.dto.BonusPointsDiscountsDTO;
import com.ftn.isa.projekat.purchases.purchasesCore.bonusPointsDiscounts.model.BonusPointsDiscounts;
import com.ftn.isa.projekat.purchases.purchasesCore.bonusPointsDiscounts.repository.BonusPointsDiscountRepository;
import com.ftn.isa.projekat.purchases.purchasesCore.converter.DTOBonusPointsDiscountsConverter;

@Component
public class BonusPointsDiscountsServiceImpl implements IBonusPointsDiscountsService {

	@Autowired
	BonusPointsDiscountRepository discountRepository;
	
	@Autowired
	DTOBonusPointsDiscountsConverter discountConverter;
	
	
	@Override
	public BonusPointsDiscountsDTO findOneById(Long id) {
		
		Optional <BonusPointsDiscounts> bonusPoint = discountRepository.findById(id);
		
		
		if (bonusPoint.isPresent()) {
			
			return discountConverter.convertToDTO(bonusPoint.get());
		
		}
		else {
			
			return new BonusPointsDiscountsDTO();
			
		}
		
	}

	@Override
	public List<BonusPointsDiscountsDTO> findAll() {
		Optional< List<BonusPointsDiscounts> > list = Optional.of(discountRepository.findAll());
		ArrayList< BonusPointsDiscountsDTO > bonusPointsDiscountsDTO = new ArrayList< BonusPointsDiscountsDTO >();
		
		if ( list.isPresent() ) {
			
			for (BonusPointsDiscounts discount : list.get() ) {
				
				bonusPointsDiscountsDTO.add(discountConverter.convertToDTO(discount));
				
			}
			
			return bonusPointsDiscountsDTO;
			
		}
		
		return Collections.emptyList();
	}

	@Override
	public BonusPointsDiscountsDTO save(BonusPointsDiscountsDTO discountsToSave) {
		
		/*
		 * First we need to see if there is already discount row for 
		 * number of bonus points . If it exists in bonus points discounts table, then we will override it 
		 * 
		 * Also discount must be between [1, 100]
		 *  */
		
		Optional<BonusPointsDiscounts> presentBonusPoints = discountRepository.findOneByPoints(discountsToSave.getPoints());
		
		if(presentBonusPoints.isPresent() && discountsToSave.getDiscount()<=100 && discountsToSave.getDiscount()>0) {
			
			presentBonusPoints.get().setDiscount(discountsToSave.getDiscount());
			
			discountRepository.save(presentBonusPoints.get());
			
			
		}else {
			if(discountsToSave.getDiscount()<=100 && discountsToSave.getDiscount()>0) {
			BonusPointsDiscounts bonusPoint = discountConverter.convertFromDTO(discountsToSave);
			discountRepository.save(bonusPoint);
			}
			
			return discountsToSave;			
		}
		
		return new BonusPointsDiscountsDTO();
	}

	@Override
	public BonusPointsDiscountsDTO deleteById(Long id) {
		
		Optional<BonusPointsDiscounts> bonusPointsToDelete = discountRepository.findById(id);
		
		if( bonusPointsToDelete.isPresent() ) {
		
			discountRepository.deleteById(id);
			
			return discountConverter.convertToDTO(bonusPointsToDelete.get());
		
		}
		
		return new BonusPointsDiscountsDTO();
		
	}

	@Override
	public BonusPointsDiscountsDTO changeBonusPoints(Long id, BonusPointsDiscountsDTO discount) {
	
		Optional<BonusPointsDiscounts> discountToChange = discountRepository.findById(id);
		
		if(discountToChange.isPresent() && discount.getDiscount()<=100 && discount.getDiscount()>0) {
			
			discountToChange.get().setPoints(discount.getPoints());
			discountToChange.get().setDiscount(discount.getDiscount());
			
			
			discount.setId(discountToChange.get().getId());
			
			return discount;
			
		}
		
		return new BonusPointsDiscountsDTO();
		
	}
	
	
}
