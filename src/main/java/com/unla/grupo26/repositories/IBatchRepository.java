package com.unla.grupo26.repositories;

import com.unla.grupo26.entities.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IBatchRepository extends JpaRepository<Batch, Long> {
}