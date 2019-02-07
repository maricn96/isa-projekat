package com.ftn.isa.projekat.rentACar.rentACarCore.reservation.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ftn.isa.projekat.rentACar.rentACarCore.branchOffice.model.BranchOffice;
import com.ftn.isa.projekat.rentACar.rentACarCore.car.model.Car;
import com.ftn.isa.projekat.rentACar.rentACarCore.rentACarService.model.RentACarService;

import lombok.Data;

@Entity
@Table (name="car_reservation")
@Data
public class CarReservation {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name="id")
	private Long id;
	
	@Column (name = "date_from" , nullable= false)
	private LocalDateTime dateFrom;
	
	@Column (name = "date_to", nullable= false)
	private LocalDateTime dateTo;
	
	@Column (name="rating")
	private int rating;
	
	@Column (name="car_rating")
	private int carRating;
	

	@JsonIgnore
	@ManyToOne ()
	@JoinColumn (name="reserved_car", nullable= false)
	private Car reservedCar;
	
	/*
	 * Location where user will pick a car.
	 * */
	@JsonIgnore
	@ManyToOne ()
	@JoinColumn (name="branch_office_from", nullable= false)
	private BranchOffice branchOfficeFrom;
	
	/*
	 * Location where user will return a car.
	 * */
	@JsonIgnore
	@ManyToOne ()
	@JoinColumn (name="branch_office_to", nullable= false)
	private BranchOffice branchOfficeTo;
	
	
	@JsonIgnore
	@ManyToOne ()
	@JoinColumn (name="rent_a_car_service_id",nullable = false)
	private RentACarService reservationRentService;
}
