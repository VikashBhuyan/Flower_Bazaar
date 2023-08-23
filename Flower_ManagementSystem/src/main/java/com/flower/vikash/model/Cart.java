package com.flower.vikash.model;

import java.util.Date;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cartId;
	
	private Date createdDate;
	
	private Double cartTotal;
	
	@ManyToOne
    @JoinColumn(name = "flower_id", referencedColumnName = "flowerId")
    private Flower flower;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "user_id")
	private User user;
	
	private Integer quantity;
	
	public Cart(Flower flower, int quantity, User user){
        this.user = user;
        this.flower = flower;
        this.quantity = quantity;
        this.createdDate = new Date();
    }
}
