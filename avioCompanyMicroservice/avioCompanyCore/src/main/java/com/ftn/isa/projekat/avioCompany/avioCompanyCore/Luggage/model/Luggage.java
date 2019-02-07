package com.ftn.isa.projekat.avioCompany.avioCompanyCore.Luggage.model;

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
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.Ticket.model.Ticket;

import lombok.Data;

@Entity
@Table (name = "luggage")
@Data
public class Luggage
{
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id")
	private Long id;
	
	@Column (name = "weight")
	private Float weight;
	
	@Column (name = "width")
	private  Integer width;
	
	@Column (name = "height")
	private Integer height;
	
	@Column(name = "length")
	private Integer length;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ticket_id")
	private Ticket ticket;
}
