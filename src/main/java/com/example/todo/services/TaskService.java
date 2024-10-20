package com.example.todo.services;

import java.util.List;
import java.util.Optional;

import com.example.todo.domains.Task;

public interface TaskService {
	Task addTask(Task task);

	Optional<Task> read(Long id);
	List<Task> readAll();
	List<Task> readByStatus(String status);
	
	boolean exists(Long id);
	Task update(Long id, Task task);
	
	Task markInProgress(Long id);
	Task markDone(Long id);
	
	Task delete(Long id);
}
