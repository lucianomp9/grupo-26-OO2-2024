package com.unla.grupo26.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.grupo26.entities.Sale;

import java.util.List;

@Repository
public interface ISaleRepository extends JpaRepository<Sale, Long> {
    List<Sale> findByUser_Id(Long userId);

    
}
