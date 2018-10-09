package com.hpe.training.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String department;
	private Double salary = 20000.0;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(unique = true, name = "laptop_serial_number")
	private Laptop laptop;
	
	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST },
			mappedBy="employees") // the field Skill.employees
	private Set<Skill> skills = new HashSet<>();

	public Employee(String name, String department) {
		this.name = name;
		this.department = department;
	}

	public Employee(String name, String department, Double salary) {
		this.name = name;
		this.department = department;
		this.salary = salary;
	}
	
	// helper function to associate 2-way binding
	public void addSkill(Skill skill) {
		skills.add(skill);
		skill.getEmployees().add(this);
	}

}
