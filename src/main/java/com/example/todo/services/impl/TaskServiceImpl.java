package com.example.todo.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todo.domains.Task;
import com.example.todo.repositories.TaskRepository;
import com.example.todo.services.TaskService;

@Service
public class TaskServiceImpl implements TaskService {
	@Autowired
	private TaskRepository taskRepository;
	
	@Override
	public Task addTask(Task task) {
		task.setStatus("Todo");
		
		String currentDateTime = LocalDateTime.now().toString();
		task.setCreatedAt(currentDateTime);
		task.setUpdatedAt(currentDateTime);
		
		return taskRepository.save(task);
	}
	
	@Override
	public Optional<Task> read(Long id) {
		return taskRepository.findById(id);
	}
	
	@Override
	public List<Task> readAll() {
		return StreamSupport.stream(taskRepository
				.findAll()
				.spliterator(),
				false)
			.collect(Collectors.toList());
	}
	
	@Override
	public List<Task> readByStatus(String status) {
		return taskRepository.findByStatus(status);
	}
	
    @Override
    public boolean exists(Long id) {
        return taskRepository.existsById(id);
    }
	
	@Override 
	public Task update(Long id, Task task) {
		task.setId(id);
		
		return taskRepository.findById(id).map(taskFound -> {
			Optional.ofNullable(task.getDescription()).ifPresent(taskFound::setDescription);
			Optional.ofNullable(task.getStatus()).ifPresent(taskFound::setStatus);
			taskFound.setUpdatedAt(LocalDateTime.now().toString());
			
			return taskRepository.save(taskFound);
			
		}).orElseThrow(() -> new RuntimeException("Task with id " + id + " not found"));
	}
	
	@Override 
	public Task markInProgress(Long id) {
		Optional<Task> optionalTask = taskRepository.findById(id);
		
		if(optionalTask.isPresent()) {
			Task task = optionalTask.get();
			task.setStatus("In-Progress");
			task.setUpdatedAt(LocalDateTime.now().toString());
			return taskRepository.save(task);
			
		} else {
			throw new RuntimeException("Task with id " + id + " not found");
		}
	}
	
	@Override 
	public Task markDone(Long id) {
		Optional<Task> optionalTask = taskRepository.findById(id);
		
		if(optionalTask.isPresent()) {
			Task task = optionalTask.get();
			task.setStatus("Done");
			task.setUpdatedAt(LocalDateTime.now().toString());
			return taskRepository.save(task);
			
		} else {
			throw new RuntimeException("Task with id " + id + " not found");
		}
	}
	
	@Override
	public Task delete(Long id) {
		Optional<Task> optionalTask = taskRepository.findById(id);
		
		if(optionalTask.isPresent()) {
			Task task = optionalTask.get();
			taskRepository.delete(task);
			return task;
		} else {
			throw new RuntimeException("Task with id " + id + " not found");
		}
	}
}
