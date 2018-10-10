package com.hpe.training.cfg;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig2 {

	public AppConfig2() {
		System.out.println("AppConfig2 instantiated!");
	}

	@Bean
	public String bean1() {
		System.out.println("from inside of bean1()");
		System.out.println("I am instnaceof " + this.getClass());
		
		for (int i = 0; i < 5; i++) {
			System.out.println("bean2 says : " + bean2());
		}
		return "Something";
	}

	// @Bean
	public String bean2() {
		System.out.println("from inside of bean2()");
		
		return "Something else";
	}
	
	public static void main(String[] args) {
		new AnnotationConfigApplicationContext(AppConfig2.class).close();
	}

}
