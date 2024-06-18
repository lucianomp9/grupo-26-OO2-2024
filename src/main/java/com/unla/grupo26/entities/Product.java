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
	private String productDescription;

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

	public Product(String name, String productDescription, float price, float cost, String code) {
		super();
		this.name = name;
		this.productDescription = productDescription;
		this.price = price;
		this.cost = cost;
		this.code = code;
	}
}
