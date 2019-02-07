package com.ftn.isa.projekat.hotel.hotelCore.VanredneCeneNocenja.model;

import java.util.Date;
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
import com.ftn.isa.projekat.hotel.hotelCore.TipSobe.model.TipSobe;

import lombok.Data;

//@JsonComponent
@Entity
@Table (name="vanredne_cene_nocenja")
@Data 
public class VanredneCeneNocenja {

	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	@Column (name="id")
	private Long id;
	
	@Column (name="isItCheaper", nullable=false)
	private Boolean isItCheaper;
	
	@Column (name="priceChange", nullable=false)
	private int priceChange;
	
	@Column (name="dateFrom", nullable=false)
	private Date dateFrom;
	
	@Column (name="dateUntil", nullable=false)
	private Date dateUntil;
	
	@JsonIgnore
	@ManyToOne (/*cascade=CascadeType.ALL*/)
	@JoinColumn (name="hotel_vandredneCeneNocenja", nullable = false)
	private Hotel hotel_vandredneCeneNocenja;
	
	/*@JsonIgnore
	@OneToMany (mappedBy="vanredneCeneNocenja_tipSobe", cascade=CascadeType.ALL)
	private List<TipSobe> tipSobe;*/
}
