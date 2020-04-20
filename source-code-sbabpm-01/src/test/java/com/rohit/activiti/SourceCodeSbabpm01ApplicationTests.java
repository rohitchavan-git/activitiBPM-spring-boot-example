package com.rohit.activiti;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rohit.activiti.entity.Customer;
import com.rohit.activiti.entity.Insurance;
import com.rohit.activiti.repository.CustomerRepo;
import com.rohit.activiti.repository.InsuranceRepo;
import com.rohit.activiti.service.ActivitiInterface;
import com.rohit.activiti.util.WorkFlowConstants;

@SpringBootTest
class SourceCodeSbabpm01ApplicationTests {


	@Test
	void contextLoads() {
		System.out.println("Context Loaded");
	}
	
	@Autowired
	private ActivitiInterface activitiInterface;
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private InsuranceRepo insuranceRepo;
	
	
	@Test
	public void whenApplyInsurance_thenCheckInsuranceStatusAndProcessInstanceId() {
		Insurance insurance = initInsurance();
		
		activitiInterface.startNewProcess(WorkFlowConstants.PROCESS_KEY, insurance);
		
	}


	private Insurance initInsurance() {
		Customer customer = customerRepo.findById(1L).get();
		Insurance insurance = new Insurance("young Start", "TV", 100000d, 10000000d, null, null);
		insurance=insuranceRepo.save(insurance);
		List<Insurance> insuranes=new ArrayList<>();
		insuranes.add(insurance);
		customer.setInsurance(insuranes);
		return insurance;
	}

}
