package com.flower.vikash.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flower.vikash.model.Cart;
import com.flower.vikash.model.Flower;
import com.flower.vikash.model.User;

public interface CartRepository extends JpaRepository<Cart, Integer> {
	public Cart findByUserAndFlower(User user, Flower flower);

	public List<Cart> findAllByUserOrderByCreatedDateDesc(User user);

	public List<Cart> deleteByUser(User user);
}
