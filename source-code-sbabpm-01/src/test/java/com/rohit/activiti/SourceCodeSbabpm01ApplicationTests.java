package com.rohit.activiti;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.task.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rohit.activiti.entity.Customer;
import com.rohit.activiti.entity.Insurance;
import com.rohit.activiti.repository.CustomerRepo;
import com.rohit.activiti.repository.InsuranceRepo;
import com.rohit.activiti.service.ActivitiInterface;
import com.rohit.activiti.util.EumSet.EmployeeType;
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
		// start te process
		activitiInterface.startNewProcess(WorkFlowConstants.PROCESS_KEY, insurance);

		// find all tasks of SCRUTING user

		List<Task> taskPoolByUserRoll = activitiInterface.getTaskPoolByUserRoll(
				EmployeeType.SCRUTING.name());
		// check task is present or not
		assertTrue("Task Not Present for SCRUTING", taskPoolByUserRoll.stream()
				.anyMatch(i -> i.getProcessInstanceId().equals(insurance.getProcessInstanceId())));
		// if presenet the find out task Id after findout task Id claim it.
		String taskId = taskPoolByUserRoll.stream()
				.filter(i -> i.getProcessInstanceId()
						.equals(insurance.getProcessInstanceId()))
				.findFirst().get()
				.getId();
		// Claim the Task.
		activitiInterface.claimTask(taskId);

		/**
		 * Complete Task : 
		 * 1.Sum Assured Amount is better then 5000 and Product="TV"
		 * then Approve 
		 * 2.Other wise Reject / hold insurance req.
		 */
		activitiInterface.completeTask(taskId);

	}

//	@Test
//	public void whenCompleteTask_ThenCheckStatus() {
//		activitiInterface.completeTask("c44b1306-8309-11ea-bc7c-7c05070ec9ab");
//	}

	private Insurance initInsurance() {
		Customer customer = customerRepo.findById(1L).get();
		Insurance insurance = new Insurance("young Start", "TV", 100000d, 10000000d, null, null);
		insurance = insuranceRepo.save(insurance);
		List<Insurance> insuranes = new ArrayList<>();
		insuranes.add(insurance);
		customer.setInsurance(insuranes);
		return insurance;
	}

}
