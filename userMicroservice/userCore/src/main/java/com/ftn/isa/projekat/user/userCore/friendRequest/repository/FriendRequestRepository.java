package com.ftn.isa.projekat.user.userCore.friendRequest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.isa.projekat.user.userCore.friendRequest.model.FriendRequest;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {

	Optional<FriendRequest> findOneBySourceUserAndInvitedUser(Long sourceUser, Long invitedUser);

}
