package com.hpe.training.programs;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hpe.training.dao.ProductDao;

public class P01_SpringAsFactoryOfBeans {

	public static void main(String[] args) throws Exception {
		
		// a variable representing spring container
		ClassPathXmlApplicationContext ctx;
		
		// object representing the spring container
		ctx = new ClassPathXmlApplicationContext("context1.xml");
		// When the spring container is created, it will create objects
		// of all bean definitions from the XML. Whenever you need a bean
		// spring just gives you a reference of the same.
		
		ProductDao dao;
		int pc;
		
		// ask for a bean; getBean is the factory method that creates/returns
		// an object based on "name" or "type" or both
		dao = ctx.getBean("dummy-dao", ProductDao.class);
		pc = dao.count();
		System.out.println("Product count = " + pc);
		
		dao = ctx.getBean("jdbc-dao", ProductDao.class);
		pc = dao.count();
		System.out.println("Product count = " + pc);
		
		// close the spring container to avoid resource leak
		ctx.close();
		
	}
}
