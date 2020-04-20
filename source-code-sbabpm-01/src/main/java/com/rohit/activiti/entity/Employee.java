package com.rohit.activiti.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.rohit.activiti.util.EumSet.EmployeeType;

@Entity
@Table(name = "employee")
public class Employee extends User {

	@Enumerated(EnumType.STRING)
	private EmployeeType employeeType;

	public Employee() {

	}

	public Employee(String userName, EmployeeType employeeType) {
		super(userName);
		this.employeeType = employeeType;
	}

	public EmployeeType getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(EmployeeType employeeType) {
		this.employeeType = employeeType;
	}

}
