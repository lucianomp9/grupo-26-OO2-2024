package com.unla.grupo26.dto;


import lombok.Data;

@Data
public class ProductDto {

    private String name;
    private String productDescription;
    private float price;
    private float cost;
    private String code;
	public String getName() {
		return name;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public float getPrice() {
		return price;
	}
	public float getCost() {
		return cost;
	}
	public String getCode() {
		return code;
	}
    
}
