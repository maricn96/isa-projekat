package com.ftn.isa.projekat.user.userCore.friendRequest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ftn.isa.projekat.user.userApi.dto.FriendRequestDTO;


@Service
public interface IFriendRequestService {
	
	public FriendRequestDTO findOneById ( Long id );
	
	public List<FriendRequestDTO> findAll();
	
	public FriendRequestDTO save (FriendRequestDTO friendRequestToSave);
	
	public FriendRequestDTO deleteById ( Long id );
	
	public FriendRequestDTO changeFriendRequest ( Long id, FriendRequestDTO friendRequest );

	public FriendRequestDTO acceptRequest(Long id);
	
	

}
