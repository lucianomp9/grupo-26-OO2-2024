package com.unla.grupo26.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.grupo26.entities.Almacen;

@Repository
public interface IAlmacenRepositorio extends JpaRepository<Almacen, Long> {

}
