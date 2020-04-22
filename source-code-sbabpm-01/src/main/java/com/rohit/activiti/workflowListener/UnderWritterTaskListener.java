package com.rohit.activiti.workflowListener;

import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rohit.activiti.entity.Insurance;
import com.rohit.activiti.repository.InsuranceRepo;
import com.rohit.activiti.service.ActivitiInterface;
import com.rohit.activiti.util.EumSet.InsuranceStatus;
import com.rohit.activiti.util.WorkFlowConstants;

@Component("underWritterTaskComplete")
public class UnderWritterTaskListener implements TaskListener {

	private static final long serialVersionUID = 1L;

	@Autowired
	private InsuranceRepo insuranceRepo;

	@Override
	public void notify(DelegateTask delegateTask) {
		// TODO Auto-generated method stub

		DelegateExecution execution = delegateTask.getExecution();
		Long applicationNo = (Long) execution.getVariable(WorkFlowConstants.ProcessVariables.APPLICATION_NO, false);
		Insurance insurance = insuranceRepo.findById(applicationNo).get();
		if (insurance.getPremiumAmount() > 100000) {
			execution.setTransientVariableLocal("isUnderWritterApproved", true);
			insurance.setStatus(InsuranceStatus.APPROVED);
		} else if(insurance.getSumAssuredAmount()<100000){
			execution.setTransientVariableLocal("isUnderWritterApproved", false);
			insurance.setStatus(InsuranceStatus.NOT_APPROVED);
		}else {
			insurance.setStatus(InsuranceStatus.SEND_FOR_CLEARIFICATION);
		}
		insuranceRepo.save(insurance);
	}

}
