package com.hpe.training.programs;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.hpe.training.cfg.AppConfig5;

public class P03_TestingCyclicDependencies {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext ctx =
				new AnnotationConfigApplicationContext(AppConfig5.class);
		
		System.out.println("Spring container initialized!");
		
		ctx.close();
		
	}
}
