package com.ftn.isa.projekat.purchases.purchasesCore.adminLink.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ftn.isa.projekat.purchases.purchasesApi.dto.AdminLinkDTO;

@Service
public interface IAdminLinkService {

	
	public AdminLinkDTO findOneById ( Long id );
	
	public AdminLinkDTO findOneByUserId(Long id);
	
	public List<AdminLinkDTO> findAll();
	
	public AdminLinkDTO save (AdminLinkDTO adminLinkToSave);
	
	public AdminLinkDTO deleteById ( Long id );

	
}
