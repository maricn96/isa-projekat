package com.ftn.isa.projekat.rentACar.rentACarCore.carDiscounts.model;

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
import com.ftn.isa.projekat.rentACar.rentACarCore.car.model.Car;

import lombok.Data;

@Entity
@Table (name = "car_discounts")
@Data
public class CarDiscounts {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name="id")
	private Long id;
		
	@Column (name = "date_to", nullable = false)
	private LocalDateTime dateTo;
	
	@Column (name = "date_from", nullable = false)
	private LocalDateTime dateFrom;
	
	@Column (name = "discount_precentage" , nullable=false)
	private int discountPrecentage;
	
	@JsonIgnore
	@ManyToOne()
	@JoinColumn (name="car_on_discount_id", nullable= false)
	private Car carOnDiscount;
	
}
