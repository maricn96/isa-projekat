package com.ftn.isa.projekat.purchases.purchasesCore.adminLink.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.isa.projekat.purchases.purchasesCore.adminLink.model.AdminLink;


public interface AdminLinkRepository extends JpaRepository<AdminLink, Long> {

	Optional<AdminLink> findOneByUserId(Long userId);
	
	Optional<AdminLink> findOneByUserIdAndRole(Long userId, String role);
	
}
