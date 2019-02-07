package com.ftn.isa.projekat.user.userApi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCredentialsDTO {
	
	String email;
	String password;

}
