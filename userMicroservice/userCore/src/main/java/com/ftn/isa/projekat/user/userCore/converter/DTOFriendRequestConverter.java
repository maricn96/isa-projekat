package com.ftn.isa.projekat.user.userCore.converter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.user.userApi.dto.FriendRequestDTO;
import com.ftn.isa.projekat.user.userCore.friendRequest.model.FriendRequest;
import com.ftn.isa.projekat.user.userCore.friendRequest.repository.FriendRequestRepository;


@Component
public class DTOFriendRequestConverter {
	
	@Autowired
	private FriendRequestRepository requestRepository;
	
	public FriendRequestDTO convertToDTO (FriendRequest friendRequest) {
		
		FriendRequestDTO dto = new FriendRequestDTO();
		
		dto.setId(friendRequest.getId());
		dto.setInvitedUser(friendRequest.getInvitedUser());
		dto.setSourceUser(friendRequest.getSourceUser());
		dto.setStatus(friendRequest.getStatus());

		return dto;
	}
	
	public FriendRequest convertFromDTO (FriendRequestDTO dto) {
		
		Optional<FriendRequest> request = requestRepository.findById(dto.getId());
		
		if(request.isPresent()) {
			
			return request.get();
			
		}
		
		FriendRequest bean = new FriendRequest();
		
		bean.setId(dto.getId());
		bean.setInvitedUser(dto.getInvitedUser());
		bean.setSourceUser(dto.getSourceUser());
		bean.setStatus(dto.getStatus());
		
		
		return bean;
		
	}

}
