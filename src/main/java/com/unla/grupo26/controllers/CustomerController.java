package com.unla.grupo26.controllers;

import com.unla.grupo26.dto.BatchDto;
import com.unla.grupo26.dto.ProductDto;
import com.unla.grupo26.services.admin.AdminServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final AdminServiceImpl adminService;

    public CustomerController(AdminServiceImpl adminService){
        this.adminService = adminService;
    }


    //Products operations

}
