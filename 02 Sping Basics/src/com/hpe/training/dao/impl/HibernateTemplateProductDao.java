package com.hpe.training.dao.impl;

import java.util.List;

import javax.naming.NoPermissionException;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.hpe.training.dao.DaoException;
import com.hpe.training.dao.ProductDao;
import com.hpe.training.entity.Brand;
import com.hpe.training.entity.Product;

// @Service --> business logic classes (service layer)
// @Repository --> data access layer (DAO layer)
// @Controller --> web controller classes (work with service/dao layers and view)
// @RestController --> RESTful web service controllers (resources)
// @Component --> for classes that do not belong to any of the above (aspects, ..)
// @Configuration --> annotation based configuration classes
@Repository("htDao")
@SuppressWarnings("unchecked")
public class HibernateTemplateProductDao implements ProductDao {

	@Autowired(required = false)
	private HibernateTemplate template;

	@Override
	public void addProduct(Product product) throws DaoException {
		// this function and all other ProductDao function implementations
		// should catch Spring's DataAccessException and convert it into
		// DaoException and throw the same. Let spring handle this using AOP.
		template.persist(product);
	}

	@Override
	public Product getProductById(Integer id) throws DaoException {
		return template.get(Product.class, id);
	}

	@Override
	public void updateProduct(Product product) throws DaoException {
		template.merge(product);
	}

	@Override
	public void deleteProduct(Integer id) throws DaoException {
		Product product = getProductById(id);
		if (product == null) {
			throw new DaoException("Invalid product id: " + id);
		}
		template.delete(product);
	}

	@Override
	public List<Product> getAllProducts() throws DaoException {
		return (List<Product>) template.find("from Product");
	}

	@Override
	public List<Product> getProductsByBrand(Integer brandId) throws DaoException {
		Brand b = template.get(Brand.class, brandId);
		Product p = new Product();
		p.setBrand(b);
		
		return template.findByExample(p);
	}

	@Override
	public List<Product> getProductsByCategory(Integer categoryId) throws DaoException {
		DetachedCriteria cr = DetachedCriteria.forClass(Product.class);
		cr.createAlias("category", "c");
		cr.add(Restrictions.eq("c.id", categoryId));
		return (List<Product>) template.findByCriteria(cr);
	}

	@Override
	public List<Product> getProductsByPriceRange(Double min, Double max) throws DaoException {
		return (List<Product>) template.find(
				"from Product where unitPrice between ? and ?", min, max);
	}

	@Override
	public int count() throws DaoException {
		Object pc = template.find("select count(p) from Product p").get(0);
		// pc.toString() --> "85"
		return new Integer(pc.toString());
	}

}
