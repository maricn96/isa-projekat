package com.ftn.isa.projekat.purchases.purchasesCore.converter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.purchases.purchasesApi.dto.ShoppingCartDTO;
import com.ftn.isa.projekat.purchases.purchasesCore.shoppingCart.model.ShoppingCart;
import com.ftn.isa.projekat.purchases.purchasesCore.shoppingCart.repository.ShoppingCartRepository;

@Component
public class DTOShoppingCartConverter {
	
	@Autowired
	private ShoppingCartRepository cartRepository;
	
	
	public ShoppingCartDTO convertToDTO (ShoppingCart bean) {
		
		ShoppingCartDTO dto = new ShoppingCartDTO();
		
		dto.setCarReservationId(bean.getCarReservationId());
		dto.setRoomReservationId(bean.getRoomReservationId());
		dto.setUslugaReservationId(bean.getUslugaReservationId());
		dto.setCenovnikReservationId(bean.getCenovnikReservationId());
		dto.setId(bean.getId());
		dto.setUserId(bean.getUserId());
		dto.setPrice(bean.getPrice());
		dto.setBonusPoints(bean.getBonusPoints());
		
		return dto;
		
	}
	
	public ShoppingCart convertFromDTO (ShoppingCartDTO dto) {
		
		Optional<ShoppingCart> cart = cartRepository.findById(dto.getId());
		
		if(cart.isPresent()) {
			
			return cart.get();
			
		}
		
		
		ShoppingCart bean = new ShoppingCart();
		
		bean.setCarReservationId(dto.getCarReservationId());
		bean.setRoomReservationId(dto.getRoomReservationId());
		bean.setUslugaReservationId(dto.getUslugaReservationId());
		bean.setCenovnikReservationId(dto.getCenovnikReservationId());
		bean.setId(dto.getId());
		bean.setUserId(dto.getUserId());
		bean.setPrice(dto.getPrice());
		bean.setBonusPoints(dto.getBonusPoints());
		
		return bean;
	}
	
}

