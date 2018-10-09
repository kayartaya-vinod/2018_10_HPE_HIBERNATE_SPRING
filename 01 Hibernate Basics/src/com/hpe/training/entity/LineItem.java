package com.hpe.training.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "line_items")
public class LineItem implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;

	@Id
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	private Integer quantity;
	
	@Column(name = "unit_price")
	private Double unitPrice;

	public LineItem(Product product, Integer quantity, Double unitPrice) {
		this.product = product;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
	}
	
	
}
