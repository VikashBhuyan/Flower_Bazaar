package com.flower.vikash.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddToCartDto {
	private Integer id;
    private Integer flowerId;
    private Integer quantity;
}
