package com.hpe.training.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "brands")
public class Brand implements Comparable<Brand> {

	// by default Hibernate maps all the fields of this class to
	// respective columns of the corresponding table (brands)

	@Id // mapping the primary (candidate) key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;

	// one brand --> many products
	// mappedBy="brand" --> "brand" is the field in Product.java
	// which already has the join-column mapping information.
	@OneToMany(mappedBy = "brand")
	@XmlTransient
	@JsonIgnore
	private List<Product> products;

	public Brand() {
	}

	public Brand(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Brand [id=" + id + ", name=" + name + "]";
	}

	@Override
	public int compareTo(Brand other) {
		return this.id-other.id;
	}

}
