package com.unla.grupo26.configuration;


import com.unla.grupo26.services.auth.jwt.UserService;
import com.unla.grupo26.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component

public class JwtAuthenticationFilter extends OncePerRequestFilter {


    private final JwtUtil jwtUtil;
    private final UserService userService;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        final  String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String email;

        if(StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader,"Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }
        jwt = authHeader.substring(7);
        email = jwtUtil.extractUserName(jwt);

        if(StringUtils.isNotEmpty(email) && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = userService.userDetailsService().loadUserByUsername(email);
            if (jwtUtil.isTokenValid(jwt,userDetails)){
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                context.setAuthentication(authToken);
                SecurityContextHolder.setContext(context);
            }
        }
        filterChain.doFilter(request,response);

    }
}
