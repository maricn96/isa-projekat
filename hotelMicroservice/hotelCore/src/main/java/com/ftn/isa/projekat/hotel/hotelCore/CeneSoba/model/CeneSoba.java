package com.ftn.isa.projekat.hotel.hotelCore.CeneSoba.model;

import java.util.Date;

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
import com.ftn.isa.projekat.hotel.hotelCore.HotelskaSoba.model.HotelskaSoba;

import lombok.Data;

@JsonComponent
@Entity
@Table (name="cene_soba")
@Data 

public class CeneSoba {
	
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	@Column (name="id")
	private Long id;
	
	/*@Column (name = "standardna")
	private Boolean standardna;*/
	
	@Column (name="cena", nullable=false)
	private int cena;
	
	@Column (name = "datumOd")
	private Date datumOd;
	
	@Column (name = "datumDo")
	private Date datumDo;
	
	@JsonIgnore
	@ManyToOne ()
	@JoinColumn (name="hotelskaSoba_cena", nullable = false)
	private HotelskaSoba hotelskaSoba_cena;

}
