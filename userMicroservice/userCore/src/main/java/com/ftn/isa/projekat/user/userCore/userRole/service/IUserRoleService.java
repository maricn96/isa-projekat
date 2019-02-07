package com.ftn.isa.projekat.user.userCore.userRole.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ftn.isa.projekat.user.userApi.dto.UserRoleDTO;


@Service
public interface IUserRoleService {

	public UserRoleDTO findOneById ( Long id );
	
	public List<UserRoleDTO> findAll();
	
	public UserRoleDTO save (UserRoleDTO userRoleToSave);
	
	public UserRoleDTO deleteById ( Long id );
	
	public UserRoleDTO changeUserRole ( Long id, UserRoleDTO userRole );
	
}
