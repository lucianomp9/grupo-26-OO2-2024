package com.unla.grupo26.controllers;


import com.unla.grupo26.dto.AuthenticationRequest;
import com.unla.grupo26.dto.AuthenticationResponse;
import com.unla.grupo26.dto.SignUpRequest;
import com.unla.grupo26.dto.UserDto;
import com.unla.grupo26.entities.User;
import com.unla.grupo26.repositories.IUserSQLRepository;
import com.unla.grupo26.services.IAuthService;
import com.unla.grupo26.services.auth.jwt.UserDetailsServiceImpl;
import com.unla.grupo26.util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {

    private final IAuthService iAuthService;
    private final AuthenticationManager authenticationManager;

    private final UserDetailsServiceImpl userDetailsService;

    private final IUserSQLRepository userSQLRepository;

    private final JwtUtil jwtUtil;

    public AuthController(IAuthService iAuthService, AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService, IUserSQLRepository userSQLRepository, JwtUtil jwtUtil) {
        this.iAuthService = iAuthService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.userSQLRepository = userSQLRepository;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<?> signUpUser(@RequestBody SignUpRequest singupRequest){
        UserDto createdDto = iAuthService.createUser(singupRequest);
        if(createdDto == null){
            return new ResponseEntity<>("User not created. Come again later",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createdDto,HttpStatus.CREATED);
    }

    @PostMapping(value = "/login")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws IOException {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),authenticationRequest.getPassword()));
        }catch (BadCredentialsException e){
            throw new BadCredentialsException(("Incorrect username or password"));
        }catch (DisabledException disabledException){
            response.sendError(HttpServletResponse.SC_NOT_FOUND,"User not active");
            return null;
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());

        final String jwt = jwtUtil.generateToken(userDetails);
        Optional<User> optionalUser = userSQLRepository.findFirstByEmail(userDetails.getUsername());
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        if(optionalUser.isPresent()){
            authenticationResponse.setJwt(jwt);
            authenticationResponse.setUserRole(optionalUser.get().getUserRole());
            authenticationResponse.setUserId(optionalUser.get().getId());
        }
        return authenticationResponse;
    }
}
