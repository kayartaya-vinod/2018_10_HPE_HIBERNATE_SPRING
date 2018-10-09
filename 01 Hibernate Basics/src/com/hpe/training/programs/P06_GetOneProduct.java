package com.hpe.training.programs;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hpe.training.entity.Product;
import com.hpe.training.utils.HibernateUtil;

public class P06_GetOneProduct {
	public static void main(String[] args) {
		SessionFactory factory = null;
		Session session = null;
		
		try {
			factory = HibernateUtil.getSessionFactory();
			session = factory.openSession();
			
			Product p1 = (Product) session.get(Product.class, 1);
			session.close();
			
			// even after session is closed, we are able to access
			// the associated category and brand from a product.
			
			System.out.println("Name     = " + p1.getName());
			System.out.println("Price    = " + p1.getUnitPrice());
			System.out.println("Category = " + p1.getCategory().getName());
			System.out.println("Brand    = " + p1.getBrand().getName());
			
			
			
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			factory.close();
		}
	}
}
