package com.flower.vikash.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.flower.vikash.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByEmail(String email);
	
}
