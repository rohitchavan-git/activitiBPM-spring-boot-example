package com.rohit.activiti.workflowListener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

import com.rohit.activiti.util.WorkFlowConstants;

@Component("RMandHAApproved")
public class RMandHAApproved implements TaskListener {

	private static final long serialVersionUID = 1L;

	
	@Override
	public void notify(DelegateTask delegateTask) {
		
		DelegateExecution execution = delegateTask.getExecution();
		execution.setVariable(WorkFlowConstants.ProcessVariables.STATUS, "ApprovedBYRMHA");
	}

}
