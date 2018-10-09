package com.hpe.training.programs;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hpe.training.entity.Category;
import com.hpe.training.entity.Product;
import com.hpe.training.utils.HibernateUtil;

public class P05_GetOneCategory {
	public static void main(String[] args) {
		SessionFactory factory = null;
		Session session = null;
		
		try {
			factory = HibernateUtil.getSessionFactory();
			session = factory.openSession();
			
			Category c1 = (Category) session.get(Category.class, 2);
			System.out.println("Category is : " + c1);
			System.out.println("Products in this category are: ");
			for (Product p : c1.getProducts()) {
				System.out.println(p.getDescription() 
						+ " --> Rs." + p.getUnitPrice());
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
	}
}
