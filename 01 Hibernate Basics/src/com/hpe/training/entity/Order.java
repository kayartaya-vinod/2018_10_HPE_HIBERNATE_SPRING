package com.hpe.training.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@Column(name = "order_date")
	private Date orderDate = new Date();

	private String status = "PENDING";

	@OneToMany(mappedBy="order", 
			cascade= {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<LineItem> lineItems;

	// custom helper method for doing two way binding
	public void addLineItem(LineItem item) {
		if (lineItems == null) {
			lineItems = new HashSet<>();
		}
		lineItems.add(item);
		item.setOrder(this);
	}
}
