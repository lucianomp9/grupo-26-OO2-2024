package com.unla.grupo26.repositories;

import com.unla.grupo26.entities.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IStorageRepository extends JpaRepository<Storage, Long> {

}
