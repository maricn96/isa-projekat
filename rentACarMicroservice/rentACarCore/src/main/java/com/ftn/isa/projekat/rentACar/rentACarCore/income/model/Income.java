package com.ftn.isa.projekat.rentACar.rentACarCore.income.model;

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
import com.ftn.isa.projekat.rentACar.rentACarCore.rentACarService.model.RentACarService;

import lombok.Data;

@Entity
@Table (name="income")
@Data
public class Income {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name="id")
	private Long id;
	
	@Column (name="rent_income", nullable= false)
	private int rentIncome;
	
	@Column (name="date", nullable= false)
	private LocalDateTime date;
	
	@Column (name="number_of_cars",nullable = false)
	private int numberOfCars;
	
	@JsonIgnore 
    @ManyToOne ()
    @JoinColumn (name = "rent_a_car_service_id",nullable = false)
	private RentACarService incomeRentACarservice;
}
