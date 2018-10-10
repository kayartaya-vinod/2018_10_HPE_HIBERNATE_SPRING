package com.hpe.training.cfg;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "com.hpe.training.dao" })
public class AppConfig4 {
	
	@Bean
	public DataSource mysqlDbcp() {
		BasicDataSource bds = new BasicDataSource();
		bds.setUrl("jdbc:mysql://localhost/hpe_training_2018_10");
		bds.setUsername("root");
		bds.setPassword("root");
		bds.setDriverClassName("com.mysql.jdbc.Driver");
		return bds;
	}
	
	@Bean 
	public DataSource h2Dbcp() {
		BasicDataSource bds = new BasicDataSource();
		bds.setUrl("jdbc:h2:tcp://localhost/~/hpe_training_2018_10");
		bds.setUsername("sa");
		bds.setPassword("");
		bds.setDriverClassName("org.h2.Driver");

		bds.setInitialSize(10);
		bds.setMaxWaitMillis(50);
		return bds;
	}
}
