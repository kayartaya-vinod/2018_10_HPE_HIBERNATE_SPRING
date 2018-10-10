package com.hpe.training.cfg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hpe.training.dao.ProductDao;
import com.hpe.training.dao.impl.JdbcProductDao;

//this class is equivalent of the context.xml's <beans> tag
@Configuration
public class AppConfig1 {
	
	// this function is equivalent of <bean> tag
	// Spring calls any function annotated with @Bean, collects the
	// return value and stores in the container with the name of the 
	// function as the name of the bean (unless explicitly specified)
	@Bean(name = { "dao", "jdbc-dao" })
	public ProductDao dao() {
		System.out.println("from inside of AppConfig1.dao() function.");
		JdbcProductDao jpd = new JdbcProductDao();
		jpd.setDriverClassName("org.h2.Driver");
		jpd.setUrl("jdbc:h2:tcp://localhost/~/hpe_training_2018_10");
		jpd.setUsername("sa");
		jpd.setPassword("");
		return jpd;
	}
}
