package com.hpe.training.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.hpe.training.dao.DaoException;
import com.hpe.training.dao.ProductDao;
import com.hpe.training.entity.Product;

@Component("dao") // @Repository, @Service, @Controller, @RestController, @Configuration
public class JdbcProductDao implements ProductDao {

	// use these for manufacturing a new Connection instance
	private String url;
	private String username;
	private String password;
	private String driverClassName;

	// use this for borrowing a connection instance from the pool
	@Autowired(required = false)
	@Qualifier("h2Dbcp")
	private DataSource dataSource;

	// use this directly (supplied by the user of this class)
	private Connection conn;

	public JdbcProductDao() {
	}

	public JdbcProductDao(String driverClassName, String url, String username, String password) {
		this.driverClassName = driverClassName;
		this.url = url;
		this.username = username;
		this.password = password;
		System.out.println("JdbcProductDao(driver, url, user, password) called");
	}

	public JdbcProductDao(DataSource dataSource) {
		this.dataSource = dataSource;
		System.out.println("JdbcProductDao(DataSource) called");
	}

	public JdbcProductDao(Connection conn) {
		this.conn = conn;
		System.out.println("JdbcProductDao(Connection) called");
	}

	// this is a writable property called "url"
	// this is invoked by spring when the following is found in the XML:
	// <property name="url" value="..." />
	public void setUrl(String url) {
		this.url = url;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	private Connection openConnection() throws SQLException, ClassNotFoundException {
		// first priority --> pool
		if (dataSource != null) {
			return dataSource.getConnection();
		}
		// second priority --> connection
		if (conn != null) {
			return conn;
		}
		// last priority --> manufacture a new connection
		Class.forName(driverClassName);
		return DriverManager.getConnection(url, username, password);
	}

	@Override
	public int count() throws DaoException {
		try (Connection conn = openConnection();
				PreparedStatement stmt = conn.prepareStatement("select count(*) from products");
				ResultSet rs = stmt.executeQuery();) {
			rs.next();
			return rs.getInt(1);
		} catch (Exception ex) {
			throw new DaoException(ex); // exception funnelling
		}
	}

	@Override
	public void addProduct(Product product) throws DaoException {
		throw new DaoException("Method not implemented yet!");
	}

	@Override
	public Product getProductById(Integer id) throws DaoException {
		throw new DaoException("Method not implemented yet!");
	}

	@Override
	public void updateProduct(Product product) throws DaoException {
		throw new DaoException("Method not implemented yet!");
	}

	@Override
	public void deleteProduct(Integer id) throws DaoException {
		throw new DaoException("Method not implemented yet!");
	}

	@Override
	public List<Product> getAllProducts() throws DaoException {
		throw new DaoException("Method not implemented yet!");
	}

	@Override
	public List<Product> getProductsByBrand(Integer brandId) throws DaoException {
		throw new DaoException("Method not implemented yet!");
	}

	@Override
	public List<Product> getProductsByCategory(Integer categoryId) throws DaoException {
		throw new DaoException("Method not implemented yet!");
	}

	@Override
	public List<Product> getProductsByPriceRange(Double min, Double max) throws DaoException {
		throw new DaoException("Method not implemented yet!");
	}

}
