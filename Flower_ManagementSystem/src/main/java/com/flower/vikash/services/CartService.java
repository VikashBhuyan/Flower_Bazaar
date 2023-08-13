package com.flower.vikash.services;

import org.springframework.stereotype.Service;

import com.flower.vikash.dto.AddToCartDto;
import com.flower.vikash.model.Cart;
import com.flower.vikash.model.User;

@Service
public class CartService {

	public void addToCart(AddToCartDto addToCartDto, Flower flower, User user){
        Cart cart = new Cart(product, addToCartDto.getQuantity(), user);
        cartRepository.save(cart);
    }
}
