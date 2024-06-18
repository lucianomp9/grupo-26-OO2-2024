package com.unla.grupo26.services.auth;


import com.unla.grupo26.dto.SignUpRequest;
import com.unla.grupo26.dto.UserDto;
import com.unla.grupo26.entities.User;
import com.unla.grupo26.enums.UserRole;
import com.unla.grupo26.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository repository;

    public AuthServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void createAdminAccount(){
        User adminAccount = repository.findByRole(UserRole.ADMIN);
        if(adminAccount==null){
            User user = new User();
            user.setName("admin");
            user.setEmail("admin@test.com");
            user.setActivo(true);
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            user.setRole(UserRole.ADMIN);
            repository.save(user);
        }
    }

    @Override
    public UserDto createUser(SignUpRequest signUpRequest) {
        User user = new User();
        user.setName(signUpRequest.getName());
        user.setLastName(signUpRequest.getLastName());
        user.setDni(signUpRequest.getDni());
        user.setUser(signUpRequest.getUser());
        user.setEmail(signUpRequest.getEmail());
        user.setActivo(true);
        user.setRole(UserRole.CUSTOMER);
        user.setPassword(new BCryptPasswordEncoder().encode(signUpRequest.getPassword()));
        User created = repository.save(user);
        UserDto dto =new UserDto();
        dto.setName(created.getName());
        dto.setLastName(created.getLastName());
        dto.setDni(created.getDni());
        dto.setEmail(created.getEmail());
        dto.setUser(created.getUser());
        dto.setRole(created.getRole());
        return dto;
    }
}
