package com.ftn.isa.projekat.avioCompany.avioCompanyCore.Income.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.AvioCompany.model.AvioCompany;

import lombok.Data;

@Entity
@Table (name = "income")
@Data
public class Income
{ //kad je many to one imamo samo taj jedan, kad je one to many imamo listu tih many stavki u toj klasi u kojoj smo to naveli
	//kod many to one ide spajanje po koloni ili tabeli
	//za one to many ne ide spajanje nikakvo, samo mapiranje
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id")
	private Long id;
	
	@Column (name = "value")
	private Integer value;
	
	@Column (name = "income_date")
	private LocalDateTime incomeDate;
	
	@Column (name = "tickets_number")
	private Integer ticketsNumber;
	
	/*
	 * Related company
	 */
	@JsonIgnore
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "avio_company_id", nullable = true)
	private AvioCompany companyId; 
	
	
	
	
}
