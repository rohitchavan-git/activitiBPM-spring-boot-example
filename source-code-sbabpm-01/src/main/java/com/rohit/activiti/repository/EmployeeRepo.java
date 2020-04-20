package com.rohit.activiti.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rohit.activiti.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {

}
