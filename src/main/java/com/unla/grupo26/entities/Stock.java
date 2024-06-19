package com.unla.grupo26.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Entity
@Setter
@Getter
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idStock;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

    @Column(nullable = false)
    private int availableQuantity;

    @Column(nullable = false)
    private int minQuantity;
    
    public Stock() {}
    
    public Stock(Product product, int availableQuantity, int minQuantity){
        this.product = product;
        this.availableQuantity = availableQuantity;
        this.minQuantity = minQuantity;
    }

	public void setAvailableQuantity(int availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	public int getAvailableQuantity() {
		return availableQuantity;
	}

	

    
    
}
