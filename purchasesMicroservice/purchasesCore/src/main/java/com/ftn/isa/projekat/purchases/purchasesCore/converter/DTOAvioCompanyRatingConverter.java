package com.ftn.isa.projekat.purchases.purchasesCore.converter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.purchases.purchasesApi.dto.AvioCompanyRatingDTO;
import com.ftn.isa.projekat.purchases.purchasesCore.avioCompanyRating.model.AvioCompanyRating;
import com.ftn.isa.projekat.purchases.purchasesCore.avioCompanyRating.repository.AvioCompanyRatingRepository;

@Component
public class DTOAvioCompanyRatingConverter
{
	@Autowired
	private AvioCompanyRatingRepository avioRepo;
	
	
	public AvioCompanyRatingDTO convertToDTO (AvioCompanyRating bean) {
		
		AvioCompanyRatingDTO dto = new AvioCompanyRatingDTO();
		
		dto.setId(bean.getId());
		dto.setRating(bean.getRating());
		dto.setAvioCompanyId(bean.getAvioCompanyId());
		dto.setUserId(bean.getUserId());
		
		return dto;
		
	}
	
	public AvioCompanyRating convertFromDTO(AvioCompanyRatingDTO dto) {
		
		Optional<AvioCompanyRating> avioRating = avioRepo.findById(dto.getId());
		
		if(avioRating.isPresent()) {
			
			return avioRating.get();
			
		}
		
		
		AvioCompanyRating bean = new AvioCompanyRating();
		
		bean.setId(dto.getId());
		bean.setRating(dto.getRating());
		bean.setAvioCompanyId(dto.getAvioCompanyId());
		bean.setUserId(dto.getUserId());
		
		return bean;
	}
}
