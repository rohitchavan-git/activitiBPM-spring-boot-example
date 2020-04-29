package com.rohit.activiti.service;

import java.util.List;
import java.util.Map;

import org.activiti.engine.task.Task;


public interface ActivitiInterface {
	String startNewProcess(String processName,Map<String,Object> processVariable);

	List<Task> getTaskPoolByUserRoll(String userType);

	void completeTask(String taskId);

	void claimTask(String taskId);

}
