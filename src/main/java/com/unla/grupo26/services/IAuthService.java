package com.unla.grupo26.services;


import com.unla.grupo26.dto.SignUpRequest;
import com.unla.grupo26.dto.UserDto;

public interface IAuthService {
    UserDto createUser(SignUpRequest singupRequest);
}
