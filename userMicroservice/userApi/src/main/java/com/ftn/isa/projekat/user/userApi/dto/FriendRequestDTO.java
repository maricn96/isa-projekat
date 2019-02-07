package com.ftn.isa.projekat.user.userApi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FriendRequestDTO {
	
	private Long id;
	
	private Long sourceUser;
	
	private Long invitedUser;
	
	private String status;
	
}
