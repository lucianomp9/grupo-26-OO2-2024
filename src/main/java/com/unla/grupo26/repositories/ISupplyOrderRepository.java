package com.unla.grupo26.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.grupo26.entities.SupplyOrder;

@Repository
public interface ISupplyOrderRepository extends JpaRepository<SupplyOrder, Long> {

}
