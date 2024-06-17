package com.unla.grupo26.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.grupo26.entities.Usuario;

@Repository
public interface IUsuarioRepositorio extends JpaRepository<Usuario, Integer> {
    Usuario findByNombre(String nombre);
}
