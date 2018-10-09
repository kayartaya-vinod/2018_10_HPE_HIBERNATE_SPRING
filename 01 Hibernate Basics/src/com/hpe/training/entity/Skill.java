package com.hpe.training.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Skill {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name;
	
	// set of employees having "this" skill
	
	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinTable(name="employees_skills",
		joinColumns=@JoinColumn(name="skill_id"),
		inverseJoinColumns=@JoinColumn(name="emp_id"))
	private Set<Employee> employees = new HashSet<>();
	
	public Skill(String name) {
		this.name = name;
	}
	
	// helper function for 2-way association
	public void addEmployee(Employee emp) {
		employees.add(emp);
		emp.getSkills().add(this);
	}
}




