package com.rohit.activiti.serviceimpl;

import java.util.List;
import java.util.Map;

import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.rohit.activiti.service.ActivitiInterface;

@Service
@Transactional
public class ActivitiInterfaceImpl implements ActivitiInterface {

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private TaskService taskService;

	@Override
	public String startNewProcess(String processName, Map<String, Object> processVariable) {

		return runtimeService.startProcessInstanceByKey(processName, processVariable).getId();

	}

	@Override
	public List<Task> getTaskPoolByUserRoll(String userType) {

		return taskService.createTaskQuery().taskCandidateGroup(userType).list();
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
		taskService.claim(taskId, "rohit");

	}


}
