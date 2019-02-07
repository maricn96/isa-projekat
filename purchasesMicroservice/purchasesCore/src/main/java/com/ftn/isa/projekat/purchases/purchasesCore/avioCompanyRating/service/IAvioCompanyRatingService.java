package com.ftn.isa.projekat.purchases.purchasesCore.avioCompanyRating.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ftn.isa.projekat.purchases.purchasesApi.dto.AvioCompanyRatingDTO;
@Service
public interface IAvioCompanyRatingService {

	AvioCompanyRatingDTO findOneById(Long id);

	List<AvioCompanyRatingDTO> findAll();

	AvioCompanyRatingDTO save(AvioCompanyRatingDTO dto);

	AvioCompanyRatingDTO deleteById(Long id);

	AvioCompanyRatingDTO changeAvioCompanyRating(Long id, AvioCompanyRatingDTO ratingDto);

	Double getAverageRating(Long avioService, LocalDate parse, LocalDate parse2);

}
