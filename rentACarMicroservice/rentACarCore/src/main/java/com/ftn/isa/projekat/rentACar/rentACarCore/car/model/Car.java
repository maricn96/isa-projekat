package com.ftn.isa.projekat.rentACar.rentACarCore.car.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ftn.isa.projekat.rentACar.rentACarCore.branchOffice.model.BranchOffice;
import com.ftn.isa.projekat.rentACar.rentACarCore.carDiscounts.model.CarDiscounts;
import com.ftn.isa.projekat.rentACar.rentACarCore.carType.model.CarType;
import com.ftn.isa.projekat.rentACar.rentACarCore.rentACarService.model.RentACarService;
import com.ftn.isa.projekat.rentACar.rentACarCore.reservation.model.CarReservation;

import lombok.Data;

@Entity
@Table (name="car")
@Data
public class Car {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name="id")
	private Long id;
			
	@Column (name="rent_price" ,nullable = false)
	private int rentPrice;
	
	
	@JsonIgnore
	@ManyToOne()
	@JoinColumn ( name="branch_office_id",nullable= false )
	private BranchOffice carBranchOffice;
	
	@JsonIgnore
	@ManyToOne ()
	@JoinColumn ( name = "rent_a_car_service_id",nullable = false)
	private RentACarService carRentService;
	
	@JsonIgnore
	@ManyToOne ()
	@JoinColumn (name="car_type_id", nullable = false)
	private CarType carType;
	
	@JsonIgnore
	@OneToMany (mappedBy="reservedCar")
	private List<CarReservation> carReservations;
	
	@JsonIgnore
	@OneToMany (mappedBy="carOnDiscount", cascade = CascadeType.ALL)
	private List<CarDiscounts> carDiscounts;
	
	
	
}
