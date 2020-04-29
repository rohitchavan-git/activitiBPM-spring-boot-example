package com.rohit.activiti;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.task.Task;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.rohit.activiti.service.ActivitiInterface;
import com.rohit.activiti.util.EumSet.EmployeeType;
import com.rohit.activiti.util.WorkFlowConstants;

@SpringBootTest
@AutoConfigureTestDatabase
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class SourceCodeSbabpm01ApplicationTests {

	@Test
	void contextLoads() {
		System.out.println("Context Loaded");
	}

	@Autowired
	private ActivitiInterface activitiInterface;

	@Test
	public void firstTestwhenApplyInsurance_thenCheckInsuranceStatusAndProcessInstanceId() {

		// start the process

		Map<String, Object> processVariable = new HashMap<>();

		processVariable.put(WorkFlowConstants.ProcessVariables.INSURANCE_NAME, "young start");
		processVariable.put(WorkFlowConstants.ProcessVariables.PREMIUM_AMOUNT, 100000L);
		processVariable.put(WorkFlowConstants.ProcessVariables.SUM_ASSURED_AMOUNT, 10000000L);
		processVariable.put(WorkFlowConstants.ProcessVariables.PRODUCT, "TV");
		String processInstanceId = activitiInterface.startNewProcess(WorkFlowConstants.PROCESS_KEY, processVariable);

		// find all tasks of SCRUTING user

		List<Task> taskPoolByUserRoll = activitiInterface.getTaskPoolByUserRoll(EmployeeType.SCRUTING.name());
		// check task is present or not
		assertTrue("Task Not Present for SCRUTING",
				taskPoolByUserRoll.stream().anyMatch(i -> i.getProcessInstanceId().equals(processInstanceId)));
		// if presenet the find out task Id after findout task Id claim it.
		String taskId = taskPoolByUserRoll.stream().filter(i -> i.getProcessInstanceId().equals(processInstanceId))
				.findFirst().get().getId();
		// Claim the Task.
		activitiInterface.claimTask(taskId);

		/**
		 * Complete Task : 1.Sum Assured Amount is better then 5000 and Product="TV"
		 * then Approve 2.Other wise Reject / hold insurance req.
		 */
		activitiInterface.completeTask(taskId);

	}

	@Test
	public void secondTestwhenUnderWritterSFMC_ThenHA_RMTaskGenerated() {
		// Retrive Underwritter task.
		List<Task> taskPoolByUserRoll = activitiInterface.getTaskPoolByUserRoll(EmployeeType.UNDERWRITER.name());

		Assert.notEmpty(taskPoolByUserRoll, "UnderWriiter Task not present!!");

		String taskId = taskPoolByUserRoll.get(0).getId();
		// claim task
		activitiInterface.claimTask(taskId);
		// Complete Task
		activitiInterface.completeTask(taskId);

	}

	@Test
	public void thirdTestwhenRM_HACompleteTask_ThenCheckStatus() {

		List<Task> taskPoolByUserRoll = activitiInterface.getTaskPoolByUserRoll(EmployeeType.HA.name());

		if (taskPoolByUserRoll.isEmpty()) {
			taskPoolByUserRoll = activitiInterface.getTaskPoolByUserRoll(EmployeeType.RM.name());
		}
		String taskId = taskPoolByUserRoll.get(0).getId();
		// after completing HA and RM task send to underwritter update status then task get approved
		activitiInterface.claimTask(taskId);
		activitiInterface.completeTask(taskId);

	}

}
