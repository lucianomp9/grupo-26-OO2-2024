package com.unla.grupo26.services.auth.jwt;


import com.unla.grupo26.repositories.IUserSQLRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final IUserSQLRepository repository;


    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return repository.findFirstByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }
}