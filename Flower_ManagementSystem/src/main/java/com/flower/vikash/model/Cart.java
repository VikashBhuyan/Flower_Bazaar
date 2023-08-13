package com.flower.vikash.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
	
	private Double cartTotal;
	
	@JsonIgnore
	@OneToOne(mappedBy = "cart",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private User user;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "cartId")
	private List<Flower> flowerList ;
}
