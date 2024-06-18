package com.unla.grupo26.dto;

import lombok.Data;

@Data
public class SignUpRequest {
    private String name;
    private String lastName;
    private String password;
    private String user;
    private String email;
    private int dni;
}
