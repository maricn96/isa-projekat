package com.ftn.isa.projekat.user.userCore.userRole.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.user.userApi.dto.UserDTO;
import com.ftn.isa.projekat.user.userApi.dto.UserRoleDTO;
import com.ftn.isa.projekat.user.userCore.converter.DTOUserConverter;
import com.ftn.isa.projekat.user.userCore.converter.DTOUserRoleConverter;
import com.ftn.isa.projekat.user.userCore.user.model.User;
import com.ftn.isa.projekat.user.userCore.user.repository.UserRepository;
import com.ftn.isa.projekat.user.userCore.userRole.model.UserRole;
import com.ftn.isa.projekat.user.userCore.userRole.repository.UserRoleRepository;

@Component
public class UserRoleServiceImpl implements IUserRoleService{
	
	@Autowired
	UserRoleRepository roleRepository;
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	DTOUserRoleConverter roleConverter;
	@Autowired
	DTOUserConverter userConverter;

	@Override
	public UserRoleDTO findOneById(Long id) {
		
		Optional <UserRole> userRole = roleRepository.findById(id);
		
		
		if (userRole.isPresent()) {
			
			return roleConverter.convertToDTO(userRole.get());
		
		}
		else {
			
			return new UserRoleDTO();
			
		}
		
	}

	@Override
	public List<UserRoleDTO> findAll() {

		Optional< List<UserRole> > list = Optional.of(roleRepository.findAll());
		ArrayList< UserRoleDTO > userRolesDTO = new ArrayList< UserRoleDTO >();
		
		if ( list.isPresent() ) {
			
			for ( UserRole role : list.get()) {
				
				userRolesDTO.add(roleConverter.convertToDTO(role));
				
			}
			
			return userRolesDTO;
			
		}
		
		return Collections.emptyList();
		
	}

	@Override
	public UserRoleDTO save(UserRoleDTO userRoleToSave) {
		
		roleRepository.save(roleConverter.convertFromDTO(userRoleToSave));
		
		return userRoleToSave;
	}

	@Override
	public UserRoleDTO deleteById(Long id) {
		
		Optional<UserRole> userRoleToDelete = roleRepository.findById(id);
		
		if( userRoleToDelete.isPresent() ) {
		
			roleRepository.deleteById(id);
			
			return roleConverter.convertToDTO(userRoleToDelete.get());
		
		}
		
		return new UserRoleDTO();
		
		
	}

	@Override
	public UserRoleDTO changeUserRole(Long id, UserRoleDTO userRole) {
		
		Optional<UserRole> roleForChange= roleRepository.findById(id);
		
		if(roleForChange.isPresent() && userRole != null) {
						
			
			
			roleForChange.get().setRole(userRole.getRole());
			
			roleRepository.save(roleForChange.get());
			
			userRole.setId(roleForChange.get().getId());
			
			return userRole;
			
			
		}
		
		return new UserRoleDTO();
		
	}

}
