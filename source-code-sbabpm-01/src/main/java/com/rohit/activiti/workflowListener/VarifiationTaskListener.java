package com.rohit.activiti.workflowListener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

import com.rohit.activiti.util.WorkFlowConstants;

@Component("completeTaskListenerDelegateForVarificationTask")
public class VarifiationTaskListener implements TaskListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void notify(DelegateTask delegateTask) {

		DelegateExecution execution = delegateTask.getExecution();
		String product = (String) execution.getVariable(WorkFlowConstants.ProcessVariables.PRODUCT);

		if (product.equals("TV")) {
			// execution.createVariableLocal("isDrbApplicable", true);
			execution.setTransientVariableLocal("isApplicableForInsurance", true);
		} else {
			execution.setTransientVariableLocal("isApplicableForInsurance", false);
		}
	}

}
