package com.unla.grupo26.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SaleItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long saleItem_id;

    @OneToOne(fetch = FetchType.LAZY)
    private Sale sale;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private float price;


	public void setSale(Sale sale) {
		this.sale = sale;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setPrice(float price) {
		this.price = price;
	}


}
