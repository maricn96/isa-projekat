

package com.ftn.isa.projekat.avioCompany.avioCompanyCore.Income.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.hibernate.id.IncrementGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.isa.projekat.avioCompany.avioCompanyApi.dto.IncomeDTO;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.AvioCompany.model.AvioCompany;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.AvioCompany.repository.AvioCompanyRepository;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.Income.model.Income;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.Income.repository.IncomeRepository;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.dtoConverter.DTOAvioCompanyConverter;
import com.ftn.isa.projekat.avioCompany.avioCompanyCore.dtoConverter.DTOIncomeConverter;

@Service
public class IncomeServiceImpl implements IIncomeService
{
	@Autowired
	IncomeRepository incomeRepository;
	@Autowired
	AvioCompanyRepository companyRepository;
	
	
	@Autowired
	DTOIncomeConverter incomeConverter;
	@Autowired
	DTOAvioCompanyConverter companyConverter;
	
	
	@Override
	public IncomeDTO findOneById(Long id)
	{
		//Optional - kontejner koji moze i ne mora da sadrzi null vrednost. Moze da sadrzi 0 ili 1 vrednost
		//ako sadrzi vrednost, isPresent() ce vratiti true a get() ce vratiti tu vrednost.
		Optional<Income> income = incomeRepository.findById(id);
		
		if(income.isPresent()) //vraca tacno ako sadrzi vrednost
			return incomeConverter.convertToDTO(income.get());
		else
			return new IncomeDTO();
	}

	@Override
	public List<IncomeDTO> findAll() 
	{
		Optional< List<Income> > list = Optional.of(incomeRepository.findAll()); //of vraca optional sa trenutnom ne nula vrednosti.
		ArrayList<IncomeDTO> incDto = new ArrayList<IncomeDTO>();
		
		if(list.isPresent())
		{
			for(Income income : list.get())
			{
				incDto.add(incomeConverter.convertToDTO(income));
			}
				
			return incDto;
		}
		
		return Collections.emptyList();
	}

	@Override
	public IncomeDTO save(IncomeDTO dto)
	{
		Optional<AvioCompany> avio = companyRepository.findById(dto.getCompanyId().getId());
		
		if(avio.isPresent())
		{
			incomeRepository.save(incomeConverter.convertFromDTO(dto));
			
			return dto;
		}


		return new IncomeDTO();
	}

	@Override
	public IncomeDTO deleteById(Long id)
	{
		Optional<Income> incToDelete = incomeRepository.findById(id);
		
		if(incToDelete.isPresent())
		{
			incomeRepository.deleteById(id);
			return incomeConverter.convertToDTO(incToDelete.get());
		}
		return null;
	}

	@Override
	public IncomeDTO changeIncome(Long id, IncomeDTO income)
	{
		Optional<Income> incToChange = incomeRepository.findById(id);
		
		if(incToChange.isPresent() && income != null)
		{
			//setting AvioCompany for income
			Optional<AvioCompany> company = companyRepository.findById(income.getCompanyId().getId());
			
			if(company.isPresent())
			{
				incToChange.get().setIncomeDate(income.getIncomeDate());
				incToChange.get().setValue(income.getValue());
				incToChange.get().setTicketsNumber(income.getTicketsNumnber());
				
				incomeRepository.save(incToChange.get());
				
				income.setId(incToChange.get().getId());
				
				return income;
			}
			
		}
		
		return new IncomeDTO();
		
	}

	@Override
	public Float getSumOfIncomesByDate(Long id, LocalDateTime startDate, LocalDateTime endDate)
	{
		Optional<Float> sum = incomeRepository.getIncomeByDate(id, startDate, endDate);
		
		if(sum.isPresent())
		{
			return sum.get();
		}
		
		return null;
	}

	
	
	
}
