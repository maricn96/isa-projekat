package com.ftn.isa.projekat.purchases.purchasesCore.avioCompanyRating.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.purchases.purchasesApi.dto.AvioCompanyRatingDTO;
import com.ftn.isa.projekat.purchases.purchasesCore.avioCompanyRating.model.AvioCompanyRating;
import com.ftn.isa.projekat.purchases.purchasesCore.avioCompanyRating.repository.AvioCompanyRatingRepository;
import com.ftn.isa.projekat.purchases.purchasesCore.converter.DTOAvioCompanyRatingConverter;
import com.ftn.isa.projekat.purchases.purchasesCore.utils.DatasFromOtherMicroservices;
import com.ftn.isa.projekat.rentACar.rentACarApi.dto.RentACarServiceDTO;
import com.ftn.isa.projekat.user.userApi.dto.UserDTO;

@Component
public class AvioCompanyRatingServiceImpl implements IAvioCompanyRatingService
{
	@Autowired
	AvioCompanyRatingRepository avioRepo;
	
	@Autowired
	DTOAvioCompanyRatingConverter avioConv;
	
	@Autowired
	DatasFromOtherMicroservices servicesProxy;

	@Override
	public AvioCompanyRatingDTO findOneById(Long id) 
	{
		Optional <AvioCompanyRating> avioRating = avioRepo.findById(id);
		
		
		if (avioRating.isPresent()) {
			
			return avioConv.convertToDTO(avioRating.get());
		
		}
		else {
			
			return new AvioCompanyRatingDTO();
			
		}
	}

	@Override
	public List<AvioCompanyRatingDTO> findAll()
	{
		Optional<List<AvioCompanyRating>> list = Optional.of(avioRepo.findAll());
		ArrayList< AvioCompanyRatingDTO > avioDto = new ArrayList< AvioCompanyRatingDTO >();
		
		if ( list.isPresent() ) {
			
			for ( AvioCompanyRating rentAcarRating : list.get()) {
				
				avioDto.add(avioConv.convertToDTO(rentAcarRating));
				
			}
			
			return avioDto;
			
		}
		
		return Collections.emptyList();
	}

	@Override
	public AvioCompanyRatingDTO save(AvioCompanyRatingDTO dto)
	{
		UserDTO user = servicesProxy.getUserById(dto.getUserId());
		RentACarServiceDTO rentService = servicesProxy.getRentACarServiceById(dto.getAvioCompanyId());
		
		
		if(user.getId()!=null && rentService.getId()!=null && dto.getRating()>0 && dto.getRating()<6) {
		
			AvioCompanyRating rating = avioConv.convertFromDTO(dto);
			rating.setRatingDate(LocalDateTime.now());
			
			avioRepo.save(rating);
			
			return dto;
		
		}
		
		return new AvioCompanyRatingDTO();
	}

	@Override
	public AvioCompanyRatingDTO deleteById(Long id)
	{
		Optional<AvioCompanyRating> avioToDel = avioRepo.findById(id);
		
		if( avioToDel.isPresent() ) {
		
			avioRepo.deleteById(id);
			return avioConv.convertToDTO(avioToDel.get());
		
		}
		
		return new AvioCompanyRatingDTO();
	}

	@Override
	public AvioCompanyRatingDTO changeAvioCompanyRating(Long id, AvioCompanyRatingDTO ratingDto)
	{
		Optional<AvioCompanyRating> avioToChange = avioRepo.findById(id);
		
		if(avioToChange.isPresent() && ratingDto != null) 
		{
			UserDTO user = servicesProxy.getUserById(ratingDto.getUserId());
		//	AvioCompanyDTO avioService = servicesProxy.getAvioCompanyServiceById(ratingDto.getAvioCompanyId());
			
			
		//	if(user.getId()!=null && avioService.getId()!=null && ratingDto.getRating()>0 && ratingDto.getRating()<6) {
				
				avioToChange.get().setAvioCompanyId(ratingDto.getAvioCompanyId());
				avioToChange.get().setRating(ratingDto.getRating());
				avioToChange.get().setUserId(ratingDto.getUserId());
				avioToChange.get().setRatingDate(LocalDateTime.now());
					
				avioRepo.save(avioToChange.get());
					
				ratingDto.setId(avioToChange.get().getId());
					
				return ratingDto;
			
			//}
			
		}
		return new AvioCompanyRatingDTO();
		
	}

	@Override
	public Double getAverageRating(Long avioService, LocalDate parse, LocalDate parse2) 
	{
		Optional<Double> averageRating = avioRepo.getAverageRating(avioService,parse,parse2);
		
		if( averageRating.isPresent() ) {
			
			return averageRating.get();
			
		}
		
		return -1.0;
	}

}
