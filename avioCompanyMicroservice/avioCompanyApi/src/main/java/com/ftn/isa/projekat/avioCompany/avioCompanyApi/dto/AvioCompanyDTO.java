package com.ftn.isa.projekat.avioCompany.avioCompanyApi.dto;

import java.util.List;

import javax.print.attribute.standard.Destination;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AvioCompanyDTO //uradjen
{
	private Long id;
	private String name;
	private String address;
	private String description;
	
	private DestinationDTO destination;
}
