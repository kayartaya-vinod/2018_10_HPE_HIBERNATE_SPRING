package com.hpe.training.programs;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.hpe.training.cfg.AppConfig6;
import com.hpe.training.dao.DaoException;
import com.hpe.training.dao.ProductDao;
import com.hpe.training.entity.Product;

public class P05_TestingHibernateTemplateProductDao {

	public static void main(String[] args) throws Exception {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig6.class);

		ProductDao dao = ctx.getBean("htDao", ProductDao.class);
		System.out.println("dao is an instanceof " + dao.getClass());

		Product p = dao.getProductById(1);
		System.out.println("Name = " + p.getName());
		System.out.println("Price = " + p.getUnitPrice());
		
		try {
			p.setUnitPrice(p.getUnitPrice() + 1);
			dao.updateProduct(p);
			
			p = dao.getProductById(1);
			System.out.println("After update, ");
			System.out.println("Price = " + p.getUnitPrice());
			
		} catch (DaoException e) {
			System.out.println("OOPS! " + e.getMessage());
		}

		List<Product> list;

		list = dao.getProductsByPriceRange(40.0, 50.0);
		System.out.println("There are " + list.size() 
			+ " products between 40 and 50 rupees ");


		list = dao.getProductsByPriceRange(50.0, 40.0);
		System.out.println("There are " + list.size() 
			+ " products between 50 and 40 rupees ");

		
		int pc = dao.count();
		System.out.println("There are a total of " + pc + " products");
		
		ctx.close();
	}

}
