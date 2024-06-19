package com.unla.grupo26.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long product_id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private float price;

	@Column(nullable = false)
	private float cost;

	@Column(nullable = false)
	private String code;

	@OneToOne(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
	private Batch batch;

	@OneToOne(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
	private SupplyOrder supplyOrder;

	@OneToOne(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
	private SaleItem saleItem;

	@OneToOne(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
	private Stock stock;

	public Product(String name, String description, float price, float cost, String code) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.cost = cost;
		this.code = code;
	}

	public Stock getStock() {
		return stock;
	}
	

	public String getName() {
		return name;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setProductDescription(String description) {
		this.description = description;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}
	
	
	
}
