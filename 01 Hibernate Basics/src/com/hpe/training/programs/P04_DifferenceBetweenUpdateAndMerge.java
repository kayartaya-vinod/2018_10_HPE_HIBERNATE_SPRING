package com.hpe.training.programs;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hpe.training.entity.Brand;
import com.hpe.training.utils.HibernateUtil;

public class P04_DifferenceBetweenUpdateAndMerge {
	public static void main(String[] args) {
		SessionFactory factory = null;
		Session session = null;
		Transaction tx = null;
		
		try {
			factory = HibernateUtil.getSessionFactory();
			session = factory.openSession(); // 1st session
			
			
			// b1 is a persistent object
			Brand b1 = (Brand) session.get(Brand.class, 7);
			
			session.close();
			// session (db connection) is closed, but b1 still 
			// exists in the JVM (and not garbage collected)
			// At this time b1 is a detatched object
			
			b1.setName(b1.getName().toUpperCase());
			
			// another session
			session = factory.openSession(); // 2nd session 
			tx = session.beginTransaction();
			System.out.println("Pass-1...");
			// session.update(b1) always sends SQL UPDATE during commit
			
			// session.merge(b1) executes  SELECT, 
			// compares the data received with that of b1, 
			// and only if there is change, sends the SQL UPDATE
			session.merge(b1); 
			System.out.println("Pass-2...");
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
			factory.close();
		}
	}
}
