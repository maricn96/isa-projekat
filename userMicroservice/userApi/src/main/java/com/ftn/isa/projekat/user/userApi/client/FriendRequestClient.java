package com.ftn.isa.projekat.user.userApi.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ftn.isa.projekat.user.userApi.dto.FriendRequestDTO;


@FeignClient(name="friendRequestClient", url = "http://localhost:8096/api/user/friendRequest")
public interface FriendRequestClient {

	@GetMapping("/{id}")
	public FriendRequestDTO getOneFriendRequestById (@PathVariable("id") Long id);
	
	@GetMapping("/all")
	public List<FriendRequestDTO> getAllRequests();
	
	@PostMapping("/")
	public FriendRequestDTO addFriendRequest(@RequestBody FriendRequestDTO dto);
	
	@DeleteMapping("/{id}")
	public FriendRequestDTO deleteFriendRequest(@PathVariable("id") Long id);
	
	@PutMapping("/{id}")
	public FriendRequestDTO changeRequest (@PathVariable("id") Long id, @RequestBody FriendRequestDTO requestDto );
	
	@PutMapping("/acceptRequest/{id}")
	public FriendRequestDTO acceptRequest (@PathVariable("id") Long id);
}
