package com.unla.grupo26.entities;


import com.unla.grupo26.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class User implements UserDetails {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String name;
   private String lastName;
   private String user;
   private String password;
   private String email;
   private int dni;

   @CreationTimestamp
   private LocalDateTime fechaAlta;
   private boolean activo;
   @UpdateTimestamp
   private LocalDateTime fechaActualizacion;

   @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
   private Set<Sale> sales = new HashSet<>();

   private UserRole userRole;

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return List.of(new SimpleGrantedAuthority(userRole.name()));
   }

   @Override
   public String getUsername() {
      return email;
   }



   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public boolean isEnabled() {
      return true;
   }
}