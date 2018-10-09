package com.hpe.training.programs;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hpe.training.entity.Employee;
import com.hpe.training.entity.Laptop;
import com.hpe.training.utils.HibernateUtil;

public class P09_AddEmployeesAndLaptops {
	
	public static void main(String[] args) {
		SessionFactory factory = null;
		Session session = null;
		Transaction tx = null;
		
		try {
			factory = HibernateUtil.getSessionFactory();
			session = factory.openSession();
			tx = session.beginTransaction();
			
			Laptop lt1 = new Laptop("ASD123", "Acer", "Travelmate");
			Laptop lt2 = new Laptop("PQR222", "Lenovo", "Z560");
			Laptop lt3 = new Laptop("C0R2J2", "Apple", "Macbook Pro");
			
			Employee e1 = new Employee("John", "Admin");
			Employee e2 = new Employee("Miller", "Accounting");
			Employee e3 = new Employee("Smith", "Production");
			
			// from laptop to employee
			lt1.setOwnedBy(e1);
			// from employee to laptop
			e1.setLaptop(lt1);
			
			// using a helper function
			lt2.setOwner(e2);
			
			session.persist(lt1); // persists e1 as well
			session.persist(lt2); // persists e2 as well
			
			// no associated objects
			session.persist(lt3);
			session.persist(e3);
			
			System.out.println("Data saved successfully!");
			
			
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
