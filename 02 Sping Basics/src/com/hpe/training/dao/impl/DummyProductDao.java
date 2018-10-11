package com.hpe.training.dao.impl;

import java.util.List;

import com.hpe.training.dao.DaoException;
import com.hpe.training.dao.ProductDao;
import com.hpe.training.entity.Product;

public class DummyProductDao implements ProductDao {

	public DummyProductDao() {
		System.out.println("DummyProductDao instantiated!");
	}

	@Override
	public int count() throws DaoException {
		return 100;
	}

	@Override
	public void addProduct(Product product) throws DaoException {
		throw new DaoException("Method not implemented yet!");
	}

	@Override
	public Product getProductById(Integer id) throws DaoException {
		throw new DaoException("Method not implemented yet!");
	}

	@Override
	public void updateProduct(Product product) throws DaoException {
		throw new DaoException("Method not implemented yet!");
	}

	@Override
	public void deleteProduct(Integer id) throws DaoException {
		throw new DaoException("Method not implemented yet!");
	}

	@Override
	public List<Product> getAllProducts() throws DaoException {
		throw new DaoException("Method not implemented yet!");
	}

	@Override
	public List<Product> getProductsByBrand(Integer brandId) throws DaoException {
		throw new DaoException("Method not implemented yet!");
	}

	@Override
	public List<Product> getProductsByCategory(Integer categoryId) throws DaoException {
		throw new DaoException("Method not implemented yet!");
	}

	@Override
	public List<Product> getProductsByPriceRange(Double min, Double max) throws DaoException {
		throw new DaoException("Method not implemented yet!");
	}

}
