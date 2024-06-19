package com.unla.grupo26.dto;


import lombok.Data;

import java.time.LocalDate;

@Data
public class BatchDto {

    private long batchId;
    private long productId;
    private long storageId;
    private LocalDate receptionDate;
    private int quantityReceived;
    private float batchPrice;
    private String supplier;

    public int getQuantityReceived() {
		return quantityReceived;
	}
    
}
