package com.flower.vikash.dto;

import com.flower.vikash.model.Cart;
import com.flower.vikash.model.Flower;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {
	private Integer id;
    private  Integer quantity;
    private  Flower flower;
    public CartItemDto(Cart cart) {
        this.setId(cart.getCartId());
        this.setQuantity(cart.getQuantity());
        this.setFlower(cart.getFlower());
    }
}
