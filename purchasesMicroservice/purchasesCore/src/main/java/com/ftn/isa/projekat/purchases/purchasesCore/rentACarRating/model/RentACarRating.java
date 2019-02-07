package com.ftn.isa.projekat.purchases.purchasesCore.rentACarRating.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name ="rent_a_car_rating")
public class RentACarRating {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id")
	private Long id;
	
	@Column (name = "user_id",nullable = false)
	private Long userId;
	
	@Column (name = "rent_a_car_id",nullable = false)
	private Long rentACarId;
	
	@Column (name = "rating",nullable = false)
	private Integer rating;
	
	@Column (name = "date",nullable = false)
	private LocalDateTime ratingDate;
}
