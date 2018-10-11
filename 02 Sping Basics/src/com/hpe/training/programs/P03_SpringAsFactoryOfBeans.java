package com.hpe.training.programs;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hpe.training.dao.ProductDao;

public class P03_SpringAsFactoryOfBeans {

	public static void main(String[] args) throws Exception {

		ClassPathXmlApplicationContext ctx;
		System.out.println("pass-1");
		ctx = new ClassPathXmlApplicationContext("context1.xml");
		System.out.println("pass-2");
		ProductDao dao = ctx.getBean("dummy-dao", ProductDao.class);
		ProductDao dao2 = ctx.getBean("dummy-dao", ProductDao.class);
		
		System.out.println("dao==dao2 is: " + (dao==dao2));
		
		System.out.println("pass-3");
		int pc = dao.count();
		System.out.println("Product count = " + pc);

		ctx.close();

	}
}
