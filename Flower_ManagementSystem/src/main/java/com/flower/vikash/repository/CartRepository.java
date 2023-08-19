package com.flower.vikash.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flower.vikash.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {

}
