package com.unla.grupo26.mappers;

import com.unla.grupo26.dto.BatchDto;
import com.unla.grupo26.dto.ProductDto;
import com.unla.grupo26.entities.Batch;
import com.unla.grupo26.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BatchMapper {
        BatchDto batchToBatchDTO(Batch batch);
        Batch BatchDTOToBatch(BatchDto batchDto);
}
