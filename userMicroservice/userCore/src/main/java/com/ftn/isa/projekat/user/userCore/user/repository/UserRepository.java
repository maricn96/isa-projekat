package com.ftn.isa.projekat.user.userCore.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ftn.isa.projekat.user.userCore.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

	@Query(value="select * from user where id in (select invited_user from friend_request where source_user=:id and status='active')" + 
			" union " + 
			"select * from user where id in (select source_user from friend_request where invited_user=:id and status='active');"
			,nativeQuery=true)
	Optional<List<User>> getAllFriends(Long id);

	Optional<User> findOneByEmail(String email);

	Optional<List<User>> findAllByRoleId(Long id);

	@Query(value="select * from user where id in(select source_user from friend_request where invited_user =:id and status='pending');",nativeQuery=true)	
	Optional<List<User>> getAllFriendRequests(Long id);

	Optional<User> findByEmailAndPassword(String email, String password);

}
