package com.flower.vikash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flower.vikash.dto.CartDto;
import com.flower.vikash.dto.ResponseDto;
import com.flower.vikash.model.User;
import com.flower.vikash.services.AuthenticationService;
import com.flower.vikash.services.OrderService;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*")
public class OrderController {

	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private OrderService orderService;
	@PostMapping("/add")
    public ResponseEntity<ResponseDto> placeOrder(@RequestParam("token") String token) {
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        
        ResponseDto response =   orderService.placeOrder(user);
        
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
	
}
