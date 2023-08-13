package com.flower.vikash.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flower.vikash.model.Flower;

public interface FlowerRepository extends JpaRepository<Flower, Integer>{
	public Flower findByName(String name);
}
