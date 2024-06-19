package com.unla.grupo26.services.customer;

import com.unla.grupo26.dto.ProductDto;
import com.unla.grupo26.dto.SaleDto;
import io.jsonwebtoken.io.IOException;

import java.util.List;

public interface ICustomerService {

    public List<ProductDto> getAllProducts();

    public SaleDto generateSale(SaleDto saleDto) throws IOException;
    }
