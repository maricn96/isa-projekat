package com.ftn.isa.projekat.purchases.purchasesCore.carRating.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.purchases.purchasesApi.dto.CarRatingDTO;
import com.ftn.isa.projekat.purchases.purchasesCore.carRating.model.CarRating;
import com.ftn.isa.projekat.purchases.purchasesCore.carRating.repository.CarRatingRepository;
import com.ftn.isa.projekat.purchases.purchasesCore.converter.DTOCarRatingConverter;
import com.ftn.isa.projekat.purchases.purchasesCore.utils.DatasFromOtherMicroservices;
import com.ftn.isa.projekat.rentACar.rentACarApi.dto.CarDTO;
import com.ftn.isa.projekat.user.userApi.dto.UserDTO;



@Component
public class CarRatingServiceImpl implements ICarRatingService {

	@Autowired
	CarRatingRepository carRatingRepository;
	
	@Autowired
	DTOCarRatingConverter carRatingConverter;
	
	@Autowired
	DatasFromOtherMicroservices servicesProxy;
	
	@Override
	public CarRatingDTO findOneById(Long id) {
		Optional <CarRating> carRating = carRatingRepository.findById(id);
		
		
		if (carRating.isPresent()) {
			
			return carRatingConverter.convertToDTO(carRating.get());
		
		}
		else {
			
			return new CarRatingDTO();
			
		}
	}
	
	@Override
	public List<CarRatingDTO> findAll() {
		Optional< List<CarRating> > list = Optional.of(carRatingRepository.findAll());
		ArrayList< CarRatingDTO > carRatingsDTO = new ArrayList< CarRatingDTO >();
		
		if ( list.isPresent() ) {
			
			for ( CarRating carRating : list.get()) {
				
				carRatingsDTO.add(carRatingConverter.convertToDTO(carRating));
				
			}
			
			return carRatingsDTO;
			
		}
		
		return Collections.emptyList();
	}

	@Override
	public CarRatingDTO save(CarRatingDTO carRatingToSave, LocalDateTime date) {
		
		//User can rate only at the end of reservation!!
		if(LocalDateTime.now().isAfter(date)) {
			/*
			 * First we need to see if car and user exits.If not, then we will return empty object..
			 *  */
			CarDTO carForRate = servicesProxy.getCarById(carRatingToSave.getCarId());
			UserDTO userWhoRates = servicesProxy.getUserById(carRatingToSave.getUserId());
			
			if(carForRate.getId()!=null && userWhoRates.getId()!=null && carRatingToSave.getRating()>0 && carRatingToSave.getRating()<6) {
				
				//then we need to see if car rating already exits then we will override it 
				Optional<CarRating> foundRating = carRatingRepository.findByUserIdAndCarId(userWhoRates.getId(),carForRate.getId());
				
				if(foundRating.isPresent()) {
					
					foundRating.get().setRating(carRatingToSave.getRating());
					foundRating.get().setRatingDate(LocalDateTime.now());
	
					carRatingRepository.save(foundRating.get());
					
					carRatingToSave.setId(foundRating.get().getId());
				}
				else {
				
				CarRating rating = carRatingConverter.convertFromDTO(carRatingToSave);
				rating.setRatingDate(LocalDateTime.now());
				CarRating savedCar = carRatingRepository.save(rating);
				carRatingToSave.setId(savedCar.getId());
	
				}
				
				
				
				return carRatingToSave;
				
			}
		}
		
		return new CarRatingDTO();
	}

	@Override
	public CarRatingDTO deleteById(Long id) {
		
		Optional<CarRating> carRatingToDelete = carRatingRepository.findById(id);
		
		if( carRatingToDelete.isPresent() ) {
		
			carRatingRepository.deleteById(id);
			return carRatingConverter.convertToDTO(carRatingToDelete.get());
		
		}
		
		return new CarRatingDTO();
		
	}

	@Override
	public CarRatingDTO changeCarRating(Long id, CarRatingDTO carRating) {
		
		Optional<CarRating> carRatingForChange = carRatingRepository.findById(id);
		
		if(carRatingForChange.isPresent() && carRating != null) {
			
			/*
			 * First we need to see if car and user exits.If not, then we will return empty object..
			 *  */
			CarDTO carForRate = servicesProxy.getCarById(carRating.getCarId());
			UserDTO userWhoRates = servicesProxy.getUserById(carRating.getUserId());
			
			if(carForRate.getId()!=null && userWhoRates.getId()!=null && carRating.getRating()>0 && carRating.getRating()<6) {
				
				carRatingForChange.get().setCarId(carRating.getCarId());
				carRatingForChange.get().setRating(carRating.getRating());
				carRatingForChange.get().setUserId(carRating.getUserId());
				carRatingForChange.get().setRatingDate(LocalDateTime.now());
				
				carRatingRepository.save(carRatingForChange.get());
				
				carRating.setId(carRatingForChange.get().getId());
				
				return carRating;
			
			}
			
		}
		return new CarRatingDTO();
		
	}

	@Override
	public Float findAverageRatingByCar(Long id) {
		
		Optional<Float> averageRating = carRatingRepository.findAverageRatingById(id);
		
		if(averageRating.isPresent()) {
			return averageRating.get();
		}
		
		
		return -1.0f;
	}

	
	
	

	
	
}
