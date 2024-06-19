package com.unla.grupo26.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SaleItemDto {
	//Atributos
	private Long productId;
    private int quantity;

    // Getters y Setters

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
