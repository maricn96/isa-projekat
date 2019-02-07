package com.ftn.isa.projekat.rentACar.rentACarCore.car.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.isa.projekat.rentACar.rentACarApi.dto.CarDTO;
import com.ftn.isa.projekat.rentACar.rentACarApi.dto.RentACarOnDiscountDTO;
import com.ftn.isa.projekat.rentACar.rentACarCore.car.service.ICarService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/rentacar/car")
@Api(value="car")
@CrossOrigin(origins = "http://localhost:3000")
public class CarController {

	@Autowired
	ICarService carService;
	
	
	
	@GetMapping("/{id}")
	@ApiOperation( value = "Finds one car.", notes = "Returns found car.", httpMethod="GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message = "OK"),
							 @ApiResponse( code = 404, message = "Not Found")})
	public ResponseEntity<CarDTO> getOneCarById (@PathVariable("id") Long id){
		
		CarDTO carDto = carService.findOneById(id);
		
		return ( carDto.getId()!=null)? new ResponseEntity<CarDTO>(carDto,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping("/all")
	@ApiOperation( value = "Returns all cars", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity<List<CarDTO>> getAllCars(){
		
		List<CarDTO> cars = carService.findAll();
		
		return ( !cars.isEmpty() )? new ResponseEntity<List<CarDTO>>(cars,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@PostMapping("/")
	@ApiOperation( value = "Create a car.", notes = "Returns the car being saved.", httpMethod="POST", produces = "application/json", consumes = "application/json" )
	@ApiResponses( value = {
					@ApiResponse( code = 201 , message = "Created"),
					@ApiResponse( code = 400, message= "Bad request")
	})
	public ResponseEntity<CarDTO> addCar(@RequestBody CarDTO dto){
		
		CarDTO savedCar = carService.save(dto);
		
		return ( savedCar!=null )? new ResponseEntity<CarDTO>(savedCar,HttpStatus.CREATED): new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation( value = "Delete a car.", notes = "Returns the car being deleted. If branch office is not from a rent a car service in car object, car will not be saved and user will get empty object with rentPrice attribute '-10'", httpMethod="DELETE")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity<CarDTO> deleteCar(@PathVariable("id") Long id){
		CarDTO deletedCarDTO = carService.deleteById(id);
		
		return (deletedCarDTO.getId() != null ) ? new ResponseEntity<CarDTO>(deletedCarDTO,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	
	@PutMapping("/{id}")
	@ApiOperation( value= "Change a car", notes = "Returns the car being changed", httpMethod="PUT")
	@ApiResponses( value = { 
			 @ApiResponse( code = 200, message ="OK"),
			 @ApiResponse( code = 400, message ="Bad Request")})
	public ResponseEntity<CarDTO> changeCar (@PathVariable("id") Long id, @RequestBody CarDTO carDto ){
		
		CarDTO carToEdit = carService.changeCar(id, carDto);
	
	    return ( carToEdit.getId() != null )? new ResponseEntity<CarDTO>(carToEdit,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	
	
	@GetMapping("/getReservedCars/{dateFrom}/{dateTo}")
	public ResponseEntity< List<CarDTO> > getReservedCarsFromTo(@PathVariable("dateFrom") String dateFrom , @PathVariable("dateTo") String dateTo){
		
		List<CarDTO> cars = carService.getReservedCarsFromTo(LocalDateTime.parse(dateFrom), LocalDateTime.parse(dateTo));
		
		return ( !cars.isEmpty() )? new ResponseEntity<List<CarDTO>>(cars,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);		
		
	}
	
	@GetMapping("/getFreeCars/{dateFrom}/{dateTo}")
	public ResponseEntity< List<CarDTO> > getFreeCarsFromTo(@PathVariable("dateFrom") String dateFrom , @PathVariable("dateTo") String dateTo){
		
		List<CarDTO> cars = carService.getFreeCarsFromTo(LocalDateTime.parse(dateFrom), LocalDateTime.parse(dateTo));
		
		return ( !cars.isEmpty() )? new ResponseEntity<List<CarDTO>>(cars,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);		
		
	}
	
	@GetMapping("/getByRentService/{id}")
	@ApiOperation( value = "Returns all cars by some rent a car service", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity< List<CarDTO> > getAllCarsByRentService(@PathVariable("id") Long rentId){
		
		List<CarDTO> cars = carService.findAllByRentACarService(rentId);
		
		return ( !cars.isEmpty() )? new ResponseEntity<List<CarDTO>>(cars,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}

	@GetMapping("/currentlyDiscount/{dateFrom}/{dateTo}")
	@ApiOperation( value = "Returns all cars that are on discount on that date", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity< List<RentACarOnDiscountDTO> > findAllCurrentlyOnDiscount(@PathVariable("dateFrom") String dateFrom, @PathVariable("dateTo") String dateTo){
		
		List<RentACarOnDiscountDTO> cars = carService.getAllCurrentlyDiscount(LocalDateTime.parse(dateFrom), LocalDateTime.parse(dateTo));
		
		return ( !cars.isEmpty() )? new ResponseEntity<List<RentACarOnDiscountDTO>>(cars,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);		
		
	}
	
	@GetMapping("/AllOnDiscount")
	@ApiOperation( value = "Returns all cars that are on discount", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 404, message ="Not Found")})		
	public ResponseEntity< List<CarDTO> > findAllOnDiscount(){
		
		List<CarDTO> cars = carService.getAllOnDiscount();
		
		return ( !cars.isEmpty() )? new ResponseEntity<List<CarDTO>>(cars,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);		
		
	}
	
	@GetMapping("/allNotOnDiscount/{date}")
	@ApiOperation( value = "Returns all cars that are not on discount on that date", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 404, message ="Not Found")})	
	public ResponseEntity< List<CarDTO> > findAllNotOnDiscount(@PathVariable("date") String date){
		
		List<CarDTO> cars = carService.getAllNotOnDiscount(LocalDate.parse(date));
		
		return ( !cars.isEmpty() )? new ResponseEntity<List<CarDTO>>(cars,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);		
		
	}
	
	
	
}
