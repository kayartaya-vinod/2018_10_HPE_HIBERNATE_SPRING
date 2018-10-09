package com.hpe.training.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor

@Getter
@Setter
@Entity
public class Laptop {
	@Id
	private String serialNumber;
	private String make;
	private String model;

	// mappedBy="laptop" --> Employee.laptop, which has mapping info
	@OneToOne(mappedBy = "laptop", 
			cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	private Employee ownedBy;
	// Alternately, you can still use (instead of mappedBy="..")
	// @JoinColumn(name="laptop_serial_number", unique=true)
	// which corresponds to the foreign key in EMPLOYEE table

	public Laptop(String serialNumber, String make, String model) {
		this.serialNumber = serialNumber;
		this.make = make;
		this.model = model;
	}

	// helper function; make sure this does not lead to recursion
	public void setOwner(Employee emp) {
		this.ownedBy = emp;
		emp.setLaptop(this);
	}

	
	

}
