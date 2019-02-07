package com.ftn.isa.projekat.purchases.purchasesCore.hotelRating.model;

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
@Table (name="hotel_rating_table")
public class HotelRating {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id")
	private Long id;
	
	@Column (name = "user_id", nullable = false)
	private Long userId;
	
	@Column (name = "hotel_id", nullable = false)
	private Long hotelId;
	
	@Column (name = "hotel_rating", nullable = false)
	private int rating;
	
}
