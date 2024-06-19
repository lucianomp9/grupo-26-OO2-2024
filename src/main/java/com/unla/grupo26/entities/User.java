package com.unla.grupo26.entities;


import com.unla.grupo26.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
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

   private UserRole role;

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return null;
   }

   @Override
   public String getUsername() {
      return null;
   }

   @Override
   public boolean isAccountNonExpired() {
      return false;
   }

   @Override
   public boolean isAccountNonLocked() {
      return false;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return false;
   }

   @Override
   public boolean isEnabled() {
      return false;
   }
}
