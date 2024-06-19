package com.unla.grupo26.dto;

import lombok.Data;

@Data
public class SupplyOrderDto {
	
	private String user;
	private int quantityOrdered;
	private String supplier;
	private Long productId;

}
