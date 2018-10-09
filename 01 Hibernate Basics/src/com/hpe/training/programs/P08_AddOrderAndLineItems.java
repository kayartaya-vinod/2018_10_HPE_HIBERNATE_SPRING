package com.hpe.training.programs;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hpe.training.entity.Customer;
import com.hpe.training.entity.LineItem;
import com.hpe.training.entity.Order;
import com.hpe.training.entity.Product;
import com.hpe.training.utils.HibernateUtil;

public class P08_AddOrderAndLineItems {
	public static void main(String[] args) {
		SessionFactory factory = null;
		Session session = null;
		Transaction tx = null;
		
		try {
			factory = HibernateUtil.getSessionFactory("mysql");
			session = factory.openSession();
			tx = session.beginTransaction();
			
			// get a customer
			Customer cust = (Customer) session.get(Customer.class, 1);
			
			// get few products
			Product p1 = (Product) session.get(Product.class, 12);
			Product p2 = (Product) session.get(Product.class, 24);
			Product p3 = (Product) session.get(Product.class, 48);
			
			// create line items for these products 
			LineItem li1 = new LineItem(p1, 2, p1.getUnitPrice());
			LineItem li2 = new LineItem(p2, 1, p2.getUnitPrice());
			LineItem li3 = new LineItem(p3, 5, p3.getUnitPrice());
			
			// create a new order
			Order order1 = new Order();
			
			// add line items to the order
			order1.addLineItem(li1);
			order1.addLineItem(li2);
			order1.addLineItem(li3);
			
			// set the customer to the order
			order1.setCustomer(cust);

			// "persist" (not save) the order
			session.persist(order1);
			
			tx.commit();
			System.out.println("Order has been placed successfully!");
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
			factory.close();
		}
	}
}
