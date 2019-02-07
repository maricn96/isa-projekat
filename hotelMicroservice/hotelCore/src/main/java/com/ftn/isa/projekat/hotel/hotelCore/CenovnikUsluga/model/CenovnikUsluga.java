package com.ftn.isa.projekat.hotel.hotelCore.CenovnikUsluga.model;

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
import com.ftn.isa.projekat.hotel.hotelCore.DodatneUsluge.model.DodatneUsluge;
import com.ftn.isa.projekat.hotel.hotelCore.Hotel.model.Hotel;

import lombok.Data;

@JsonComponent
@Entity
@Table (name="cenovnik_usluga")
@Data 

public class CenovnikUsluga {
	
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	@Column (name="id")
	private Long id;
	
	@Column (name="imeUsluge", nullable=false)
	private String imeUsluge;
	
	@Column (name="cenaUsluge", nullable=false)
	private int cenaUsluge;
	
	@JsonIgnore
	@ManyToOne ()
	@JoinColumn (name="hotel_cenovnikUsluga",nullable = false)
	private Hotel hotel_cenovnikUsluga;
	
	

}
