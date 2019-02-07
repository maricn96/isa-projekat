package com.ftn.isa.projekat.hotel.hotelApi.dto;

import java.util.Date;

import org.springframework.boot.jackson.JsonComponent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonComponent
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CeneSobaDTO {

	private Long id;
	private int cena;
	//private Boolean standardna;
	private Date datumOd;
	private Date datumDo;
	private HotelskaSobaDTO hotelskaSoba_cena;
	
}
