package com.ftn.isa.projekat.rentACar.rentACarCore.carDiscounts.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ftn.isa.projekat.rentACar.rentACarApi.dto.CarDiscountsDTO;

@Service
public interface ICarDiscountsService {

	public CarDiscountsDTO findOneById ( Long id );
	
	public List<CarDiscountsDTO> findAll();
	
	public CarDiscountsDTO save (CarDiscountsDTO discountToSave);
	
	public CarDiscountsDTO deleteById ( Long id );
	
	public CarDiscountsDTO changeDiscount ( Long id, CarDiscountsDTO discount );

	public List<CarDiscountsDTO> findAllByRentService(Long rentId);
	

}
