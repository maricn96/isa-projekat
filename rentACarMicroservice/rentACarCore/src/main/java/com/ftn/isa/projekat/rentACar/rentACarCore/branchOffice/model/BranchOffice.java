package com.ftn.isa.projekat.rentACar.rentACarCore.branchOffice.model;

import java.util.List;

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
import com.ftn.isa.projekat.rentACar.rentACarCore.car.model.Car;
import com.ftn.isa.projekat.rentACar.rentACarCore.rentACarService.model.RentACarService;
import com.ftn.isa.projekat.rentACar.rentACarCore.reservation.model.CarReservation;

import lombok.Data;

@Entity
@Table (name="branch_office")
@Data
public class BranchOffice {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id")
	private Long id;
	
	@Column (name="name",nullable = false)
	private String name;
	
	@Column (name="city", nullable = false)
	private String city;
	
	@Column (name="adress",nullable = false)
	private String adress;
	
	
	@JsonIgnore
	@ManyToOne ()
	@JoinColumn (name="rent_a_car_service_id",nullable = false)
	private RentACarService branchRentService;
	
	@JsonIgnore
	@OneToMany (mappedBy="carBranchOffice")
	private List<Car> cars;
	
	/*
	 * List of reservations which are taken from this branch office
	 * */
	@JsonIgnore
	@OneToMany (mappedBy="branchOfficeFrom")
	private List<CarReservation> reservationFromBranchOffice;
	
	/*
	 *List of reservation who has a car who needs to get back to this branch office
	 *
	 * */
	@JsonIgnore
	@OneToMany (mappedBy="branchOfficeTo")
	private List<CarReservation> reservationToBranchOffice;
}
