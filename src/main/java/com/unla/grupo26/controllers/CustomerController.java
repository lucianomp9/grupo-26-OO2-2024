package com.unla.grupo26.controllers;

import com.unla.grupo26.dto.SaleDto;
import com.unla.grupo26.services.customer.CustomerServiceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/customer")
public class CustomerController {

	private final CustomerServiceImpl customerService;

	public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }


    //Products operations
	@PostMapping("/sale")
    public ResponseEntity<?> generateSale(@RequestBody SaleDto saleDto) throws IOException {
        SaleDto generatedSale = customerService.generateSale(saleDto);
		if (generatedSale == null) {
		    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to generate sale");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(generatedSale);
    }
}
