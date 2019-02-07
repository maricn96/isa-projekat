package com.ftn.isa.projekat.rentACar.rentACarCore.carType.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ftn.isa.projekat.rentACar.rentACarCore.car.model.Car;

import lombok.Data;

@Entity
@Table (name="car_type")
@Data
public class CarType {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name="id")
	private Long id;
	
	@Column (name="model",nullable = false)
	private String model;
	
	@Column (name="brand",nullable = false)
	private String brand;
	
	@Column (name="model_year",nullable = false)
	private int modelYear;
	
	@Column (name="car_type",nullable = false)
	private String carType;
	
	@Column (name="number_of_seats",nullable = false)
	private int numberOfSeats;
	
	@JsonIgnore
	@OneToMany(mappedBy="carType", cascade=CascadeType.ALL )
	private List<Car> cars;
}
