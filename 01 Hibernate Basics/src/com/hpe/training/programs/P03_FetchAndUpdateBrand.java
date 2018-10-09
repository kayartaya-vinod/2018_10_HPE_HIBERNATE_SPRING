package com.hpe.training.programs;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hpe.training.entity.Brand;
import com.hpe.training.utils.HibernateUtil;

public class P03_FetchAndUpdateBrand {
	public static void main(String[] args) {
		SessionFactory factory = null;
		Session session = null;
		Transaction tx = null;
		
		try {
			factory = HibernateUtil.getSessionFactory();
			session = factory.openSession();
			tx = session.beginTransaction();
			
			Brand b = (Brand) session.get(Brand.class, 7);
			
			// changes to the persistent object will be managed
			// by the session; and such objects are marked as 'dirty'
			// and while committing, 'dirty' objects will be converted
			// into SQL UPDATE statements.
			b.setName("Reebock");
			tx.commit();
			
			System.out.println("Transaction committed!");
			
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
			factory.close();
		}
	}
}
