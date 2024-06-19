package com.unla.grupo26.controllers;

import com.unla.grupo26.dto.SaleDto;
import com.unla.grupo26.services.customer.CustomerServiceImpl;


import com.unla.grupo26.dto.ProductDto;
import com.unla.grupo26.services.customer.CustomerServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

	private final CustomerServiceImpl customerService;

	public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }


    //Products operations
    @GetMapping("/product")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        return new ResponseEntity<>(customerService.getAllProducts(), HttpStatus.OK);
    }

	@PostMapping("/sale")
    public ResponseEntity<?> generateSale(@ModelAttribute SaleDto saleDto) throws IOException {
        SaleDto generatedSale = customerService.generateSale(saleDto);
		if (generatedSale == null) {
		    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to generate sale");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(generatedSale);
    }

    @GetMapping("/sale")
    public ResponseEntity<List<SaleDto>> getAllSales(){
        return new ResponseEntity<>(customerService.getAllSales(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}/sale", method = RequestMethod.GET)
    public ResponseEntity<List<SaleDto>> getAllSalesByUserId(@PathVariable long userId){
        return new ResponseEntity<>(customerService.getAllSalesByUserId(userId), HttpStatus.OK);
    }
}
