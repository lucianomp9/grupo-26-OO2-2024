package com.unla.grupo26.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class SaleDto {
	
	private LocalDate saleDate;
    private Long userId; 
    private SaleItemDto saleItem;


	public Long getUserId() {
		return userId;
	}


	public SaleItemDto getSaleItem() {
		return saleItem;
	}

	
    
    
    
}
