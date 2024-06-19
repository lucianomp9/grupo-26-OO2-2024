package com.unla.grupo26.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.unla.grupo26.dto.SupplyOrderDto;
import com.unla.grupo26.entities.SupplyOrder;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SupplyOrderMapper {
	SupplyOrderDto supplyOrderToSupplyOrderDTO(SupplyOrder supplyOrder);
    SupplyOrder supplyOrderDTOToSupplyOrder(SupplyOrderDto supplyOrderDto);

}
