package com.flower.vikash.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flower.vikash.dto.AddToCartDto;
import com.flower.vikash.dto.CartDto;
import com.flower.vikash.dto.CartItemDto;
import com.flower.vikash.dto.ResponseDto;
import com.flower.vikash.enums.ResponseStatus;
import com.flower.vikash.exception.CartException;
import com.flower.vikash.model.Cart;
import com.flower.vikash.model.Flower;
import com.flower.vikash.model.User;
import com.flower.vikash.repository.CartRepository;

@Service
public class CartService {

	@Autowired
	CartRepository cartRepository;
	
	public ResponseDto addToCart(AddToCartDto addToCartDto, Flower flower, User user){
		Cart cart = cartRepository.findByUserAndFlower(user, flower);

        if (cart == null) {
            cart = new Cart(flower, addToCartDto.getQuantity(), user);
        } else {
            cart.setQuantity(cart.getQuantity() + addToCartDto.getQuantity());
        }
        cart.setCartTotal(flower.getPrice() * addToCartDto.getQuantity());
        cartRepository.save(cart);
        return new ResponseDto("Added to cart SuccessFully",ResponseStatus.success.toString());
    }
	
	
	public CartDto listCartItems(User user) {
        List<Cart> cartList = cartRepository.findAllByUserOrderByCreatedDateDesc(user);
        List<CartItemDto> cartItems = new ArrayList<>();
        for (Cart cart:cartList){
        	CartItemDto cartItemDto = getDtoFromCart(cart);
            cartItems.add(cartItemDto);
        }
        double totalCost = 0;
        for (CartItemDto cartItemDto :cartItems){
            totalCost += (cartItemDto.getFlower().getPrice()* cartItemDto.getQuantity());
        }
        return new CartDto(cartItems,totalCost);
    }
	
	
	public static CartItemDto getDtoFromCart(Cart cart) {
        return new CartItemDto(cart);
    }


	public ResponseDto updateCartItem(AddToCartDto cartDto, User user,Flower flower){
        Cart cart = cartRepository.findByUserAndFlower(user, flower);
        if(cart==null) {
        	throw new CartException("Invalid cart id");
        }
    	cart.setQuantity(cartDto.getQuantity());
        cart.setCreatedDate(new Date());
        cartRepository.save(cart);
        return new ResponseDto("Updation to cart SuccessFull",ResponseStatus.success.toString());
    }


	public ResponseDto deleteCartItem(int id,int userId) throws CartException {
        if (!cartRepository.existsById(id))
            throw new CartException("Cart id is invalid : " + id);
        cartRepository.deleteById(id);
        return new ResponseDto("Cart item deleted",ResponseStatus.success.toString());

    }
	
	
	public void deleteCartItems(int userId) {
        cartRepository.deleteAll();
    }


    public void deleteUserCartItems(User user) {
        cartRepository.deleteByUser(user);
    }
}
