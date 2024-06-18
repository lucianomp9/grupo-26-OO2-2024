package com.unla.grupo26.entities;


import com.unla.grupo26.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class User {
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
}
