package com.ftn.isa.projekat.purchases.purchasesCore.bonusPoints.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.purchases.purchasesApi.dto.BonusPointsDTO;
import com.ftn.isa.projekat.purchases.purchasesCore.bonusPoints.model.BonusPoints;
import com.ftn.isa.projekat.purchases.purchasesCore.bonusPoints.repository.BonusPointsRepository;
import com.ftn.isa.projekat.purchases.purchasesCore.converter.DTOBonusPointsConverter;
import com.ftn.isa.projekat.purchases.purchasesCore.utils.DatasFromOtherMicroservices;
import com.ftn.isa.projekat.user.userApi.dto.UserDTO;

@Component
public class BonusPointsServiceImpl implements IBonusPointsService {

	
	@Autowired
	BonusPointsRepository bonusPointsRepository;
	
	@Autowired
	DTOBonusPointsConverter bonusPointsConverter;
	
	@Autowired
	DatasFromOtherMicroservices servicesProxy;
	
	
	@Override
	public BonusPointsDTO findOneById(Long id) {
		
		Optional <BonusPoints> bonusPoint = bonusPointsRepository.findById(id);
		
		
		if (bonusPoint.isPresent()) {
			
			return bonusPointsConverter.convertToDTO(bonusPoint.get());
		
		}
		else {
			
			return new BonusPointsDTO();
			
		}
		
	}

	@Override
	public List<BonusPointsDTO> findAll() {
		Optional< List<BonusPoints> > list = Optional.of(bonusPointsRepository.findAll());
		ArrayList< BonusPointsDTO > bonusPointsDTO = new ArrayList< BonusPointsDTO >();
		
		if ( list.isPresent() ) {
			
			for ( BonusPoints bonusPoint : list.get()) {
				
				bonusPointsDTO.add(bonusPointsConverter.convertToDTO(bonusPoint));
				
			}
			
			return bonusPointsDTO;
			
		}
		
		return Collections.emptyList();
	}

	@Override
	public BonusPointsDTO save(BonusPointsDTO bonusPointsToSave) {
		
		/*
		 * First we need to see if user exists
		 *  */
		
		UserDTO user = servicesProxy.getUserById(bonusPointsToSave.getUserId());
		
		if(user.getId()!=null) {
			
			/*
			 * Then we need to see if there is already bonus points row for 
			 * userId. If userId exists in bonus points table, then we will only add points 
			 *  */
			
			Optional<BonusPoints> presentBonusPoints = bonusPointsRepository.findOneByUserId(bonusPointsToSave.getUserId());
			
			if(presentBonusPoints.isPresent()) {
				
				presentBonusPoints.get().setPoints(presentBonusPoints.get().getPoints() + bonusPointsToSave.getPoints());
				
				bonusPointsRepository.save(presentBonusPoints.get());
			}else {
				BonusPoints bonusPoint = bonusPointsConverter.convertFromDTO(bonusPointsToSave);
				
				bonusPointsRepository.save(bonusPoint);
				
				return bonusPointsToSave;	
				
			}
			
		}
		
		return new BonusPointsDTO();
	}

	@Override
	public BonusPointsDTO deleteById(Long id) {
		
		Optional<BonusPoints> bonusPointsToDelete = bonusPointsRepository.findById(id);
		
		if( bonusPointsToDelete.isPresent() ) {
		
			bonusPointsRepository.deleteById(id);
			
			return bonusPointsConverter.convertToDTO(bonusPointsToDelete.get());
		
		}
		
		return new BonusPointsDTO();
		
	}

	@Override
	public BonusPointsDTO changeBonusPoints(Long id, BonusPointsDTO bonusPoints) {
	
		Optional<BonusPoints> bonusPointsToChange = bonusPointsRepository.findById(id);
		
		if(bonusPointsToChange.isPresent()) {
			
			/*
			 * First we need to see if user exists
			 *  */
			
			UserDTO user = servicesProxy.getUserById(bonusPoints.getUserId());
			
			if(user.getId()!=null) {
			
				bonusPointsToChange.get().setPoints(bonusPoints.getPoints());
				bonusPointsToChange.get().setUserId(bonusPoints.getUserId());
				
				bonusPointsRepository.save(bonusPointsToChange.get());
				
				bonusPoints.setId(bonusPointsToChange.get().getId());
				
				return bonusPoints;
			}
			
		}
		
		return new BonusPointsDTO();
		
	}

}
