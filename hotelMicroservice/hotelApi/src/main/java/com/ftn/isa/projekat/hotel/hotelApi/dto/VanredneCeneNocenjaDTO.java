package com.ftn.isa.projekat.hotel.hotelApi.dto;

import java.util.Date;

import org.springframework.boot.jackson.JsonComponent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonComponent
@NoArgsConstructor
@AllArgsConstructor
public class VanredneCeneNocenjaDTO {
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Boolean getIsItCheaper() {
		return isItCheaper;
	}
	public void setIsItCheaper(Boolean isItCheaper) {
		this.isItCheaper = isItCheaper;
	}
	public int getPriceChange() {
		return priceChange;
	}
	public void setPriceChange(int priceChange) {
		this.priceChange = priceChange;
	}
	public Date getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}
	public Date getDateUntil() {
		return dateUntil;
	}
	public void setDateUntil(Date dateUntil) {
		this.dateUntil = dateUntil;
	}
	public HotelDTO getHotel_vandredneCeneNocenja() {
		return hotel_vandredneCeneNocenja;
	}
	public void setHotel_vandredneCeneNocenja(HotelDTO hotel_vandredneCeneNocenja) {
		this.hotel_vandredneCeneNocenja = hotel_vandredneCeneNocenja;
	}
	private Long id;
	private Boolean isItCheaper;
	private int priceChange;
	private Date dateFrom;
	private Date dateUntil;
	private HotelDTO hotel_vandredneCeneNocenja;

}
