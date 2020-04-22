package com.rohit.activiti.service;

import java.util.List;

import org.activiti.engine.task.Task;

import com.rohit.activiti.entity.Insurance;


public interface ActivitiInterface {
	String startNewProcess(String processName,Insurance insurance);

	List<Object> getTasksByUser(String username);
	
	List<Task> getTaskPoolByUserRoll(String userType);

	void completeTask(String taskId);

	void claimTask(String taskId);

	boolean checkInstanceStatus(String processInstanceId);
}
