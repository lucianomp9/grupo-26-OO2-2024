package com.unla.grupo26.dto;


import com.unla.grupo26.entities.Batch;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class StorageDto {

    private Set<Batch> batches = new HashSet<>();
}
