package com.ftn.isa.projekat.user.userApi.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ftn.isa.projekat.user.userApi.dto.UserRoleDTO;


@FeignClient(name="userRoleClient" , url ="http://localhost:8096/api/user/userRole")
public interface UserRoleClient {

	@GetMapping("/{id}")
	public UserRoleDTO getOneUserRoleById (@PathVariable("id") Long id);
	
	@GetMapping("/all")
	public List<UserRoleDTO> getAllUserRoles();
	
	@PostMapping("/")
	public UserRoleDTO addUserRole(@RequestBody UserRoleDTO dto);
	
	@DeleteMapping("/{id}")
	public UserRoleDTO deleteUserRole(@PathVariable("id") Long id);
	
	@PutMapping("/{id}")
	public UserRoleDTO changeUserRole (@PathVariable("id") Long id, @RequestBody UserRoleDTO roleDto );


}
