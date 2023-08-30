package com.flower.vikash.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlowerDTO {

	private String name;
	
	private  Double price;
	
	private String description;
	
	private String imageUrl;
	
//	private String imageUrl;
	private String imageUrl2;
	private String imageUrl3;
	private String flowerRating;
	private String flowerDetails;
	private String careInformation;
	
	private Integer quantity;
}
