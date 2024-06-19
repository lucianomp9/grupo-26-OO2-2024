package com.unla.grupo26.dto;


import com.unla.grupo26.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {

    private Long id;
    private String name;
    private String lastName;
    private String user;
    private String password;
    private String email;

    private int dni;
    private UserRole userRole;
}
