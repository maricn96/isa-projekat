package com.ftn.isa.projekat.purchases.purchasesApi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvioCompanyRatingDTO
{
	private Long id;
	private Long userId;
	private Long avioCompanyId;
	private Integer rating;
}
