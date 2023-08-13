package com.flower.vikash.dto;

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
	
	private Integer quantity;
}
