package com.ftn.isa.projekat.purchases.purchasesCore.bonusPointsDiscounts.model;

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
@Table (name = "bonus_points_discounts")
public class BonusPointsDiscounts {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id")
	private Long id;
	
	@Column (name = "points", nullable= false)
	private int points;
	
	@Column (name = "discount", nullable= false)
	private int discount;

}
