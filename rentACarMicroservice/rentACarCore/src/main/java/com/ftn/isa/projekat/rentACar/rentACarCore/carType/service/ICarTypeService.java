package com.ftn.isa.projekat.rentACar.rentACarCore.carType.service;

import java.util.List;

import com.ftn.isa.projekat.rentACar.rentACarApi.dto.CarTypeDTO;


public interface ICarTypeService {
	
	public CarTypeDTO findOneById ( Long id );
	
	public List<CarTypeDTO> findAll();
	
	public CarTypeDTO save (CarTypeDTO carTypeToSave);
	
	public CarTypeDTO deleteById ( Long id );
	
	public CarTypeDTO changeCarType ( Long id, CarTypeDTO carType );

}
