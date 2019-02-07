package com.ftn.isa.projekat.purchases.purchasesCore.converter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.purchases.purchasesApi.dto.AdminLinkDTO;
import com.ftn.isa.projekat.purchases.purchasesCore.adminLink.model.AdminLink;
import com.ftn.isa.projekat.purchases.purchasesCore.adminLink.repository.AdminLinkRepository;

@Component
public class DTOAdminLinkConverter {

	@Autowired
	private AdminLinkRepository adminLinkRepository;

	public AdminLinkDTO convertToDTO(AdminLink adminLink) {
		
		AdminLinkDTO dto = new AdminLinkDTO();
		
		dto.setId(adminLink.getId());
		dto.setServiceId(adminLink.getServiceId());
		dto.setRole(adminLink.getRole());
		dto.setUserId(adminLink.getUserId());
		
		
		return dto;
		
	}
	
	public AdminLink convertFromDTO (AdminLinkDTO dto) {
		
		Optional<AdminLink> adminLink = adminLinkRepository.findById(dto.getId());
		
		if(adminLink.isPresent()) {
			
			return adminLink.get();
			
		}
		
		
		AdminLink bean = new AdminLink();
		
		bean.setId(dto.getId());
		bean.setServiceId(dto.getServiceId());
		bean.setRole(dto.getRole());
		bean.setUserId(dto.getUserId());
		
		return bean;
		
	}
}
