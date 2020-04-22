package com.rohit.activiti.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rohit.activiti.entity.Employee;
import com.rohit.activiti.util.EumSet.EmployeeType;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {

	
	@Query("select new Map( u.id as userId ) from Employee u where u.employeeType=:employeeType")
	List<Map<String,Object>> getUserIdsByEmployeeType(@Param("employeeType")EmployeeType employeeType);
}
