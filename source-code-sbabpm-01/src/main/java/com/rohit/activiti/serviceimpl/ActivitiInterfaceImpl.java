package com.rohit.activiti.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.rohit.activiti.entity.Insurance;
import com.rohit.activiti.repository.EmployeeRepo;
import com.rohit.activiti.repository.InsuranceRepo;
import com.rohit.activiti.service.ActivitiInterface;
import com.rohit.activiti.util.EumSet.EmployeeType;
import com.rohit.activiti.util.WorkFlowConstants;

@Service
@Transactional
public class ActivitiInterfaceImpl implements ActivitiInterface {

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private EmployeeRepo employeeRepo;

	@Autowired
	private TaskService taskService;

	@Autowired
	private InsuranceRepo insuranceRepo;

	@Override
	public String startNewProcess(String processName, Insurance insurance) {
		Map<String, Object> processVariable = new HashMap<>();
		processVariable.put(WorkFlowConstants.ProcessVariables.APPLICATION_NO, insurance.getApplicationNo());
		processVariable.put(WorkFlowConstants.ProcessVariables.INSURANCE_NAME, insurance.getInsuranceName());
		processVariable.put(WorkFlowConstants.ProcessVariables.PREMIUM_AMOUNT, insurance.getPremiumAmount());
		processVariable.put(WorkFlowConstants.ProcessVariables.SUM_ASSURED_AMOUNT, insurance.getSumAssuredAmount());
		processVariable.put(WorkFlowConstants.ProcessVariables.PRODUCT, insurance.getProduct());
		String id = runtimeService.startProcessInstanceByKey(processName, processVariable).getId();
		insurance.setProcessInstanceId(id);
		insuranceRepo.save(insurance);
		return id;
	}

	@Override
	public List<Task> getTaskPoolByUserRoll(String userType) {

		return taskService.createTaskQuery().taskCandidateGroup(userType).list();
	}

	@Override
	public List<Object> getTasksByUser(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void completeTask(String taskId) {

		Assert.notNull(taskId, "taskId can't be null!!");
		try {
			taskService.complete(taskId);
		} catch (ActivitiObjectNotFoundException e) {
			throw new IllegalArgumentException("Please Provide Valid Task Id");
		}
	}

	@Override
	public void claimTask(String taskId) {

		String userId = employeeRepo.getUserIdsByEmployeeType(EmployeeType.SCRUTING)
				.get(0).get("userId").toString();
		taskService.claim(taskId, userId);

	}

	@Override
	public boolean checkInstanceStatus(String processInstanceId) {
		// TODO Auto-generated method stub
		return false;
	}

}
