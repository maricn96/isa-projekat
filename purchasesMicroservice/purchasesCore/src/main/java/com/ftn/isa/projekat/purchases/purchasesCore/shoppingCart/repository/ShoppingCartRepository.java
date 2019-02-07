package com.ftn.isa.projekat.purchases.purchasesCore.shoppingCart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.isa.projekat.purchases.purchasesCore.shoppingCart.model.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
	
	Optional<ShoppingCart> findOneByUserId(Long userId);

	Optional<ShoppingCart> findByUserId(Long id);

}
