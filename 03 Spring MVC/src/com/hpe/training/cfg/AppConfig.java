package com.hpe.training.cfg;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.hpe.training.entity.Brand;
import com.hpe.training.entity.Category;
import com.hpe.training.entity.Product;

@EnableTransactionManagement
@Configuration
@ComponentScan(basePackages = { "com.hpe.training.web", "com.hpe.training.dao" })
public class AppConfig {
	
	// ViewResolver configuration
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver vr = new InternalResourceViewResolver();
		vr.setPrefix("/WEB-INF/pages/");
		vr.setSuffix(".jsp");
		return vr;
	}
	
	@Bean
	public HibernateTransactionManager txManager(SessionFactory factory) {
		return new HibernateTransactionManager(factory);
	}
	
	@Bean 
	public HibernateTemplate template(SessionFactory sessionFactory) {
		return new HibernateTemplate(sessionFactory);
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
		LocalSessionFactoryBean lsfb;
		lsfb = new LocalSessionFactoryBean();
		lsfb.setDataSource(dataSource);
		lsfb.setAnnotatedClasses(Product.class, Brand.class, Category.class);
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
