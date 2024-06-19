package com.unla.grupo26.services.customer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.unla.grupo26.dto.ProductDto;
import com.unla.grupo26.dto.StorageDto;
import com.unla.grupo26.entities.*;
import com.unla.grupo26.mappers.ProductMapper;
import com.unla.grupo26.repositories.IUserSQLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unla.grupo26.dto.SaleDto;
import com.unla.grupo26.dto.SaleItemDto;
import com.unla.grupo26.mappers.SaleMapper;
import com.unla.grupo26.repositories.IProductRepository;
import com.unla.grupo26.repositories.ISaleRepository;
import com.unla.grupo26.repositories.IStockRepository;

import io.jsonwebtoken.io.IOException;

@Service
public class CustomerServiceImpl implements ICustomerService{
	
    // Aca van los casos de uso que el cliente puede realizar
	private final ISaleRepository saleRepository; 
    private final IProductRepository productRepository;
    private final IStockRepository stockRepository;
    private final IUserSQLRepository userRepository;
    
    @Autowired
    private SaleMapper saleMapper;

    @Autowired
    ProductMapper productMapper;

    public CustomerServiceImpl(ISaleRepository saleRepository, IProductRepository productRepository, IStockRepository stockRepository, IUserSQLRepository userRepository) {
        this.saleRepository = saleRepository;
        this.productRepository = productRepository;
        this.stockRepository = stockRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<ProductDto> getAllProducts(){
        List<Product> products = productRepository.findAll();

        return productMapper.productListToProductDTOList(products);
    }

    public SaleDto generateSale(SaleDto saleDto) throws IOException {
    	// Obtener el usuario de la base de datos
        User user = userRepository.findById(saleDto.getUserId())
                                  .orElseThrow(() -> new IOException("User not found with ID: " + saleDto.getUserId()));

    	
    	
        //Crear una nueva venta (Sale entity)
        Sale sale = new Sale(LocalDate.now(), user); 

     
        //Crear un nuevo SaleItem
        SaleItemDto saleItemDto = new SaleItemDto(saleDto.getProductId(), saleDto.getQuantity());
        Product product = productRepository.findById(saleItemDto.getProductId())
                .orElseThrow(() -> new IOException("Product not found with ID: " + saleItemDto.getProductId()));

        SaleItem saleItem = new SaleItem();
        saleItem.setSale(sale);
        saleItem.setProduct(product);
        saleItem.setQuantity(saleItemDto.getQuantity());
        saleItem.setPrice(saleItemDto.getQuantity() * product.getPrice());
        //Guardar la venta y el SaleItem en la base de datos
        sale.setSaleItem(saleItem);
        Sale savedSale = saleRepository.save(sale);

        //Actualizar el stock del producto
        Stock stock = stockRepository.findByProduct(product);
        if (stock == null || stock.getAvailableQuantity() < saleItemDto.getQuantity()) {
            throw new IOException("Insufficient stock for product: " + product.getName());
        }

        stock.setAvailableQuantity(stock.getAvailableQuantity() - saleItemDto.getQuantity());
        stockRepository.save(stock);
        

        // Devolver el SaleDto de la venta generada
        return saleMapper.saleToSaleDto(savedSale); 
    }

    public List<SaleDto> getAllSales() throws IOException{
        List<Sale> sales = saleRepository.findAll();
        List<SaleDto> saleDtos = new ArrayList<>();
        for(Sale s : sales){
            SaleDto saleDto = new SaleDto();
            saleDto.setSaleDate(s.getSaleDate());
            saleDto.setProductId(s.getSaleItem().getProduct().getProduct_id());
            saleDto.setQuantity(s.getSaleItem().getQuantity());

            saleDtos.add(saleDto);
        }
        return saleDtos;
    }

    public List<SaleDto> getAllSalesByUserId(long userId) throws IOException{
        List<Sale> sales = saleRepository.findByUser_Id(userId);
        List<SaleDto> saleDtos = new ArrayList<>();

        for(Sale s : sales){
            SaleDto saleDto = new SaleDto();
            saleDto.setSaleDate(s.getSaleDate());
            saleDto.setProductId(s.getSaleItem().getProduct().getProduct_id());
            saleDto.setQuantity(s.getSaleItem().getQuantity());

            saleDtos.add(saleDto);
        }
        return saleDtos;
    }


}
