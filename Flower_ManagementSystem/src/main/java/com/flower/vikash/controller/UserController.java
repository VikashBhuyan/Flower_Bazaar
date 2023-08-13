package com.flower.vikash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flower.vikash.dto.ResponseDto;
import com.flower.vikash.dto.SignInDto;
import com.flower.vikash.dto.SignInResponseDto;
import com.flower.vikash.dto.SignUpDto;
import com.flower.vikash.services.UserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("user")
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping("/signUp")
	public ResponseEntity<ResponseDto> signUpHandler(@RequestBody SignUpDto signupDto){
		ResponseDto response =  userService.registerUser(signupDto);
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	@PostMapping("/signIn")
    public ResponseEntity<SignInResponseDto> Signup(@RequestBody SignInDto signInDto){
		SignInResponseDto response =  userService.signIn(signInDto);
		return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }
	
}
