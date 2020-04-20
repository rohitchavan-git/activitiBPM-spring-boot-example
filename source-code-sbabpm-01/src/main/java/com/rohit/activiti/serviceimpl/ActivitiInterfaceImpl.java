package com.rohit.activiti.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rohit.activiti.entity.Insurance;
import com.rohit.activiti.repository.InsuranceRepo;
import com.rohit.activiti.service.ActivitiInterface;
import com.rohit.activiti.util.WorkFlowConstants;

@Service
@Transactional
public class ActivitiInterfaceImpl implements ActivitiInterface {

	@Autowired
	private RuntimeService runtimeService;

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
		return id;
	}

	@Override
	public List<Object> getTasksByUser(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void completeTask(String taskId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void claimTask(String username) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean checkInstanceStatus(String processInstanceId) {
		// TODO Auto-generated method stub
		return false;
	}

}
