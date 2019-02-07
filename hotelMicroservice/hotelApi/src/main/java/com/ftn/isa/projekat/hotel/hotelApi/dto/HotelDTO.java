package com.ftn.isa.projekat.hotel.hotelApi.dto;

import java.util.List;

import org.springframework.boot.jackson.JsonComponent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonComponent
@NoArgsConstructor
@AllArgsConstructor
public class HotelDTO {
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getPromotionalDescription() {
		return promotionalDescription;
	}
	public void setPromotionalDescription(String promotionalDescription) {
		this.promotionalDescription = promotionalDescription;
	}
	private Long id;
	private String name;
	private String adress;
	private String promotionalDescription;
	
}
