package com.ftn.isa.projekat.avioCompany.avioCompanyApi.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncomeDTO 
{
	private Long id;
	private Integer value;
	private LocalDateTime incomeDate;
	private Integer ticketsNumnber;
	
	private AvioCompanyDTO companyId; //u react-u odavde treba samo id pozvati (nad ovim objektom), mozda je i logicnije da se samo pozove ovde id al nema veze
}
