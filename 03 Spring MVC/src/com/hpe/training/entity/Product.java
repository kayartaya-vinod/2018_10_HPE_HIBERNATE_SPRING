package com.hpe.training.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@XmlRootElement // equivalent to "implenents Serializable" for IO serialization
@XmlAccessorType(XmlAccessType.FIELD) // use only field values for XML serialization 
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	// products.category_id --> categories.id
	// foreign key in products --> primary key in categories
	// Rule of thumb: foreign key columns are mapped using @ManyToOne
	@ManyToOne(cascade= {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "category_id")
	private Category category;

	private String name;

	// for ManyToOne and OneToOne --> fetch type is 'EAGER' by default
	// for collection types (OneToMany and ManyToMany), its 'LAZY'
	@ManyToOne(cascade= {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "brand_id")
	private Brand brand;

	@Column(name = "quantity_per_unit")
	private String quantityPerUnit;
	@Column(name = "unit_price")
	private Double unitPrice;
	private String picture;
	private Double discount;
	private String description;
}
