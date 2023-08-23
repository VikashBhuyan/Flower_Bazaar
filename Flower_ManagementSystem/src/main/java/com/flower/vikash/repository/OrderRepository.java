package com.flower.vikash.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flower.vikash.model.Orders;

public interface OrderRepository extends JpaRepository<Orders, Integer> {

}
