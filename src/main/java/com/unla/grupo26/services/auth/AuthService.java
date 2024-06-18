package com.unla.grupo26.services.auth;


import com.unla.grupo26.dto.SignUpRequest;
import com.unla.grupo26.dto.UserDto;

public interface AuthService {

    UserDto createUser(SignUpRequest signUpRequest);
}
