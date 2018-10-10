package com.hpe.training.programs;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.hpe.training.entity.Customer;

public class P14_JPADemo {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = 
				Persistence.createEntityManagerFactory("unit1");
		
		EntityManager em = emf.createEntityManager();
		
		Customer c1 = em.find(Customer.class, 1);
		em.close();
		emf.close();
		
		System.out.println("Name = " + c1.getName());
		System.out.println("Email = " + c1.getEmail());
		System.out.println("Phone = " + c1.getContactInfo().getPhone());
		
	}
}
