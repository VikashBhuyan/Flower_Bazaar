package com.flower.vikash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flower.vikash.dto.AddToCartDto;
import com.flower.vikash.dto.CartDto;
import com.flower.vikash.dto.ResponseDto;
import com.flower.vikash.model.Flower;
import com.flower.vikash.model.User;
import com.flower.vikash.services.AuthenticationService;
import com.flower.vikash.services.CartService;
import com.flower.vikash.services.FlowerService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private FlowerService flowerService;
	
	@Autowired
    private AuthenticationService authenticationService;
	
	@Autowired
	private CartService cartService;
	
	@PostMapping("/add")
    public ResponseEntity<ResponseDto> addToCart(@RequestBody AddToCartDto addToCartDto,
                                                 @RequestParam("token") String token) {
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        Flower flower = flowerService.getFlowerByFlowerId(addToCartDto.getFlowerId());
        System.out.println("Flower to add"+  flower.getName());
        ResponseDto response = cartService.addToCart(addToCartDto, flower, user);
        return new ResponseEntity<>(response,HttpStatus.CREATED);

    }
	
	@GetMapping("/getCartItems")
    public ResponseEntity<CartDto> getCartItems(@RequestParam("token") String token){
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        CartDto cartDto = cartService.listCartItems(user);
//        System.out.println(cartDto);
        return new ResponseEntity<CartDto>(cartDto,HttpStatus.OK);
    }
	
	@PutMapping("/update/{cartItemId}")
    public ResponseEntity<ResponseDto> updateCartItem(@PathVariable("cartItemId") Integer id, @RequestBody AddToCartDto cartDto,
                                                      @RequestParam("token") String token){
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        Flower flower = flowerService.getFlowerByFlowerId(cartDto.getFlowerId());
        ResponseDto response =  cartService.updateCartItem(cartDto, user,flower);
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<ResponseDto> deleteCartItem(@PathVariable("cartItemId") int itemID,@RequestParam("token") String token){
        authenticationService.authenticate(token);
        int userId = authenticationService.getUser(token).getId();
        ResponseDto response = cartService.deleteCartItem(itemID, userId);
        return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
    }
}
