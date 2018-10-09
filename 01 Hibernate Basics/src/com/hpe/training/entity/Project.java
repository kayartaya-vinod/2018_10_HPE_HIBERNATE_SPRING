package com.hpe.training.entity;

import java.util.Set;

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

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String description;

	// set of employees worked/working in the project
	@ManyToMany
	@JoinTable(name = "employees_projects", 
		joinColumns = @JoinColumn(name="project_id"), 
		inverseJoinColumns = @JoinColumn(name="employee_id"))
	private Set<Employee> employees;

}
