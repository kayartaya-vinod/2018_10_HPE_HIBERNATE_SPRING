package com.hpe.training.cfg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hpe.training.dao.ProductDao;
import com.hpe.training.dao.impl.JdbcProductDao;

@Configuration
public class AppConfig3 {
	
	@Bean
	public Connection conn() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost/hpe_training_2018_10", "root", "root");
	}
	
	// one of the dbcp beans must have name "dataSource"
	@Bean(autowire=Autowire.BY_NAME) 
	public ProductDao dao() {
		// wires both "dataSource" and "conn" beans to this bean
		return new JdbcProductDao();
	}

	// @Bean
	public ProductDao dao1(@Qualifier("h2ds") DataSource ds) {
		// since this is a function annotated with @Bean,
		// spring is expected to call this by supplying an
		// instanceof DataSource (example of Dependency injection)
		return new JdbcProductDao(ds); // manual wiring
	}

	
	@Bean(name = { "mysqlds"})
	public DataSource dbcp1() {
		BasicDataSource bds = new BasicDataSource();
		bds.setUrl("jdbc:mysql://localhost/hpe_training_2018_10");
		bds.setUsername("root");
		bds.setPassword("root");
		bds.setDriverClassName("com.mysql.jdbc.Driver");
		return bds;
	}

	@Bean(name = { "h2ds", "dataSource"  })
	public DataSource dbcp2() {
		BasicDataSource bds = new BasicDataSource();
		bds.setUrl("jdbc:h2:tcp://localhost/~/hpe_training_2018_10");
		bds.setUsername("sa");
		bds.setPassword("");
		bds.setDriverClassName("org.h2.Driver");

		bds.setInitialSize(10);
		bds.setMaxWaitMillis(50);
		return bds;
	}

	// @Bean(name = { "dao" })
	public ProductDao dao2() {
		JdbcProductDao jpd = new JdbcProductDao(dbcp2()); // manual wiring
		// jpd.setDataSource(dbcp()); // manual wiring
		// call to dbcp() is executed on the proxy and not on
		// the object of this class. The proxy function returns
		// already cached bean instance, collected by spring initially.
		return jpd;
	}
}
