package com.hpe.training.springboot;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends CrudRepository<Product, Integer> {
	public Iterable<Product> findByDiscount(Double discount);
}
