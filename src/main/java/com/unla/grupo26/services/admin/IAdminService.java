package com.unla.grupo26.services.admin;

import com.unla.grupo26.dto.BatchDto;
import com.unla.grupo26.dto.ProductDto;
import com.unla.grupo26.dto.StorageDto;
import com.unla.grupo26.dto.SupplyOrderDto;

import java.io.IOException;
import java.util.List;

public interface IAdminService {

    public List<ProductDto> getAllProducts();

    public List<BatchDto> getAllBatches();

    public List<StorageDto> getAllStorages();

    public StorageDto createStorage(StorageDto storageDto) throws IOException;

    public ProductDto createProduct(ProductDto dto) throws IOException;

    public ProductDto deleteProduct(Long id) throws IOException;

    public ProductDto editProduct(Long id, ProductDto productDto) throws IOException;

    
    public SupplyOrderDto createSupplyOrder(SupplyOrderDto dto) throws IOException;

    public BatchDto addBatchToStorage(BatchDto batchDto) throws IOException;

    public ProductDto getProduct(Long id) throws IOException;


    }
