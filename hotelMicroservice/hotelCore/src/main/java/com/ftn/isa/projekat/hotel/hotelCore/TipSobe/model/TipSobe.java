package com.ftn.isa.projekat.hotel.hotelCore.TipSobe.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ftn.isa.projekat.hotel.hotelCore.Hotel.model.Hotel;
import com.ftn.isa.projekat.hotel.hotelCore.HotelskaSoba.model.HotelskaSoba;
import com.ftn.isa.projekat.hotel.hotelCore.VanredneCeneNocenja.model.VanredneCeneNocenja;

import lombok.Data;

@JsonComponent
@Entity
@Table (name="tip_sobe")
@Data 
public class TipSobe {

	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	@Column (name="id")
	private Long id;
	
	@Column (name="roomType", nullable=false)
	private String roomType;
	
	@Column (name = "kapacitet", nullable = false)
	private int kapacitet; //broj kreveta
	
	/*@Column (name="nocenjePrice", nullable=false)
	private int nocenjePrice;
	
	@Column (name="polupansionPrice", nullable=false)
	private int polupansionPrice;
	
	@Column (name="pansionPrice", nullable=false)
	private int pansionPrice;*/
	
	@JsonIgnore
	@OneToMany (mappedBy="tipSobe_hotelskeSobe", cascade=CascadeType.ALL)
	private List<HotelskaSoba> hotelskaSoba;
	
	@JsonIgnore
	@ManyToOne ()
	@JoinColumn (name="hotel_tipSobe", nullable = false)
	private Hotel hotel_tipSobe;
	
	//@JsonIgnore
	//@ManyToOne (/*cascade=CascadeType.ALL*/)
	//@JoinColumn (name="vanredneCeneNocenja_tipSobe", nullable = false)
	//private VanredneCeneNocenja vanredneCeneNocenja_tipSobe;
}
