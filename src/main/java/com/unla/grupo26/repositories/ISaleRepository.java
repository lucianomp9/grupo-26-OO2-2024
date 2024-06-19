package com.unla.grupo26.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.grupo26.entities.Sale;

@Repository
public interface ISaleRepository extends JpaRepository<Sale, Long> {

    
}
