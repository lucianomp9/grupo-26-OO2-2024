package com.unla.grupo26.services.admin;

import com.unla.grupo26.dto.BatchDto;
import com.unla.grupo26.dto.ProductDto;
import com.unla.grupo26.dto.StorageDto;
import com.unla.grupo26.dto.SupplyOrderDto;
import com.unla.grupo26.entities.Batch;
import com.unla.grupo26.entities.Product;
import com.unla.grupo26.entities.Stock;
import com.unla.grupo26.entities.Storage;
import com.unla.grupo26.entities.SupplyOrder;
import com.unla.grupo26.mappers.BatchMapper;
import com.unla.grupo26.mappers.ProductMapper;
import com.unla.grupo26.mappers.StorageMapper;
import com.unla.grupo26.mappers.SupplyOrderMapper;
import com.unla.grupo26.repositories.IBatchRepository;
import com.unla.grupo26.repositories.IProductRepository;
import com.unla.grupo26.repositories.IStockRepository;
import com.unla.grupo26.repositories.IStorageRepository;
import com.unla.grupo26.repositories.ISupplyOrderRepository;

import org.springframework.stereotype.Service;

import java.io.IOException;
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
    private final ISupplyOrderRepository supplyOrderRepository;
    private final SupplyOrderMapper supplyOrderMapper;

    public AdminServiceImpl(IProductRepository productRepository, ProductMapper productMapper, IStorageRepository storageRepository, BatchMapper batchMapper,
                            StorageMapper storageMapper, IBatchRepository batchRepository,IStockRepository stockRepository, ISupplyOrderRepository supplyOrderRepository, SupplyOrderMapper supplyOrderMapper){
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.storageRepository = storageRepository;
        this.batchMapper = batchMapper;
        this.storageMapper = storageMapper;
        this.batchRepository = batchRepository;
        this.stockRepository = stockRepository;
		this.supplyOrderRepository = supplyOrderRepository;
		this.supplyOrderMapper = supplyOrderMapper;
    }

    //Products Operations

    @Override
    public List<ProductDto> getAllProducts(){
        List<Product> products = productRepository.findAll();

        return productMapper.productListToProductDTOList(products);
    }

    @Override
    public ProductDto createProduct(ProductDto dto) throws IOException {
        Product product = productMapper.productDTOToProduct(dto);

        Product savedProduct = productRepository.save(product);

        return productMapper.productToProductDTO(savedProduct);
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


        productRepository.save(product);

        // Devolver el DTO del producto actualizado
        return productMapper.productToProductDTO(product);
    }
    
    @Override
    public BatchDto addBatchToStorage(Long productId, BatchDto batchDto) throws IOException {
        Optional<Product> productOptional = productRepository.findById(productId);

        if (productOptional.isEmpty()) {
            throw new IOException("Product not found with ID: " + productId);
        }

        Product product = productOptional.get();

        // Map BatchDto to Batch entity
        Batch newBatch = batchMapper.batchDTOToBatch(batchDto);

        // Set the Product for the Batch
        newBatch.setProduct(product);

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
    
    @Override
    public SupplyOrderDto createSupplyOrder(SupplyOrderDto dto) throws IOException {
        SupplyOrder supplyOrder = supplyOrderMapper.supplyOrderDTOToSupplyOrder(dto);

        SupplyOrder savedSupplyOrder = supplyOrderRepository.save(supplyOrder);

        return supplyOrderMapper.supplyOrderToSupplyOrderDTO(savedSupplyOrder);
    }
}
