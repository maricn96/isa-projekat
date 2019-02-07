package com.ftn.isa.projekat.user.userApi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserForRegistrationDTO {

private Long id;
	
	private String name;
	
	private String surname;
	
	private String city;
	
	private String email;
	
	private String telephoneNumber;
	
	private String passport;
		
	private String password;
}
