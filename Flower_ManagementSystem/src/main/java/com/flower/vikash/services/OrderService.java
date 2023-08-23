package com.flower.vikash.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flower.vikash.dto.CartDto;
import com.flower.vikash.dto.CartItemDto;
import com.flower.vikash.dto.ResponseDto;
import com.flower.vikash.enums.ResponseStatus;
import com.flower.vikash.model.OrderItem;
import com.flower.vikash.model.Orders;
import com.flower.vikash.model.User;
import com.flower.vikash.repository.OrderRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderService {

	@Autowired
	private CartService cartService;
	
	@Autowired OrderRepository orderRepository;
	@Transactional	
	public ResponseDto placeOrder(User user) {
        CartDto cartDto = cartService.listCartItems(user);
//        System.out.print(cartDto);
        List<CartItemDto> cartItemDtoList = cartDto.getCartItems();
        
        Orders newOrder = new Orders();
        newOrder.setOrderDate(new Date());
        newOrder.setUser(user);
        newOrder.setTotalCost(cartDto.getTotalCost());
        
        // Assuming you have an OrderItem class and repository
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItemDto cartItemDto : cartItemDtoList) {
            OrderItem orderItem = new OrderItem();
            orderItem.setCreatedDate(new Date());
            orderItem.setPrice(cartItemDto.getFlower().getPrice());
            orderItem.setFlower(cartItemDto.getFlower());
            orderItem.setQuantity(cartItemDto.getQuantity());
            orderItem.setOrder(newOrder);
            orderItems.add(orderItem);
        }
        
        newOrder.setOrderItems(orderItems);
        Orders savedOrder = orderRepository.save(newOrder);
        
//
        cartService.deleteUserCartItems(user);
        System.out.println("This is prockled");
//        
        return new ResponseDto("Order added SuccessFully", ResponseStatus.success.toString());
//        return cartDto;
    }
	
}
