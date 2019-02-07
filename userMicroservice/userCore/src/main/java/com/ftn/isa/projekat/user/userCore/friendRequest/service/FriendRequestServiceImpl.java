package com.ftn.isa.projekat.user.userCore.friendRequest.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.user.userApi.dto.FriendRequestDTO;
import com.ftn.isa.projekat.user.userCore.converter.DTOFriendRequestConverter;
import com.ftn.isa.projekat.user.userCore.friendRequest.model.FriendRequest;
import com.ftn.isa.projekat.user.userCore.friendRequest.repository.FriendRequestRepository;
import com.ftn.isa.projekat.user.userCore.user.model.User;
import com.ftn.isa.projekat.user.userCore.user.repository.UserRepository;

@Component
public class FriendRequestServiceImpl implements IFriendRequestService {

	@Autowired
	FriendRequestRepository requestRepository;
	
	@Autowired
	UserRepository userRepository;
	
	
	
	@Autowired
	DTOFriendRequestConverter requestConverter;
	
	
	@Override
	public FriendRequestDTO findOneById(Long id) {
		
		Optional <FriendRequest> friendRequest = requestRepository.findById(id);
		
		
		if (friendRequest.isPresent()) {
			
			return requestConverter.convertToDTO(friendRequest.get());
		
		}
		else {
			
			return new FriendRequestDTO();
			
		}
		
	}

	@Override
	public List<FriendRequestDTO> findAll() {

		Optional< List<FriendRequest> > list = Optional.of(requestRepository.findAll());
		ArrayList< FriendRequestDTO > friendRequestsDTO = new ArrayList< FriendRequestDTO >();
		
		if ( list.isPresent() ) {
			
			for ( FriendRequest request : list.get()) {
				
				friendRequestsDTO.add(requestConverter.convertToDTO(request));
				
			}
			
			return friendRequestsDTO;
			
		}
		
		return Collections.emptyList();
		
	}

	@Override
	public FriendRequestDTO save(FriendRequestDTO friendRequestToSave) {
		
		/*
		 * First we need to know if these users exits in database and also if 
		 * connection between exits already in database.
		 *  */
		
		Optional<User> sourceUser = userRepository.findById(friendRequestToSave.getSourceUser());
		Optional<User> invitedUser = userRepository.findById(friendRequestToSave.getInvitedUser());
		
		Optional<FriendRequest> existingFriendRequest = requestRepository.findOneBySourceUserAndInvitedUser(friendRequestToSave.getSourceUser(),friendRequestToSave.getInvitedUser());
		
		if(sourceUser.isPresent() && invitedUser.isPresent() && !existingFriendRequest.isPresent()) {
			
			FriendRequest requestToSaveBean= requestConverter.convertFromDTO(friendRequestToSave);
			
			requestToSaveBean.setStatus("pending");
			
			requestRepository.save(requestToSaveBean);
			
			friendRequestToSave.setId(requestToSaveBean.getId());
			
			return friendRequestToSave;
		}
		
		
		return new FriendRequestDTO();
		
	}

	@Override
	public FriendRequestDTO deleteById(Long id) {
		
		Optional<FriendRequest> friendRequestToDelete = requestRepository.findById(id);
		
		if( friendRequestToDelete.isPresent() ) {
		
			requestRepository.deleteById(id);
			
			return requestConverter.convertToDTO(friendRequestToDelete.get());
		
		}
		
		return new FriendRequestDTO();
		
		
	}

	@Override
	public FriendRequestDTO changeFriendRequest(Long id, FriendRequestDTO friendRequest) {
		
		Optional<FriendRequest> requestForChange = requestRepository.findById(id);
		
		if(requestForChange.isPresent() && friendRequest !=null) {
			
			requestForChange.get().setInvitedUser(friendRequest.getInvitedUser());
			requestForChange.get().setSourceUser(friendRequest.getSourceUser());
			requestForChange.get().setStatus(friendRequest.getStatus());
			
			requestRepository.save(requestForChange.get());
			
			friendRequest.setId(requestForChange.get().getId());
			
			return friendRequest;
			
			
		}
		
		return new FriendRequestDTO();
		
	}

	@Override
	public FriendRequestDTO acceptRequest(Long id) {
		
		Optional<FriendRequest> request = requestRepository.findById(id);
		
		if(request.isPresent()) {
			
			request.get().setStatus("active");
			
			requestRepository.save(request.get());
			
			return requestConverter.convertToDTO(request.get());
			
		}
		
		return new FriendRequestDTO();
	}

}
