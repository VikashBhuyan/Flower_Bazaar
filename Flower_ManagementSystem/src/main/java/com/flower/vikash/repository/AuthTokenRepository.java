package com.flower.vikash.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flower.vikash.model.AuthenticationToken;
import com.flower.vikash.model.User;

public interface AuthTokenRepository extends JpaRepository<AuthenticationToken, Integer>{
	AuthenticationToken findTokenByUser(User user);
    AuthenticationToken findTokenByToken(String token);
}
