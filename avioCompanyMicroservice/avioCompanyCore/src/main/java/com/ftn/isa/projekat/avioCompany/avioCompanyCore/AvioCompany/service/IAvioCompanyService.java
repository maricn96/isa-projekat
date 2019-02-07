package com.ftn.isa.projekat.avioCompany.avioCompanyCore.AvioCompany.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ftn.isa.projekat.avioCompany.avioCompanyApi.dto.AvioCompanyDTO;

@Component
public interface IAvioCompanyService
{

	AvioCompanyDTO findOneById(Long id);

	List<AvioCompanyDTO> findAll();

	AvioCompanyDTO save(AvioCompanyDTO dto);

	AvioCompanyDTO deleteById(Long id);

	AvioCompanyDTO changeAvioCompany(Long id, AvioCompanyDTO avioDto);

	Float getAvgRating(Long id);


}
