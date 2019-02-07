package com.ftn.isa.projekat.avioCompany.avioCompanyCore.Luggage.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.avioCompany.avioCompanyApi.dto.LuggageDTO;

@Component
public interface ILuggageService
{

	public LuggageDTO findOneById(Long id);

	public List<LuggageDTO> findAll();

	public LuggageDTO save(LuggageDTO dto);

	public LuggageDTO deleteById(Long id);

	public LuggageDTO changeLuggage(Long id, LuggageDTO luggageDto);

}
