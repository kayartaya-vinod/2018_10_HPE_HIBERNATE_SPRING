package com.hpe.training.programs;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hpe.training.entity.Brand;
import com.hpe.training.utils.HibernateUtil;

public class P02_AddNewBrand {

	public static void main(String[] args) {
		SessionFactory factory = null;
		Session session = null;
		Transaction tx = null;
		try {
			factory = HibernateUtil.getSessionFactory();
			session = factory.openSession();
			tx = session.beginTransaction();
			
			Brand b = new Brand("Adidas");
			
			// insert into brands(id, name) values(null, ?)
			session.save(b);
			
			tx.commit();
			System.out.println("New brand saved!");
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
	}

}
