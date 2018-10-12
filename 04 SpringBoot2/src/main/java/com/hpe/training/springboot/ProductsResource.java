package com.hpe.training.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restapi/products")
public class ProductsResource {
	
	@Autowired
	private ProductDao dao;

	@RequestMapping(
			path = "/{id}",
			method=RequestMethod.GET, 
			produces= {"application/json"})
	public Product getOneProduct(@PathVariable("id") Integer id) {
		System.out.println("Got id : " + id);
		Product p = dao.findById(id).get();
		System.out.println("Got product: " + p.getName());
		return p;
	}
	
	@RequestMapping(
			method=RequestMethod.GET, 
			produces= {"application/json"})
	public Iterable<Product> getAll() {
		return dao.findAll();
	}
	

	@RequestMapping(
			path="/bydiscount/{discount}",
			method=RequestMethod.GET, 
			produces= {"application/json"})
	public Iterable<Product> getProductsByDiscount(@PathVariable Double discount) {
		return dao.findByDiscount(discount);
	}
}
