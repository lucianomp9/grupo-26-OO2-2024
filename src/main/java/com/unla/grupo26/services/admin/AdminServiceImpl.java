package com.unla.grupo26.services.admin;

import com.unla.grupo26.dto.BatchDto;
import com.unla.grupo26.dto.ProductDto;
import com.unla.grupo26.dto.StorageDto;
import com.unla.grupo26.entities.Batch;
import com.unla.grupo26.entities.Product;
import com.unla.grupo26.entities.Stock;
import com.unla.grupo26.entities.Storage;
import com.unla.grupo26.mappers.BatchMapper;
import com.unla.grupo26.mappers.ProductMapper;
import com.unla.grupo26.mappers.StorageMapper;
import com.unla.grupo26.repositories.IBatchRepository;
import com.unla.grupo26.repositories.IProductRepository;
import com.unla.grupo26.repositories.IStockRepository;
import com.unla.grupo26.repositories.IStorageRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements IAdminService{

    //Aca van los casos de uso que el Administrador puede realizar

    private final IProductRepository productRepository;
    private final ProductMapper productMapper;
    private final IStorageRepository storageRepository;
    private final BatchMapper batchMapper;
    private final StorageMapper storageMapper;
    private final IBatchRepository batchRepository;
    private final IStockRepository stockRepository;

    public AdminServiceImpl(IProductRepository productRepository, ProductMapper productMapper, IStorageRepository storageRepository, BatchMapper batchMapper,
                            StorageMapper storageMapper, IBatchRepository batchRepository,IStockRepository stockRepository){
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.storageRepository = storageRepository;
        this.batchMapper = batchMapper;
        this.storageMapper = storageMapper;
        this.batchRepository = batchRepository;
        this.stockRepository = stockRepository;
    }

    //Products Operations

    @Override
    public List<ProductDto> getAllProducts(){
        List<Product> products = productRepository.findAll();

        return productMapper.productListToProductDTOList(products);
    }

    @Override
    public List<StorageDto> getAllStorages(){
        List<Storage> storages = storageRepository.findAll();
        List<StorageDto> storageDtos = new ArrayList<>();
        for(Storage s : storages){
            StorageDto storageDto = new StorageDto();
            storageDto.setId(s.getStorage_id());
            storageDto.setLocation(s.getLocation());

            storageDtos.add(storageDto);
        }
        return storageDtos;
    }


    @Override
    public List<BatchDto> getAllBatches(){
        List<Batch> batches = batchRepository.findAll();
        List<BatchDto> batchDtos = new ArrayList<>();
        for(Batch b : batches){
            BatchDto batchDto = new BatchDto();
            batchDto.setBatchId(b.getBatchId());
            batchDto.setProductId(b.getProduct().getProduct_id());
            batchDto.setBatchPrice(b.getBatchPrice());
            batchDto.setSupplier(b.getSupplier());
            batchDto.setReceptionDate(b.getReceptionDate());
            batchDto.setQuantityReceived(b.getQuantityReceived());
            batchDto.setStorageId(b.getStorage().getStorage_id());

            batchDtos.add(batchDto);
        }

        return batchDtos;
    }

    @Override
    public ProductDto getProduct(Long id) throws IOException {
        Optional<Product> product = productRepository.findById(id);

        if(product.isEmpty()){
            throw new IOException("Product was not found");
        }
        return productMapper.productToProductDTO(product.get());
    }

    @Override
    public ProductDto createProduct(ProductDto dto) throws IOException {
        Product product = productMapper.productDTOToProduct(dto);

        Product savedProduct = productRepository.save(product);

        return productMapper.productToProductDTO(savedProduct);
    }

    @Override
    public StorageDto createStorage(StorageDto storageDto) throws IOException {
        return storageMapper.storageToStorageDTO(storageRepository.save(storageMapper.storageDTOToStorage(storageDto)));
    }

    @Override
    public ProductDto deleteProduct(Long id) throws IOException {
        Optional<Product> productOptional = productRepository.findById(id);

        if(productOptional.isEmpty()){
            throw new IOException("Product was not found");
        }

        productRepository.delete(productOptional.get());

        return productMapper.productToProductDTO(productOptional.get());
    }

    @Override
    public ProductDto editProduct(Long id, ProductDto productDto) throws IOException {
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isEmpty()) {
            throw new IOException("Product was not found");
        }

        Product product = productOptional.get();

        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setProductDescription(productDto.getProductDescription());
        product.setCost(productDto.getCost());
        product.setCode(productDto.getCode());


        productRepository.save(product);

        // Devolver el DTO del producto actualizado
        return productMapper.productToProductDTO(product);
    }
    
    @Override
    public BatchDto addBatchToStorage(BatchDto batchDto) throws IOException {
        Optional<Product> productOptional = productRepository.findById(batchDto.getProductId());
        Optional<Storage> optionalStorage = storageRepository.findById(batchDto.getStorageId());

        if (productOptional.isEmpty()) {
            throw new IOException("Product not found with ID: " + batchDto.getProductId());
        }

        if (optionalStorage.isEmpty()) {
            throw new IOException("Storage not found with ID: " + batchDto.getStorageId());
        }

        Product product = productOptional.get();
        Storage storage = optionalStorage.get();

        // Map BatchDto to Batch entity
        Batch newBatch = batchMapper.batchDTOToBatch(batchDto);

        // Set the Product for the Batch
        newBatch.setProduct(product);
        newBatch.setStorage(storage);

        // Save the Batch
        newBatch = batchRepository.save(newBatch);

        // Update Stock
        updateStock(product, batchDto.getQuantityReceived());

        // Return added BatchDto
        return batchMapper.batchToBatchDTO(newBatch);
    }

    private void updateStock(Product product, int quantityReceived) {
        Stock stock = product.getStock();
        if (stock == null) {
            stock = new Stock(product, quantityReceived, 0); // minQuantity needed
        } else {
            stock.setAvailableQuantity(stock.getAvailableQuantity() + quantityReceived);
        }
        stockRepository.save(stock);
    }
}
