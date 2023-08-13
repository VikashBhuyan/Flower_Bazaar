package com.flower.vikash.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Flower {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Integer flowerId;
	
	private String name;
	
	private Double price;
	
	private String description;
	
	private String imageUrl;

	private Integer quantity;
	public Flower(String name, Double price, String description, String imageUrl,Integer quantity) {
		super();
		this.name = name;
		this.price = price;
		this.description = description;
		this.imageUrl = imageUrl;
		this.quantity = quantity;
	}

	
}
