package com.ftn.isa.projekat.hotel.hotelCore.RezervacijeSobe.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ftn.isa.projekat.hotel.hotelCore.Hotel.model.Hotel;
import com.ftn.isa.projekat.hotel.hotelCore.HotelskaSoba.model.HotelskaSoba;

import lombok.Data;

@Data
@JsonComponent
@Entity
@Table (name="rezervacija_sobe")


public class RezervacijeSobe {

	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	@Column (name="id")
	private Long id;
	
	@Column (name="totalPrice", nullable=false)
	private int totalPrice;
	
	@Column (name="dateFrom", nullable=false)
	private Date dateFrom;
	
	@Column (name="dateUntil", nullable=false)
	private Date dateUntil;

	@JsonIgnore
	@ManyToOne ()
	@JoinColumn (name="sobaId", nullable=false)
	private HotelskaSoba sobaId;
	
	@JsonIgnore
	@ManyToOne ()
	@JoinColumn (name="hotel_rezervacijeSobe", nullable = false)
	private Hotel hotel_rezervacijeSobe;
	
}
