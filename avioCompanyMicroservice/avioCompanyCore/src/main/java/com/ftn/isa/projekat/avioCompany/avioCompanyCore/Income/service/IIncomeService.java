package com.ftn.isa.projekat.avioCompany.avioCompanyCore.Income.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.avioCompany.avioCompanyApi.dto.IncomeDTO;

@Component
public interface IIncomeService
{

	public IncomeDTO findOneById(Long id);

	public List<IncomeDTO> findAll();

	public IncomeDTO save(IncomeDTO dto);

	public IncomeDTO deleteById(Long id);

	public IncomeDTO changeIncome(Long id, IncomeDTO dto);
	
	public Float getSumOfIncomesByDate(Long id, LocalDateTime startDate, LocalDateTime endDate);

}
