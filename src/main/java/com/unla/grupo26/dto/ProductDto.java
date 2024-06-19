package com.unla.grupo26.dto;


import lombok.Data;

@Data
public class ProductDto {

	private Long product_id;
    private String name;
	private float price;
	private String description;
    private float cost;
    private String code;


	public String getName() {
		return name;
	}
	public String getProductDescription() {
		return description;
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
