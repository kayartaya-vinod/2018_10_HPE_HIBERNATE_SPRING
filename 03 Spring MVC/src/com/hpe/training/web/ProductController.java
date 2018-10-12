package com.hpe.training.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hpe.training.dao.DaoException;
import com.hpe.training.dao.ProductDao;
import com.hpe.training.entity.Product;

// because this is a special type of @Component, called "controller",
// HandlerMapping (eg, RequestMappingHandlerMapping [RMHM]) scans for all the beans
// found in the spring container and strats its work on the same.
@Controller
public class ProductController {
	
	@Autowired
	ProductDao dao;

	// RMHM creates a Map of Request-info --> Handler object for all the 
	// methods which are annotated using @RequestMapping
	@RequestMapping(path = {"/product-list", "/show-products"}, 
			method = RequestMethod.GET)
	public ModelAndView getAllProducts() throws DaoException {

		List<Product> list = dao.getAllProducts();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("products", list);
		mav.setViewName("show-products"); // basename of the JSP; ViewResolver will 
		// add the prefix and suffix (ref: AppConfig.java)
		
		
		// The view has access to the model data (list) via the key (products)
		return mav;
	}
}
