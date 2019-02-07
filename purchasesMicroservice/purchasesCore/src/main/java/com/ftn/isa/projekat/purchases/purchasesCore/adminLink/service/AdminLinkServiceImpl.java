package com.ftn.isa.projekat.purchases.purchasesCore.adminLink.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.purchases.purchasesApi.dto.AdminLinkDTO;
import com.ftn.isa.projekat.purchases.purchasesCore.adminLink.model.AdminLink;
import com.ftn.isa.projekat.purchases.purchasesCore.adminLink.repository.AdminLinkRepository;
import com.ftn.isa.projekat.purchases.purchasesCore.converter.DTOAdminLinkConverter;
import com.ftn.isa.projekat.purchases.purchasesCore.utils.DatasFromOtherMicroservices;
import com.ftn.isa.projekat.rentACar.rentACarApi.dto.RentACarServiceDTO;
import com.ftn.isa.projekat.user.userApi.dto.UserDTO;

@Component
public class AdminLinkServiceImpl implements IAdminLinkService {

	@Autowired
	AdminLinkRepository adminLinkRepository;
	
	@Autowired
	DTOAdminLinkConverter adminLinkConverter;
	
	@Autowired
	DatasFromOtherMicroservices servicesProxy;
	
	
	@Override
	public AdminLinkDTO findOneById(Long id) {
		
		Optional <AdminLink> adminLink = adminLinkRepository.findById(id);
		
		
		if (adminLink.isPresent()) {
			
			return adminLinkConverter.convertToDTO(adminLink.get());
		
		}
		else {
			
			return new AdminLinkDTO();
			
		}
		
	}

	@Override
	public List<AdminLinkDTO> findAll() {
		
		Optional< List<AdminLink> > list = Optional.of(adminLinkRepository.findAll());
		ArrayList< AdminLinkDTO > adminLinkDTO = new ArrayList< AdminLinkDTO >();
		
		if ( list.isPresent() ) {
			
			for ( AdminLink adminLink : list.get()) {
				
				adminLinkDTO.add(adminLinkConverter.convertToDTO(adminLink));
				
			}
			
			return adminLinkDTO;
			
		}
		
		return Collections.emptyList();
	}

	@Override
	public AdminLinkDTO save(AdminLinkDTO adminLinkToSave) {
		
		/*
		 * First we need to see if user exists
		 *  */
		
		UserDTO user = servicesProxy.getUserById(adminLinkToSave.getUserId());
		
		
		if(user.getId()!=null) {
			
			/*
			 * At first we need to see if admin service exists. If Admin is rentacar admin, then we are looking 
			 * retacar service with adminLinkToSave's serviceId, otherwise if admin is hotel admin, then we are looking
			 * for hotel with adminLinkToSave's serviceId....
			 *  */
			RentACarServiceDTO rentService = null;
						
			//ovde bi trebala jos jedna zastita da proveri da li korisnik uopste admin rent a car servisa...
			if(adminLinkToSave.getRole().equals("rentACarServiceAdmin")) {
				
				rentService = servicesProxy.getRentACarServiceById(adminLinkToSave.getId());
				
				if(rentService.getId()==null) {
					
					return new AdminLinkDTO();
					
				}
				
			}
					
			//now we need to see if user with that role already exits in database
			//if it does then we need to override it
			Optional<AdminLink> foundAdminLink = adminLinkRepository.findOneByUserIdAndRole(adminLinkToSave.getUserId(), adminLinkToSave.getRole());
			
			if(foundAdminLink.isPresent()) {
				
				foundAdminLink.get().setServiceId(adminLinkToSave.getServiceId());
				
				adminLinkRepository.save(foundAdminLink.get());

			}
			
			
			
			adminLinkRepository.save(adminLinkConverter.convertFromDTO(adminLinkToSave));					
		}
		
		return new AdminLinkDTO();
	}

	@Override
	public AdminLinkDTO deleteById(Long id) {
		
		Optional<AdminLink> AdminLinkToDelete = adminLinkRepository.findById(id);
		
		if( AdminLinkToDelete.isPresent() ) {
		
			adminLinkRepository.deleteById(id);
			
			return adminLinkConverter.convertToDTO(AdminLinkToDelete.get());
		
		}
		
		return new AdminLinkDTO();
		
	}

	@Override
	public AdminLinkDTO findOneByUserId(Long id) {
		
		Optional <AdminLink> adminLink = adminLinkRepository.findOneByUserId(id);
		
		
		if (adminLink.isPresent()) {
			
			return adminLinkConverter.convertToDTO(adminLink.get());
		
		}
		else {
			
			return new AdminLinkDTO();
			
		}
		
	}

	
	
}
