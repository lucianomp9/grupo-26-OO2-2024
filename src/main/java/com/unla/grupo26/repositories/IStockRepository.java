package com.unla.grupo26.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.unla.grupo26.entities.Product;
import com.unla.grupo26.entities.Stock;

@Repository
public interface IStockRepository extends CrudRepository<Stock, Long> {
	 Stock findByProduct(Product product);
}
