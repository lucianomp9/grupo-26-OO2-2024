package com.unla.grupo26.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.unla.grupo26.dto.SaleDto;
import com.unla.grupo26.entities.Sale;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SaleMapper {

    SaleDto saleToSaleDto(Sale sale);

    Sale saleDtoToSale(SaleDto saleDto);

    List<SaleDto> saleListToSaleListDTO(List<Sale> saleList);

}

