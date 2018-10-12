package com.hpe.training.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hpe.training.dao.DaoException;
import com.hpe.training.dao.ProductDao;
import com.hpe.training.entity.Product;
import com.hpe.training.entity.ProductList;

@RequestMapping("/api/products")
@RestController // each @RequestMapping annotated function also
// considered to have @ResponseBody annotation
public class ProductsResource {

	@Autowired
	private ProductDao dao;

	// http://..../api/products/23 <-- path variable
	// http://..../api/products?id=23 <-- query parameter
	// http://..../api/products;id=23 <-- matrix parameter

	@RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = { "application/xml", "application/json" })
	public Product getOneProduct(@PathVariable("id") Integer id) throws DaoException {
		return dao.getProductById(id);
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<Product> getProducts(@RequestParam(required = false, name = "min_price") Double min,
			@RequestParam(required = false, name = "max_price") Double max) throws DaoException {

		if (min != null && max != null) {
			return dao.getProductsByPriceRange(min, max);
		}
		return dao.getAllProducts();
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/xml")
	public ProductList getProductsAsXml(@RequestParam(required = false, name = "min_price") Double min,
			@RequestParam(required = false, name = "max_price") Double max) throws DaoException {

		if (min != null && max != null) {
			return new ProductList(dao.getProductsByPriceRange(min, max));
		}
		return new ProductList(dao.getAllProducts());
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = { "application/json",
			"application/xml" })
	public Map<String, Object> saveProduct(@RequestBody Product product) throws DaoException {
		Map<String, Object> out = new HashMap<>();
		try {
			dao.addProduct(product);
			out.put("success", true);
			out.put("product", product);
		} catch (Exception e) {
			e.printStackTrace();
			out.put("success", false);
			out.put("message", e.getMessage());
		}
		return out;
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.PUT, produces = "application/json", consumes = {
			"application/json", "application/xml" })
	public Map<String, Object> updateProduct(@PathVariable Integer id, @RequestBody Product product) {

		Map<String, Object> out = new HashMap<>();
		try {
			Product p = dao.getProductById(id);
			if (p == null) {
				out.put("success", false);
				out.put("message", "Product not found for id: " + id);
			} else {
				product.setId(id); // making sure that correct id is used for update
				dao.updateProduct(product);
				out.put("success", true);
				out.put("product", product);
			}
		} catch (Exception e) {
			out.put("success", false);
			out.put("message", e.getMessage());
		}
		return out;
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE, 
			produces = "application/json")
	public Map<String, Object> deleteProduct(@PathVariable Integer id) {
		Map<String, Object> out = new HashMap<>();
		try {
			Product p = dao.getProductById(id);
			dao.deleteProduct(id);
			out.put("success", true);
			out.put("product", p);
		} catch (Exception e) {
			out.put("success", false);
			out.put("message", e.getMessage());
		}

		return out;
	}

}
