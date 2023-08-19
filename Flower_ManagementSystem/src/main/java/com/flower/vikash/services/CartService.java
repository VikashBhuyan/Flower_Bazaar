package com.flower.vikash.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flower.vikash.dto.AddToCartDto;
import com.flower.vikash.model.Cart;
import com.flower.vikash.model.Flower;
import com.flower.vikash.model.User;
import com.flower.vikash.repository.CartRepository;

@Service
public class CartService {

	@Autowired
	CartRepository cartRepository;
	
	public void addToCart(AddToCartDto addToCartDto, Flower flower, User user){
        Cart cart = new Cart(flower, addToCartDto.getQuantity(), user);
        cartRepository.save(cart);
    }
}
