package com.unla.grupo26.mappers;

import com.unla.grupo26.dto.ProductDto;
import com.unla.grupo26.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {
        ProductDto productToProductDTO(Product product);
        Product productDTOToProduct(ProductDto productDto);
        List<ProductDto> productListToProductDTOList(List<Product> productList);
}
