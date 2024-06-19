package com.unla.grupo26.services;


import com.unla.grupo26.dto.SignUpRequest;
import com.unla.grupo26.dto.UserDto;
import com.unla.grupo26.entities.User;
import com.unla.grupo26.enums.UserRole;
import com.unla.grupo26.repositories.IUserSQLRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements IAuthService{

    private final IUserSQLRepository userSQLRepository;

    public AuthServiceImpl(IUserSQLRepository userSQLRepository) {
        this.userSQLRepository = userSQLRepository;
    }


    @PostConstruct
    public void createAdminAccount(){
        User adminAccount = userSQLRepository.findByUserRole(UserRole.ADMIN);
        if(adminAccount==null){
            User user = new User();
            user.setName("admin");
            user.setEmail("admin@test.com");
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            user.setUserRole(UserRole.ADMIN);
            userSQLRepository.save(user);
        }
    }

    @Override
    public UserDto createUser(SignUpRequest singupRequest) {
        User user = new User();
        user.setName(singupRequest.getName());
        user.setEmail(singupRequest.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(singupRequest.getPassword()));
        user.setUserRole(UserRole.CUSTOMER);
        User created = userSQLRepository.save(user);
        UserDto dto = new UserDto();
        dto.setId(created.getId());
        dto.setName(created.getName());
        dto.setEmail(created.getEmail());
        dto.setUserRole(created.getUserRole());
        // UserMapper.userToDto(userSQLRepository.save(UserMapper.dtoToUser(singupRequest)));
        return dto;
    }
}