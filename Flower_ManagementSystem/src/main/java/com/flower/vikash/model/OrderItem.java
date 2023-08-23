package com.flower.vikash.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class OrderItem {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	private Integer quantity;
	
	private double price;
	
	private Date createdDate;
	
	@ManyToOne
    @JsonIgnore
    @JoinColumn(name = "order_id", referencedColumnName = "orderId")
    private Orders order;
	
	@OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "flowerId")
    private Flower flower;

	public OrderItem(Integer quantity, double price,Orders order, Flower flower) {
		super();
		this.quantity = quantity;
		this.price = price;
		this.createdDate = new Date();
		this.order = order;
		this.flower = flower;
	}
	
	
}
