package com.unla.grupo26.controllers;

import com.unla.grupo26.dto.BatchDto;
import com.unla.grupo26.dto.ProductDto;
import com.unla.grupo26.services.admin.AdminServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
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

    @PostMapping("/product")
    public ResponseEntity<?> createProduct(@ModelAttribute ProductDto dto) throws IOException {
        ProductDto productDto = adminService.createProduct(dto);
        if(productDto==null)return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
        return ResponseEntity.status(HttpStatus.CREATED).body(productDto);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) throws IOException {
        return new ResponseEntity<>(adminService.deleteProduct(id), HttpStatus.OK);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<?> editProduct(@PathVariable Long id, @ModelAttribute ProductDto productDto) throws IOException {
        return new ResponseEntity<>(adminService.editProduct(id, productDto), HttpStatus.OK);
    }

    @PostMapping("/batch/{id}")
    public ResponseEntity<?> addBatchToStorage(@PathVariable Long id, @ModelAttribute BatchDto batchDto) throws IOException {
        return new ResponseEntity<>(adminService.addBatchToStorage(id, batchDto), HttpStatus.OK);
    }
}