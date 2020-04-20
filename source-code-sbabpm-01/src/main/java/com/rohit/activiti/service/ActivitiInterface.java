package com.rohit.activiti.service;

import java.util.List;

import com.rohit.activiti.entity.Insurance;


public interface ActivitiInterface {
	String startNewProcess(String processName,Insurance insurance);

	List<Object> getTasksByUser(String username);

	void completeTask(String taskId);

	void claimTask(String username);

	boolean checkInstanceStatus(String processInstanceId);
}
