package com.hpe.training.dao;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hpe.training.entity.Product;

@Transactional(readOnly = true, rollbackFor = DaoException.class) // applies to all methods of this interface
public interface ProductDao {

	// CRUD functions
	@Transactional(readOnly = false)
	public void addProduct(Product product) throws DaoException;

	public Product getProductById(Integer id) throws DaoException;

	@Transactional(readOnly = false, propagation=Propagation.REQUIRES_NEW)
	public void updateProduct(Product product) throws DaoException;

	@Transactional(readOnly = false)
	public void deleteProduct(Integer id) throws DaoException;

	// Query functions (will add more later)

	public List<Product> getAllProducts() throws DaoException;

	public List<Product> getProductsByBrand(Integer brandId) throws DaoException;

	public List<Product> getProductsByCategory(Integer categoryId) throws DaoException;

	public List<Product> getProductsByPriceRange(Double min, Double max) throws DaoException;

	public int count() throws DaoException;

}
