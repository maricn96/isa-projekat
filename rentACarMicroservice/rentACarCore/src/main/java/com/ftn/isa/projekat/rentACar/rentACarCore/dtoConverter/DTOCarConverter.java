package com.ftn.isa.projekat.rentACar.rentACarCore.dtoConverter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.rentACar.rentACarApi.dto.CarDTO;
import com.ftn.isa.projekat.rentACar.rentACarCore.car.model.Car;
import com.ftn.isa.projekat.rentACar.rentACarCore.car.repository.CarRepository;
@Component
public class DTOCarConverter {
	
	@Autowired
	private DTOCarTypeConverter carTypeConverter;
	@Autowired
	private DTORentACarServiceConverter rentServiceConverter;
	@Autowired
	private DTOBranchOfficeConverter branchOfficeConverter;
	
	@Autowired
	private CarRepository carRepository;

	
	public CarDTO convertToDTO (Car car) {
		
		CarDTO dto = new CarDTO();
		
		dto.setId(car.getId());
		dto.setRentPrice(car.getRentPrice());
		dto.setCarType( carTypeConverter.convertToDTO(car.getCarType()) );
		dto.setRentService( rentServiceConverter.convertToDTO( car.getCarRentService() ) );
		dto.setBranchOffice( branchOfficeConverter.convertToDTO( car.getCarBranchOffice() ) );
		
		
		return dto;
	}
	
	
	public Car convertFromDTO ( CarDTO carDTO ) {
		
		Optional<Car> car = carRepository.findById(carDTO.getId());
		
		if(car.isPresent()) {
			
			return car.get();
			
		}
		
		
		Car bean = new Car();
		
		bean.setRentPrice(carDTO.getRentPrice());
		bean.setCarType( carTypeConverter.convertFromDTO(carDTO.getCarType()) );
		bean.setCarRentService( rentServiceConverter.convertFromDTO( carDTO.getRentService() ) );
		bean.setCarBranchOffice( branchOfficeConverter.convertFromDTO( carDTO.getBranchOffice() ) );
		
		return bean;
	}
}



