package com.ftn.isa.projekat.rentACar.rentACarCore.car.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.isa.projekat.rentACar.rentACarApi.dto.CarDTO;
import com.ftn.isa.projekat.rentACar.rentACarApi.dto.RentACarOnDiscountDTO;
import com.ftn.isa.projekat.rentACar.rentACarCore.branchOffice.model.BranchOffice;
import com.ftn.isa.projekat.rentACar.rentACarCore.branchOffice.repository.BranchOfficeRepository;
import com.ftn.isa.projekat.rentACar.rentACarCore.car.model.Car;
import com.ftn.isa.projekat.rentACar.rentACarCore.car.repository.CarRepository;
import com.ftn.isa.projekat.rentACar.rentACarCore.carDiscounts.model.CarDiscounts;
import com.ftn.isa.projekat.rentACar.rentACarCore.carDiscounts.repository.CarDiscountsRepository;
import com.ftn.isa.projekat.rentACar.rentACarCore.carType.model.CarType;
import com.ftn.isa.projekat.rentACar.rentACarCore.carType.repository.CarTypeRepository;
import com.ftn.isa.projekat.rentACar.rentACarCore.dtoConverter.DTOBranchOfficeConverter;
import com.ftn.isa.projekat.rentACar.rentACarCore.dtoConverter.DTOCarConverter;
import com.ftn.isa.projekat.rentACar.rentACarCore.dtoConverter.DTOCarTypeConverter;
import com.ftn.isa.projekat.rentACar.rentACarCore.dtoConverter.DTORentACarServiceConverter;
import com.ftn.isa.projekat.rentACar.rentACarCore.rentACarService.model.RentACarService;
import com.ftn.isa.projekat.rentACar.rentACarCore.rentACarService.repository.RentACarServiceRepository;
import com.ftn.isa.projekat.rentACar.rentACarCore.reservation.model.CarReservation;
import com.ftn.isa.projekat.rentACar.rentACarCore.reservation.repository.CarReservationRepository;

@Service
public class CarServiceImpl  implements ICarService{

	@Autowired
	CarRepository carRepository;
	@Autowired
	RentACarServiceRepository rentACarServiceRepository;
	@Autowired
	CarTypeRepository carTypeRepository;
	@Autowired
	BranchOfficeRepository branchOfficeRepository;
	@Autowired
	CarReservationRepository carReservationRepository;
	@Autowired
	CarDiscountsRepository discountRepository;
	
	@Autowired
	DTOCarConverter carConverter;
	@Autowired
	DTORentACarServiceConverter rentACarServiceConverter;
	@Autowired
	DTOCarTypeConverter carTypeConverter;
	@Autowired
	DTOBranchOfficeConverter branchOfficeConverter;

	@Override
	public CarDTO findOneById(Long id) {
		
		Optional <Car> car = carRepository.findById(id);
		
		
		if (car.isPresent()) {
			
			return carConverter.convertToDTO(car.get());
		
		}
		else {
			
			return new CarDTO();
			
		}
		
	}

	@Override
	public List<CarDTO> findAll() {

		Optional< List<Car> > list = Optional.of(carRepository.findAll());
		ArrayList< CarDTO > CarsDTO = new ArrayList< CarDTO >();
		
		if ( list.isPresent() ) {
			
			for ( Car car : list.get()) {
				
				CarsDTO.add(carConverter.convertToDTO(car));
				
			}
			
			return CarsDTO;
			
		}
		
		return Collections.emptyList();
		
	}

	@Override
	public CarDTO save(CarDTO carToSave) {
		
		/*
		 * First we need to see if there is branch office, car type and rent a car service
		 * in database. If they are not , then we will return empty object and car will not be saved.
		 * 
		 *  */
		
		Optional<RentACarService> rentService = rentACarServiceRepository.findById(carToSave.getRentService().getId());
		Optional<BranchOffice> branch = branchOfficeRepository.findById(carToSave.getBranchOffice().getId());
		Optional<CarType> carType = carTypeRepository.findById(carToSave.getCarType().getId());
		
		if(rentService.isPresent() && branch.isPresent() && carType.isPresent()) {
			//now we need to see if branch office is from found rent a car service
			//if they are not, we will return also empty object with info and car will not be saved
			if(branch.get().getBranchRentService().getId() == rentService.get().getId()) {
			
				Car savedCar = carRepository.save(carConverter.convertFromDTO(carToSave));
				
				carToSave.setId(savedCar.getId());
				
				return carToSave;
				
			}
			
			CarDTO carDTO = new CarDTO();
			
			carDTO.setRentPrice(-10);
			
			return carDTO;
		}
		
		return new CarDTO();
	}

	@Override
	public CarDTO deleteById(Long id) {

		Optional<Car> carToDelete = carRepository.findById(id);
		
		
		/*
		 * Protection of deleting the car while car is still reserved.
		 * */
		for(CarReservation reservation : carToDelete.get().getCarReservations()) {
			
			if(reservation.getDateTo().isAfter(LocalDateTime.now())) {
				
				return new CarDTO();
				
			}
			
		}
		
		
		if( carToDelete.isPresent() ) {
		
			
			Car carReplace = new Car();
			//we need to go in all reservation and replace this car with empty object
			for(CarReservation reservation : carToDelete.get().getCarReservations()) {
				
				reservation.setReservedCar(carReplace);
				
				carReservationRepository.save(reservation);
				
			}
			carRepository.deleteById(id);
			return carConverter.convertToDTO(carToDelete.get());
		
		}
		
		return new CarDTO();
		
	}

	@Override
	public CarDTO changeCar(Long id, CarDTO car) {

		Optional<Car> carForChange = carRepository.findById(id);
		
		
		
		if( carForChange.isPresent() && car!=null ) {
			
			/*
			 * Preventing editing a car while the car is still reserved.
			 * */
			for(CarReservation reservation : carForChange.get().getCarReservations()) {
				
				if(reservation.getDateTo().isAfter(LocalDateTime.now())) {
					
					return new CarDTO();
					
				}
				
			}
			
			//setting rentACarService and carType for car
			Optional<RentACarService> rentService = rentACarServiceRepository.findById(car.getRentService().getId());
			Optional<CarType> carType = carTypeRepository.findById(car.getCarType().getId());
			Optional<BranchOffice> branchOffice = branchOfficeRepository.findById( car.getBranchOffice().getId() );
			
			if( rentService.isPresent() && carType.isPresent() && branchOffice.isPresent()) {
				
				//checking if branch office is from same rent a car service
				//if it is not, then we will return empty object with rentPrice = '-10'
				
				if(rentService.get().getId() == branchOffice.get().getBranchRentService().getId()) {
					
					carForChange.get().setCarRentService(rentService.get());
					carForChange.get().setCarType(carType.get());
					carForChange.get().setCarBranchOffice( branchOffice.get() );
					
					carForChange.get().setRentPrice(car.getRentPrice());
					
					carRepository.save(carForChange.get());
					
					car.setId(carForChange.get().getId());
					
					return car;	
					
				}
				else {
					
					CarDTO carDTO = new CarDTO();
					
					carDTO.setRentPrice(-10);
					
					return carDTO;
					
				}
				
				
				
				
			}
		}
		
		return new CarDTO();
		
	}

	@Override
	public List<CarDTO> getReservedCarsFromTo(LocalDateTime dateFrom, LocalDateTime dateTo) {

		if(!dateFrom.isAfter(dateTo)) {
			Optional< List<Car> > reservedCars = carRepository.findReservedCars(dateFrom,dateTo);
			
			
			ArrayList< CarDTO > CarsDTO = new ArrayList< CarDTO >();
			
			if ( reservedCars.isPresent() ) {
				
				for ( Car car : reservedCars.get()) {
					
					CarsDTO.add(carConverter.convertToDTO(car));
					
				}
				
				return CarsDTO;
				
			}
		}
		
		return Collections.emptyList();
		
	}
	
	

	@Override
	public List<CarDTO> getFreeCarsFromTo(LocalDateTime dateFrom, LocalDateTime dateTo) {

		if(!dateFrom.isAfter(dateTo)) {
		
			Optional< List<Car> > freeCars = carRepository.findFreeCars(dateFrom,dateTo);
			
			
			ArrayList< CarDTO > CarsDTO = new ArrayList< CarDTO >();
			
			if ( freeCars.isPresent() ) {
				
				for ( Car car : freeCars.get()) {
					
					CarsDTO.add(carConverter.convertToDTO(car));
					
				}
				
				return CarsDTO;
				
			}
		}
		
		return Collections.emptyList();
		
	}

	@Override
	public List<CarDTO> findAllByRentACarService(Long rentId) {

		Optional< List<Car> > list = carRepository.findAllByCarRentServiceId(rentId);
		ArrayList< CarDTO > CarsDTO = new ArrayList< CarDTO >();
		
		if ( list.isPresent() ) {
			
			for ( Car car : list.get()) {
				
				CarsDTO.add(carConverter.convertToDTO(car));
				
			}
			
			return CarsDTO;
			
		}
		
		return Collections.emptyList();
		
	}

	@Override
	public List<RentACarOnDiscountDTO> getAllCurrentlyDiscount(LocalDateTime dateFrom , LocalDateTime dateTo) {

		Optional< List<Car> > list = carRepository.findAllCurrentlyOnDiscount(dateFrom ,dateTo);
		
		ArrayList<RentACarOnDiscountDTO> cars = new ArrayList<RentACarOnDiscountDTO>();
		
		if ( list.isPresent() ) {
			
			for(Car item : list.get()) {
				
				Optional<List<CarDiscounts>> discount = discountRepository.findAllForCarOnDiscount(item.getId(),dateFrom,dateTo);
				
				RentACarOnDiscountDTO dto = new RentACarOnDiscountDTO();
				
				CarDTO itemDto = carConverter.convertToDTO(item);
				
				dto.setId(itemDto.getId());
				dto.setRentPrice(itemDto.getRentPrice());
				dto.setCarType(itemDto.getCarType());
				dto.setBranchOffice(itemDto.getBranchOffice());
				dto.setRentService(itemDto.getRentService());
				dto.setDateFrom(discount.get().get(0).getDateFrom());
				dto.setDateTo(discount.get().get(0).getDateTo());
				dto.setCarDiscountPrecentage(discount.get().get(0).getDiscountPrecentage());
				
				cars.add(dto);
				
			}
			
			return cars;
			
		}
		
		return Collections.emptyList();
		
	}

	@Override
	public List<CarDTO> getAllNotOnDiscount(LocalDate date) {

		Optional< List<Car> > list = carRepository.findAllNotOnDiscount(date);
		ArrayList< CarDTO > CarsDTO = new ArrayList< CarDTO >();
		
		if ( list.isPresent() ) {
			
			for ( Car car : list.get()) {
				
				CarsDTO.add(carConverter.convertToDTO(car));
				
			}
			
			return CarsDTO;
			
		}
		
		return Collections.emptyList();
		
	}

	@Override
	public List<CarDTO> getAllOnDiscount() {

		Optional< List<Car> > list = carRepository.findAllOnDiscount();
		ArrayList< CarDTO > CarsDTO = new ArrayList< CarDTO >();
		
		if ( list.isPresent() ) {
			
			for ( Car car : list.get()) {
				
				CarsDTO.add(carConverter.convertToDTO(car));
				
			}
			
			return CarsDTO;
			
		}
		
		return Collections.emptyList();
		
	}

	
}
