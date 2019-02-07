package com.ftn.isa.projekat.rentACar.rentACarCore.carDiscounts.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.rentACar.rentACarApi.dto.CarDiscountsDTO;
import com.ftn.isa.projekat.rentACar.rentACarCore.car.model.Car;
import com.ftn.isa.projekat.rentACar.rentACarCore.car.repository.CarRepository;
import com.ftn.isa.projekat.rentACar.rentACarCore.carDiscounts.model.CarDiscounts;
import com.ftn.isa.projekat.rentACar.rentACarCore.carDiscounts.repository.CarDiscountsRepository;
import com.ftn.isa.projekat.rentACar.rentACarCore.dtoConverter.DTOCarConverter;
import com.ftn.isa.projekat.rentACar.rentACarCore.dtoConverter.DTOCarDiscountsConverter;


@Component
public class CarDiscountsServiceImpl  implements ICarDiscountsService{

	@Autowired
	CarDiscountsRepository discountRepository;
	
	@Autowired
	CarRepository carRepository;
	
	@Autowired
	DTOCarDiscountsConverter discountConverter;
	
	@Autowired
	DTOCarConverter carConverter;
	
	
	@Override
	public CarDiscountsDTO findOneById(Long id) {
		
		Optional <CarDiscounts> discount = discountRepository.findById(id);
		
		
		if (discount.isPresent()) {
			
			return discountConverter.convertToDTO(discount.get());
		
		}
		else {
			
			return new CarDiscountsDTO();
			
		}	
	}

	@Override
	public List<CarDiscountsDTO> findAll() {
		
		Optional< List<CarDiscounts> > list = Optional.of(discountRepository.findAll());
		ArrayList< CarDiscountsDTO > discountsDTO = new ArrayList< CarDiscountsDTO >();
		
		if ( list.isPresent() ) {
			
			for ( CarDiscounts discount : list.get()) {
				
				discountsDTO.add(discountConverter.convertToDTO(discount));
				
			}
			
			return discountsDTO;
			
		}
		
		return Collections.emptyList();

	}

	@Override
	public CarDiscountsDTO save(CarDiscountsDTO discountToSave) {
		
		//At First we need to check if dateFrom is before dateTo
		//If it is after dateTo, then we will return an empty object
		if(discountToSave.getDateFrom().isAfter(discountToSave.getDateTo())) {
			
			return new CarDiscountsDTO();
			
		}
		
		/*
		 * We don't want to save discount if database already contains some discount for same car at same time...
		 * So we need to see if there is any car in this time period for discord.
		 * */
		
		Optional<List<CarDiscounts>> discounts = discountRepository.findCarDiscountByDate(discountToSave.getCarId().getId(), discountToSave.getDateFrom(), discountToSave.getDateTo());
		
		if(!discounts.isPresent()) {
			
			//If car dont exits, then we will return empty object
			Optional<Car> car = carRepository.findById(discountToSave.getCarId().getId());
			
			if(car.isPresent()) {
				
				CarDiscounts discount = discountRepository.save(discountConverter.convertFromDTO(discountToSave));
				
				discountToSave.setId(discount.getId());
				
				return discountToSave;
			
			}
			
		}
		
		return new CarDiscountsDTO();

	}

	@Override
	public CarDiscountsDTO deleteById(Long id) {
		
		Optional<CarDiscounts> discountToDelete = discountRepository.findById(id);
	
		if( discountToDelete.isPresent() ) {
	
			discountRepository.deleteById(id);
			return discountConverter.convertToDTO(discountToDelete.get());
		
		}
		
		return new CarDiscountsDTO();
	}

	@Override
	public CarDiscountsDTO changeDiscount(Long id, CarDiscountsDTO discount) {
		
		//At First we need to check if dateFrom is before dateTo
		//If it is after dateTo, then we will return an empty object
		if(discount.getDateFrom().isAfter(discount.getDateTo())) {
			
			return new CarDiscountsDTO();
			
		}
		
		Optional<CarDiscounts> discountForChange = discountRepository.findById(id);
		
		if(discountForChange.isPresent() && discount != null) {
			
			/*
			 * While updating discount, if administrator changes fields date from or date to, we need to 
			 * check if there is any discount between these dates that is not our discount (discount with same id).
			 * If we find discount that is not this one for change, we will return empty object, and update will not pass.
			 * 
			 *  */
			Optional<List<CarDiscounts>> discounts = discountRepository.findCarDiscountByDate(discountForChange.get().getCarOnDiscount().getId(), discountForChange.get().getDateFrom(), discountForChange.get().getDateTo());

			if(discounts.isPresent()) {
				for(CarDiscounts foundDiscount : discounts.get()) {
					
					if(foundDiscount.getId().equals(discountForChange.get().getId())) {
						return new CarDiscountsDTO();
					}
					
				}
			}
			
			
			Optional<Car> carOnDiscount = carRepository.findById(discount.getCarId().getId());
			
			if(carOnDiscount.isPresent() && discount.getCarDiscountPrecentage()>0 && discount.getCarDiscountPrecentage()<100) {
				
				discountForChange.get().setCarOnDiscount(carOnDiscount.get());
				discountForChange.get().setDateFrom(discount.getDateFrom());
				discountForChange.get().setDateTo(discount.getDateTo());
				discountForChange.get().setDiscountPrecentage(discount.getCarDiscountPrecentage());
				
				discountRepository.save(discountForChange.get());
				
				discount.setId(discountForChange.get().getId());
				
				return discount;
				
			}
			
			
		}
		
		return new CarDiscountsDTO();
	}

	@Override
	public List<CarDiscountsDTO> findAllByRentService(Long rentId) {
		
		Optional< List<CarDiscounts> > list = discountRepository.findAllByCarCarRentServiceId(rentId);
		ArrayList< CarDiscountsDTO > discountsDTO = new ArrayList< CarDiscountsDTO >();
		
		if ( list.isPresent() ) {
			
			for ( CarDiscounts discount : list.get()) {
				
				discountsDTO.add(discountConverter.convertToDTO(discount));
				
			}
			
			return discountsDTO;
			
		}
		
		return Collections.emptyList();

	}

	
}
