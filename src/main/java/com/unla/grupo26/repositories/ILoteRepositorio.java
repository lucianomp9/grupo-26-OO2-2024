package com.unla.grupo26.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.grupo26.entities.Lote;


@Repository
public interface ILoteRepositorio extends JpaRepository<Lote, Long> {
}