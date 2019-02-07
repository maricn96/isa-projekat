package com.ftn.isa.projekat.user.userCore.converter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.user.userApi.dto.UserDTO;
import com.ftn.isa.projekat.user.userCore.user.model.User;
import com.ftn.isa.projekat.user.userCore.user.repository.UserRepository;

@Component
public class DTOUserConverter {
	
	@Autowired
	private DTOUserRoleConverter roleConverter;
	
	@Autowired
	private UserRepository userRepository;
	
	public UserDTO convertToDTO (User user) {
		
		UserDTO dto = new UserDTO();
		
		dto.setCity(user.getCity());
		dto.setEmail(user.getEmail());
		dto.setId(user.getId());
		dto.setName(user.getName());
		dto.setSurname(user.getSurname());
		dto.setPassport(user.getPassport());
		dto.setTelephoneNumber(user.getTelephoneNumber());
		dto.setRole(roleConverter.convertToDTO(user.getRole()));
		

		return dto;
	}
	
	public User convertFromDTO (UserDTO user) {
		
		Optional<User> foundUser = userRepository.findById(user.getId());
		
		
		if(foundUser.isPresent()) {
			
			return foundUser.get();
			
		}
		
		User bean = new User();
		
		bean.setCity(user.getCity());
		bean.setEmail(user.getEmail());
		bean.setId(user.getId());
		bean.setName(user.getName());
		bean.setSurname(user.getSurname());
		bean.setPassport(user.getPassport());
		bean.setTelephoneNumber(user.getTelephoneNumber());
		bean.setRole(roleConverter.convertFromDTO(user.getRole()));
		
		return bean;
		
	}

}
