package com.hpe.training.programs;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.hpe.training.cfg.AppConfig6;
import com.hpe.training.dao.DaoException;
import com.hpe.training.dao.ProductDao;
import com.hpe.training.entity.Product;
import com.hpe.training.service.ProductManager;

public class P06_PropagationRulesExample {

	public static void main(String[] args) {
		// Non-transactional context

		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig6.class);

		ProductDao dao = ctx.getBean("htDao", ProductDao.class);
		ProductManager mgr = ctx.getBean(ProductManager.class);
		System.out.println("mgr is an instanceof : " + mgr.getClass());

		Integer[] ids = { 1, 2, 3 };
		Double[] incs = { 1.0, -4444.0, 1.0 };

		printProductPrices(dao, ids);
		try {
			mgr.increaseProductPrices(ids, incs);
			System.out.println("Prices updated!");
		} catch (DaoException e) {
			System.out.println(e.getMessage());
		}
		printProductPrices(dao, ids);

		ctx.close();
	}

	private static void printProductPrices(ProductDao dao, Integer[] ids) {
		try {
			for (int i = 0; i < ids.length; i++) {
				Integer id = ids[i];
				Product p = dao.getProductById(id);
				System.out.println(p.getName() + " --> Rs." + p.getUnitPrice());
			}
			System.out.println("--------------------------");
		} catch (DaoException e) {
		}
	}
}
