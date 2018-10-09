package com.hpe.training.programs;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hpe.training.entity.Employee;
import com.hpe.training.entity.Skill;
import com.hpe.training.utils.HibernateUtil;

public class P10_AddEmployeeSkills {
	public static void main(String[] args) {
		SessionFactory factory = null;
		Session session = null;
		Transaction tx = null;
		
		try {
			factory = HibernateUtil.getSessionFactory();
			session = factory.openSession();
			tx = session.beginTransaction();
			
			// transient
			Skill s1 = new Skill("Core Java");
			Skill s2 = new Skill("Python");
			Skill s3 = new Skill("Hibernate");
			Skill s4 = new Skill("Spring");
			
			// transient
			Employee e1 = new Employee("Robert", "Production", 25000.0);
			
			// persistent
			Employee e2 = (Employee) session.get(Employee.class, 2);
			Employee e3 = (Employee) session.get(Employee.class, 3);
			
			s1.addEmployee(e1);
			s1.addEmployee(e2);
			
			s2.addEmployee(e1);
			s2.addEmployee(e3);
			
			e1.addSkill(s3);
			e1.addSkill(s4);
			
			session.persist(s1);
			session.persist(s2);
			session.persist(s3);
			session.persist(s4);
			
			tx.commit();
			System.out.println("Done!");
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
			factory.close();
		}
	}
}
