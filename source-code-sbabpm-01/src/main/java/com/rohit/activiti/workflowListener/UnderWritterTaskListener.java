package com.rohit.activiti.workflowListener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

import com.rohit.activiti.util.WorkFlowConstants;

@Component("underWritterTaskComplete")
public class UnderWritterTaskListener implements TaskListener {

	private static final long serialVersionUID = 1L;


	@Override
	public void notify(DelegateTask delegateTask) {
		// TODO Auto-generated method stub

		DelegateExecution execution = delegateTask.getExecution();
		
		Long sumAssuredAmount=(Long)execution.getVariable(WorkFlowConstants.ProcessVariables.SUM_ASSURED_AMOUNT);
		
		Long premiumAmount=(Long)execution.getVariable(WorkFlowConstants.ProcessVariables.PREMIUM_AMOUNT);
		String status =(String) execution.getVariable(WorkFlowConstants.ProcessVariables.STATUS);
		if (premiumAmount > 100000 || (status!=null)) {
			
			execution.setTransientVariableLocal("moreClearification", false);
		} else if(sumAssuredAmount<100000){
			execution.setTransientVariableLocal("moreClearification", false);
		}else {
			execution.setTransientVariableLocal("moreClearification", true);
		}
		
	}

}
