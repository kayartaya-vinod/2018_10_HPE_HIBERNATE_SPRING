package com.hpe.training.programs;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.hpe.training.cfg.AppConfig4;
import com.hpe.training.dao.ProductDao;

public class P02_PropertyInjectionDemo {

	public static void main(String[] args) throws Exception {

		// ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("context2.xml");
		
		AnnotationConfigApplicationContext ctx = 
				new AnnotationConfigApplicationContext(AppConfig4.class);
		
		ProductDao dao = ctx.getBean("dao", ProductDao.class);
		
		int pc = dao.count();
		System.out.println("Product count = " + pc);

		ctx.close();

	}
}
