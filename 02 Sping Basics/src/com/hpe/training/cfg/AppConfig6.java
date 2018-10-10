package com.hpe.training.cfg;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import com.hpe.training.entity.Brand;
import com.hpe.training.entity.Category;
import com.hpe.training.entity.Customer;
import com.hpe.training.entity.LineItem;
import com.hpe.training.entity.Order;
import com.hpe.training.entity.Product;

@Configuration
public class AppConfig6 {
	
	@Bean
	public HibernateTemplate hibernateTemplate(SessionFactory sessionFactory) {
		// The matched object for "sessionFactory" is the bean of type
		// LocalSessionFactoryBean, which is not an implementation of SessionFactory.
		// But, spring calls the getObject() of the bean and the return value
		// is supplied as dependency to this function (which is an instnace of 
		// SessionFactory). For example, lsfb.getObject()
		return new HibernateTemplate(sessionFactory);
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
		LocalSessionFactoryBean lsfb;
		lsfb = new LocalSessionFactoryBean();
		
		// db information
		lsfb.setDataSource(dataSource);
		
		// mapping information
		lsfb.setAnnotatedClasses(Product.class, Brand.class, Category.class, 
				Customer.class, Order.class, LineItem.class);
		
		// additional hibernate properties
		Properties props = new Properties();
		props.setProperty("hibernate.show_sql", "false");
		props.setProperty("hibernate.format_sql", "true");
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		lsfb.setHibernateProperties(props);
		
		return lsfb;
	}
	
	@Bean
	public DataSource dataSource() {
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
