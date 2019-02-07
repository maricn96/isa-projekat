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
public class HotelskaSobaDTO {
	
	private Long id;
	private int floor;
	private int originalnaCena;
	private Date datumOd;
	private Date datumDo;
	private Boolean reserved;
	private HotelDTO hotel_hotelskeSobe;
	private TipSobeDTO tipSobe_hotelskeSobe;

}
