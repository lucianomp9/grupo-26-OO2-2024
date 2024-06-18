package com.unla.grupo26.services.admin;

import com.unla.grupo26.dto.BatchDto;
import com.unla.grupo26.dto.ProductDto;
import com.unla.grupo26.dto.StorageDto;
import com.unla.grupo26.entities.Batch;
import com.unla.grupo26.entities.Product;
import com.unla.grupo26.entities.Storage;
import com.unla.grupo26.mappers.BatchMapper;
import com.unla.grupo26.mappers.ProductMapper;
import com.unla.grupo26.mappers.StorageMapper;
import com.unla.grupo26.repositories.IProductoRepository;
import com.unla.grupo26.repositories.IStorageRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class AdminServiceImpl implements IAdminService{

    //Aca van los casos de uso que el Administrador puede realizar

    private final IProductoRepository productoRepository;
    private final ProductMapper productMapper;
    private final IStorageRepository storageRepository;
    private final BatchMapper batchMapper;
    private final StorageMapper storageMapper;

    public AdminServiceImpl(IProductoRepository productoRepository, ProductMapper productMapper, IStorageRepository storageRepository, BatchMapper batchMapper,
                            StorageMapper storageMapper){
        this.productoRepository = productoRepository;
        this.productMapper = productMapper;
        this.storageRepository = storageRepository;
        this.batchMapper = batchMapper;
        this.storageMapper = storageMapper;
    }

    //Products Operations
    @Override
    public ProductDto createProduct(ProductDto dto) throws IOException {
        Product product = productMapper.productDTOToProduct(dto);

        Product savedProduct = productoRepository.save(product);

        return productMapper.productToProductDTO(savedProduct);
    }

    @Override
    public ProductDto deleteProduct(Long id) throws IOException {
        Optional<Product> productOptional = productoRepository.findById(id);

        if(productOptional.isEmpty()){
            throw new IOException("Product was not found");
        }

        productoRepository.delete(productOptional.get());

        return productMapper.productToProductDTO(productOptional.get());
    }

    @Override
    public ProductDto editProduct(Long id, ProductDto productDto) throws IOException {
        Optional<Product> productOptional = productoRepository.findById(id);

        if (productOptional.isEmpty()) {
            throw new IOException("Product was not found");
        }

        Product product = productOptional.get();

        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setProductDescription(productDto.getProductDescription());
        product.setCost(productDto.getCost());


        productoRepository.save(product);

        // Devolver el DTO del producto actualizado
        return productMapper.productToProductDTO(product);
    }

    public StorageDto addBatchToStorage(Long id, BatchDto batchDto) throws IOException {
        Optional<Storage> optionalStorage = storageRepository.findById(id);

        if (optionalStorage.isEmpty()) {
            throw new IOException("Storage was not found");
        }

        Storage storage = optionalStorage.get();
        Batch batch = batchMapper.BatchDTOToBatch(batchDto);

        Optional<Product> productOptional = productoRepository.findByCode(batchDto.getProductCode());

        if (productOptional.isEmpty()) {
            throw new IOException("No product was found with that code");
        }

        batch.setProduct(productOptional.get());

        storage.getBatches().add(batch);

        storageRepository.save(storage);
        return storageMapper.storageToStorageDTO(storage);
    }
}
