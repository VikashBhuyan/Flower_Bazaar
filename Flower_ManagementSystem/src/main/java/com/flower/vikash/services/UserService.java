package com.flower.vikash.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flower.vikash.dto.ResponseDto;
import com.flower.vikash.dto.SignInDto;
import com.flower.vikash.dto.SignInResponseDto;
import com.flower.vikash.dto.SignUpDto;
import com.flower.vikash.enums.ResponseStatus;
import com.flower.vikash.enums.Role;
import com.flower.vikash.exception.UserException;
import com.flower.vikash.model.AuthenticationToken;
import com.flower.vikash.model.User;
import com.flower.vikash.repository.AuthTokenRepository;
import com.flower.vikash.repository.UserRepository;
import org.mindrot.jbcrypt.*;
@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AuthTokenRepository authRepository;
	
	@Autowired
	AuthenticationService authenticationService;
	
	public ResponseDto registerUser(SignUpDto signupDto) {
		// TODO Auto-generated method stub
		
		if(userRepository.findByEmail(signupDto.getEmail())!=null) {
			throw new UserException("User already exist with this Email");
		}
		
//		encrypting password using hashing
		String encryptedPassWord = signupDto.getPassword();
		encryptedPassWord = hashPassword(encryptedPassWord);
		
		User user = new User(signupDto.getFirstName(),signupDto.getLastName(),signupDto.getEmail(),Role.user,encryptedPassWord); 
		User createdUser = userRepository.save(user);
		
		final AuthenticationToken authenticationToken = new AuthenticationToken(createdUser);
		
		authRepository.save(authenticationToken);
		
		ResponseDto response = new ResponseDto();
		response.setMessage("user created successfully");
		response.setStatus(ResponseStatus.success.toString());
		return response;
	}
	
	public  String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

	public SignInResponseDto signIn(SignInDto signInDto) {
		// TODO Auto-generated method stub
		User user = userRepository.findByEmail(signInDto.getEmail());
		if(user==null) {
			throw new UserException("User not found with given email");
		}
		if(user.getPassword().equals(signInDto.getPassword())) {
			throw new UserException("Password is incorrect");
		}
		
		AuthenticationToken token = authenticationService.getToken(user);
		if(token==null) throw new UserException("Token is missing");
		
		return new SignInResponseDto("Successfull SignIn", token.getToken());
	}

	
}
