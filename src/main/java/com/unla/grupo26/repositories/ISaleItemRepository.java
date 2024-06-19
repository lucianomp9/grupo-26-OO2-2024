package com.unla.grupo26.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.grupo26.entities.SaleItem;

@Repository
public interface ISaleItemRepository extends JpaRepository<SaleItem, Long> {

}
