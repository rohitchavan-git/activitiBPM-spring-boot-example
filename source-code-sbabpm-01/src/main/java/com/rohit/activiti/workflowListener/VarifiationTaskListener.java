package com.rohit.activiti.workflowListener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rohit.activiti.entity.Insurance;
import com.rohit.activiti.repository.InsuranceRepo;
import com.rohit.activiti.util.EumSet.InsuranceStatus;
import com.rohit.activiti.util.WorkFlowConstants;

@Component("completeTaskListenerDelegateForVarificationTask")
public class VarifiationTaskListener implements TaskListener {

	private static final long serialVersionUID = 1L;

	@Autowired
	private InsuranceRepo insuranceRepo;

	@Override
	public void notify(DelegateTask delegateTask) {

		DelegateExecution execution = delegateTask.getExecution();
		Long applicationNo = (Long) execution.getVariable(WorkFlowConstants.ProcessVariables.APPLICATION_NO, false);
		Insurance insurance = insuranceRepo.findById(applicationNo).get();
		if (insurance.getProduct().equals("TV")) {
			// execution.createVariableLocal("isDrbApplicable", true);
			execution.setTransientVariableLocal("isApplicableForInsurance", true);
			insurance.setStatus(InsuranceStatus.APPROVED_BY_SCRUT);
		} else {
			execution.setTransientVariableLocal("isApplicableForInsurance", false);
			insurance.setStatus(InsuranceStatus.NOT_APPROVED);
		}
		insuranceRepo.save(insurance);
	}

}
