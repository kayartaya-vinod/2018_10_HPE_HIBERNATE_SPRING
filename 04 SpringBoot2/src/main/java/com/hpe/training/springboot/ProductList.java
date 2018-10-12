package com.hpe.training.springboot;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductList {

	@XmlElement(name = "product")
	private List<Product> products;

	public ProductList() {
	}

	public ProductList(List<Product> products) {
		this.products = products;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
