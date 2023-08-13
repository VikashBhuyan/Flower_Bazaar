package com.flower.vikash.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flower.vikash.exception.AuthenticationException;
import com.flower.vikash.model.AuthenticationToken;
import com.flower.vikash.model.User;
import com.flower.vikash.repository.AuthTokenRepository;

@Service
public class AuthenticationService {

	@Autowired
	AuthTokenRepository authRepository;
	
	public AuthenticationToken getToken(User user) {
        return authRepository.findTokenByUser(user);
    }

	public User getUser(String token) {
        AuthenticationToken authenticationToken = authRepository.findTokenByToken(token);
        if (authenticationToken!=null) {
            if (authenticationToken.getUser()!=null) {
                return authenticationToken.getUser();
            }
        }
        return null;
    }
	
	public void authenticate(String token) {
		// TODO Auto-generated method stub
		if(token==null) throw new AuthenticationException("No Token present");
		
		if(this.getUser(token)==null) throw new AuthenticationException("Authentication token not valid");
	}
}
