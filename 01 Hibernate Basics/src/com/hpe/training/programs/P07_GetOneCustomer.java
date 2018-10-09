package com.hpe.training.programs;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hpe.training.entity.Customer;
import com.hpe.training.utils.HibernateUtil;

public class P07_GetOneCustomer {
	
	public static void main(String[] args) {
		SessionFactory factory = null;
		Session session = null;
		
		try {
			factory = HibernateUtil.getSessionFactory();
			session = factory.openSession();
			
			Customer c1 = (Customer) session.get(Customer.class, 1);
			System.out.println("Name    = " + c1.getName());
			System.out.println("Email   = " + c1.getEmail());
			System.out.println("City    = " + c1.getContactInfo().getCity());
			System.out.println("Phone   = " + c1.getContactInfo().getPhone());
			
			
			
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
	}

}
