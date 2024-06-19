package com.unla.grupo26.controllers;

import com.unla.grupo26.dto.BatchDto;
import com.unla.grupo26.dto.ProductDto;
import com.unla.grupo26.dto.StorageDto;
import com.unla.grupo26.entities.Storage;
import com.unla.grupo26.services.admin.AdminServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.unla.grupo26.dto.SupplyOrderDto;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminServiceImpl adminService;

    public AdminController(AdminServiceImpl adminService){
        this.adminService = adminService;
    }


    //Products operations

    @GetMapping("/product")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        return new ResponseEntity<>(adminService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/storage")
    public ResponseEntity<List<StorageDto>> getAllStorages(){
        return new ResponseEntity<>(adminService.getAllStorages(), HttpStatus.OK);
    }

    @GetMapping("/batch")
    public ResponseEntity<List<BatchDto>> getAllBatches(){
        return new ResponseEntity<>(adminService.getAllBatches(), HttpStatus.OK);
    }


    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) throws IOException {
        return new ResponseEntity<>(adminService.getProduct(id), HttpStatus.OK);
    }


    @PostMapping("/product")
    public ResponseEntity<?> createProduct(@ModelAttribute ProductDto dto) throws IOException {
        ProductDto productDto = adminService.createProduct(dto);
        if(productDto==null)return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
        return ResponseEntity.status(HttpStatus.CREATED).body(productDto);
    }

    @PostMapping("/storage")
    public ResponseEntity<?> createStorage(@ModelAttribute StorageDto storageDto) throws IOException{
        StorageDto savedStorageDto = adminService.createStorage(storageDto);
        if(savedStorageDto==null)return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStorageDto);
    }


    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) throws IOException {
        return new ResponseEntity<>(adminService.deleteProduct(id), HttpStatus.OK);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<?> editProduct(@PathVariable Long id, @ModelAttribute ProductDto productDto) throws IOException {
        return new ResponseEntity<>(adminService.editProduct(id, productDto), HttpStatus.OK);
    }

    @PostMapping("/batch")
    public ResponseEntity<?> addBatchToStorage(@ModelAttribute BatchDto batchDto) throws IOException {
        try {
            BatchDto addedBatch = adminService.addBatchToStorage(batchDto);
            if (addedBatch == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add batch");
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(addedBatch);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing the request");
        }

    }
    
    @PostMapping("/supply")
    public ResponseEntity<?> generateSupplyOrder(@ModelAttribute SupplyOrderDto supplyOrderDto) throws IOException {
        SupplyOrderDto generatedSupplyOrder = adminService.createSupplyOrder(supplyOrderDto);
		if (generatedSupplyOrder == null) {
		    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to generate supply order");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(generatedSupplyOrder);
    }
}
