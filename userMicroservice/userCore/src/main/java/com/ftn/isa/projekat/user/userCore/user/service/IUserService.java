package com.ftn.isa.projekat.user.userCore.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ftn.isa.projekat.user.userApi.dto.UserCredentialsDTO;
import com.ftn.isa.projekat.user.userApi.dto.UserDTO;
import com.ftn.isa.projekat.user.userApi.dto.UserForRegistrationDTO;


@Service
public interface IUserService {
	
	public UserDTO findOneById ( Long id );
	
	public List<UserDTO> findAll();
	
	public UserDTO save (UserDTO userToSave);
	
	public UserDTO deleteById ( Long id );
	
	public UserDTO changeUser ( Long id, UserDTO user );

	public List<UserDTO> getallFriends(Long id);
	
	public List<UserDTO> getAllFriendRequests(Long id);

	public UserForRegistrationDTO registerUser(UserForRegistrationDTO dto);

	public UserDTO activateUser(Long id);

	public List<UserDTO> findUsersByRole(Long id);

	public UserDTO changeRoleOfUser(Long userId, Long roleId);

	public UserDTO loginUser(UserCredentialsDTO userCredentials);

	
	
	

}
