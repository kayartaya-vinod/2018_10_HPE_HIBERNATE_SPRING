package com.hpe.training.dao.impl;

import com.hpe.training.dao.DaoException;
import com.hpe.training.dao.ProductDao;

public class DummyProductDao implements ProductDao {
	
	public DummyProductDao() {
		System.out.println("DummyProductDao instantiated!");
	}

	@Override
	public int count() throws DaoException {
		return 100;
	}

}
