package com.ftn.isa.projekat.rentACar.rentACarCore.income.service;

import java.util.List;

import com.ftn.isa.projekat.rentACar.rentACarApi.dto.IncomeDTO;


public interface IIncomeService {

	public IncomeDTO findOneById ( Long id );
	
	public List<IncomeDTO> findAll();
	
	public IncomeDTO save (IncomeDTO incomeToSave);
	
	public IncomeDTO deleteById ( Long id );
	
	public IncomeDTO changeIncome ( Long id, IncomeDTO income );

}
