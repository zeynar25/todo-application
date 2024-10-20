package com.example.todo.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo.domains.Task;
import com.example.todo.domains.dto.TaskDto;
import com.example.todo.mappers.Mapper;
import com.example.todo.services.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private Mapper<Task, TaskDto> taskMapper;
	
	@PostMapping
	public ResponseEntity<TaskDto> addTask(@RequestBody TaskDto taskDto) {
		Task task = taskMapper.mapFrom(taskDto);
		Task savedTask = taskService.addTask(task);
		return new ResponseEntity<>(taskMapper.mapTo(savedTask), HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TaskDto> listTasks(@PathVariable("id") Long id) {
		Optional<Task> foundTask = taskService.read(id);
		
		return foundTask.map(task -> {
			return new ResponseEntity<>(taskMapper.mapTo(task), HttpStatus.OK);
		}).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@GetMapping
	public List<TaskDto> listTasks() {
		List<Task> tasks = taskService.readAll();
		return tasks.stream()
				.map(taskMapper::mapTo)
				.collect(Collectors.toList());
	}
	
	@GetMapping("/done")
	public List<TaskDto> listDoneTasks() {
		List<Task> doneTasks = taskService.readByStatus("Done");
		
		return doneTasks.stream()
				.map(taskMapper::mapTo)
				.collect(Collectors.toList());
	}
	
	@GetMapping("/todo")
	public List<TaskDto> listTodoTasks() {
		List<Task> doneTasks = taskService.readByStatus("Todo");
		
		return doneTasks.stream()
				.map(taskMapper::mapTo)
				.collect(Collectors.toList());
	}
	
	@GetMapping("/in-progress")
	public List<TaskDto> listInProgressTasks() {
		List<Task> doneTasks = taskService.readByStatus("In-Progress");
		
		return doneTasks.stream()
				.map(taskMapper::mapTo)
				.collect(Collectors.toList());
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<TaskDto> updateTask(@PathVariable("id") Long id, @RequestBody TaskDto taskDto) {
		if(taskService.exists(id)) {
			Task task = taskMapper.mapFrom(taskDto);
			Task savedTask = taskService.update(id, task);
			return new ResponseEntity<>(taskMapper.mapTo(savedTask), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PatchMapping("/in-progress/{id}")
	public ResponseEntity<Task> markInProgress(@PathVariable("id") Long id) {
		if(taskService.exists(id)) {
			Task task = taskService.markInProgress(id);
			return new ResponseEntity<>(task, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PatchMapping("/done/{id}")
	public ResponseEntity<Task> markDone(@PathVariable("id") Long id) {
		if(taskService.exists(id)) {
			Task task = taskService.markDone(id);
			return new ResponseEntity<>(task, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Task> delete(@PathVariable("id") Long id) {
		if(taskService.exists(id)) {
			return new ResponseEntity<>(taskService.delete(id), HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
