package com.flower.vikash.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	private String imageUrl2;
	private String imageUrl3;
	private String flowerRating;
	private String flowerDetails;
	private String careInformation;
	
	private Integer quantity;
	
	@JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "flower")
    private List<Cart> carts;

	public Flower(String name, Double price, String description, String imageUrl, String imageUrl2, String imageUrl3,
		String flowerRating, String flowerDetails, String careInformation,
			Integer quantity) {
		super();
		this.name = name;
		this.price = price;
		this.description = description;
		this.imageUrl = imageUrl;
		this.imageUrl2 = imageUrl2;
		this.imageUrl3 = imageUrl3;
		this.flowerRating = flowerRating;
		this.flowerDetails = flowerDetails;
		this.careInformation = careInformation;
		this.quantity = quantity;
	}

	
	

	
}
