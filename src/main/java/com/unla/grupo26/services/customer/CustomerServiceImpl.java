package com.unla.grupo26.services.customer;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unla.grupo26.dto.SaleDto;
import com.unla.grupo26.dto.SaleItemDto;
import com.unla.grupo26.entities.Product;
import com.unla.grupo26.entities.Sale;
import com.unla.grupo26.entities.SaleItem;
import com.unla.grupo26.entities.Stock;
import com.unla.grupo26.entities.User;
import com.unla.grupo26.mappers.SaleMapper;
import com.unla.grupo26.repositories.IProductRepository;
import com.unla.grupo26.repositories.ISaleRepository;
import com.unla.grupo26.repositories.IStockRepository;
import com.unla.grupo26.repositories.UserRepository;

import io.jsonwebtoken.io.IOException;

@Service
public class CustomerServiceImpl {
	
    // Aca van los casos de uso que el cliente puede realizar
	private final ISaleRepository saleRepository; 
    private final IProductRepository productRepository;
    private final IStockRepository stockRepository;
    private final UserRepository userRepository;
    
    @Autowired
    private SaleMapper saleMapper;

    public CustomerServiceImpl(ISaleRepository saleRepository, IProductRepository productRepository, IStockRepository stockRepository,UserRepository userRepository) {
        this.saleRepository = saleRepository;
        this.productRepository = productRepository;
        this.stockRepository = stockRepository;
        this.userRepository = userRepository;
    }

    public SaleDto generateSale(SaleDto saleDto) throws IOException {
    	// Obtener el usuario de la base de datos
        User user = userRepository.findById(saleDto.getUserId())
                                  .orElseThrow(() -> new IOException("User not found with ID: " + saleDto.getUserId()));

    	
    	
        //Crear una nueva venta (Sale entity)
        Sale sale = new Sale(LocalDate.now(), user); 

     
        //Crear un nuevo SaleItem
        SaleItemDto saleItemDto = saleDto.getSaleItem();
        Product product = productRepository.findById(saleItemDto.getProductId())
                .orElseThrow(() -> new IOException("Product not found with ID: " + saleItemDto.getProductId()));

        SaleItem saleItem = new SaleItem();
        saleItem.setSale(sale);
        saleItem.setProduct(product);
        saleItem.setQuantity(saleItemDto.getQuantity());
        saleItem.setPrice(saleItemDto.getPrice());

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
}
