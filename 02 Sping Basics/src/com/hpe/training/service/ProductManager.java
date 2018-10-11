package com.hpe.training.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hpe.training.dao.DaoException;
import com.hpe.training.dao.ProductDao;
import com.hpe.training.entity.Product;

@Service // for component scan
public class ProductManager {

	@Autowired(required = false)
	private ProductDao htDao;
	// name of this variable matches with bean name (HibernateTemplateProductDao)
	// else use @Qualifier("htDao") for name resolution

	@Transactional(rollbackFor = DaoException.class)
	public void increaseProductPrices(Integer[] ids, Double[] incs) throws DaoException {

		for (int i = 0; i < ids.length; i++) {
			Integer id = ids[i];
			Product p = htDao.getProductById(id);
			p.setUnitPrice(p.getUnitPrice() + incs[i]);
			htDao.updateProduct(p);
		}

	}
}
