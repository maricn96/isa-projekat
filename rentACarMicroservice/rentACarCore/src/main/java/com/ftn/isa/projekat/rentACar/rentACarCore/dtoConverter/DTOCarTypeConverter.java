package com.ftn.isa.projekat.rentACar.rentACarCore.dtoConverter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.rentACar.rentACarApi.dto.CarTypeDTO;
import com.ftn.isa.projekat.rentACar.rentACarCore.carType.model.CarType;
import com.ftn.isa.projekat.rentACar.rentACarCore.carType.repository.CarTypeRepository;
@Component
public class DTOCarTypeConverter {
	
	@Autowired
	public CarTypeRepository carTypeRepository;

	public CarTypeDTO convertToDTO (CarType carType) {
		
		CarTypeDTO dto = new CarTypeDTO ();
		
		dto.setBrand(carType.getBrand());
		dto.setCarType(carType.getCarType());
		dto.setId(carType.getId());
		dto.setModel(carType.getModel());
		dto.setModelYear(carType.getModelYear());
		dto.setNumberOfSeats(carType.getNumberOfSeats());
		
		return dto;
	}
	
	
	
	public CarType convertFromDTO (CarTypeDTO carTypeDTO) {
		
		Optional<CarType> carType = carTypeRepository.findById(carTypeDTO.getId());
		
		if(carType.isPresent()) {
			return carType.get();
		}
		
		CarType bean = new CarType();
		
		bean.setBrand(carTypeDTO.getBrand());
		bean.setCarType(carTypeDTO.getCarType());
		bean.setModel(carTypeDTO.getModel());
		bean.setModelYear(carTypeDTO.getModelYear());
		bean.setNumberOfSeats(carTypeDTO.getNumberOfSeats() );
		
		return bean;
	}
	
}
