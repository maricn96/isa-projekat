package com.ftn.isa.projekat.purchases.purchasesCore.hotelskaSobaRating.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ftn.isa.projekat.purchases.purchasesCore.hotelRating.model.HotelRating;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "hotelska_soba_rating")
public class HotelskaSobaRating {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id")
	private Long id;
	
	@Column (name = "user_id", nullable = false)
	private Long userId;
	
	@Column (name = "hotel_id", nullable = false)
	private Long hotelId;
	
	@Column (name = "hotelska_soba_id", nullable = false)
	private Long hotelskaSobaId;
	
	@Column (name = "hotel_room_rating", nullable = false)
	private int rating;
	
}
