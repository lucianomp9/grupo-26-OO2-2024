package com.unla.grupo26.dto;


import lombok.Data;

import java.time.LocalDate;

@Data
public class BatchDto {

	private LocalDate receptionDate;
    private int quantityReceived;
    private float batchPrice;
    private String supplier;
    private long storageId;
    private String productCode;
	
    public int getQuantityReceived() {
		return quantityReceived;
	}
    
}
