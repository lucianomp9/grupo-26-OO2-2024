package com.unla.grupo26.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.grupo26.entities.Usuario;

import java.util.Optional;

@Repository
public interface IUsuarioRepositorio extends JpaRepository<Usuario, Long> {
    Usuario findByNombre(String nombre);
    public Optional<Usuario> findByUsuario(String usuario);
    public Optional<Usuario> findByDni(int dni);
}
