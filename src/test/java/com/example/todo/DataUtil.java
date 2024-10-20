package com.example.todo;

import com.example.todo.domains.Task;

public class DataUtil {
	public static  Task createTaskA() {
		Task task = new Task();
		task.setDescription("eat");
		
		return task;
	}
	
	public static Task createTaskB() {
		Task task = new Task();
		task.setDescription("sleep");
		
		return task;
	}
	
	public static Task createTaskC() {
		Task task = new Task();
		task.setDescription("walk");
		
		return task;
	}
}
