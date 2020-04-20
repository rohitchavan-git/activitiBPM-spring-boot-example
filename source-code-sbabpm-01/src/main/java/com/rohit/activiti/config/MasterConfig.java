package com.rohit.activiti.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.rohit.activiti.entity.Customer;
import com.rohit.activiti.entity.Employee;
import com.rohit.activiti.repository.CustomerRepo;
import com.rohit.activiti.repository.EmployeeRepo;
import com.rohit.activiti.util.EumSet.EmployeeType;

@Configuration
public class MasterConfig {

	@Value(value = "${activiti.master.enable}")
	private boolean masterConfigEnable;

	@Autowired
	private EmployeeRepo employeeRepo;

	@Autowired
	private CustomerRepo customerRepos;

	@Autowired
	public void addMasterData() {
		if (masterConfigEnable) {

			addCustomer();
			addEmployees();
		}
	}

	private void addEmployees() {
		List<Employee> allEmployees = new ArrayList<>();
		allEmployees.add(new Employee("urmia", EmployeeType.SCRUTING));
		allEmployees.add(new Employee("chinmayi", EmployeeType.SCRUTING));
		allEmployees.add(new Employee("irfan", EmployeeType.SCRUTING));

		allEmployees.add(new Employee("pavan", EmployeeType.UNDERWRITER));
		allEmployees.add(new Employee("swastika", EmployeeType.UNDERWRITER));

		allEmployees.add(new Employee("shirmila", EmployeeType.HA));
		allEmployees.add(new Employee("pranitha", EmployeeType.HA));

		allEmployees.add(new Employee("rohit", EmployeeType.RM));
		allEmployees.add(new Employee("kiran", EmployeeType.RM));

		employeeRepo.saveAll(allEmployees);
	}

	private void addCustomer() {
		List<Customer> allCustomer = new ArrayList<>();
		allCustomer.add(new Customer("sagar", true, null));
		allCustomer.add(new Customer("pratik", true, null));
		allCustomer.add(new Customer("srekhar", true, null));

		customerRepos.saveAll(allCustomer);
	}
}
