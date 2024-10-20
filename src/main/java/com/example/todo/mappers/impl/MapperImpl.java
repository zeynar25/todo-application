package com.example.todo.mappers.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.todo.domains.Task;
import com.example.todo.domains.dto.TaskDto;
import com.example.todo.mappers.Mapper;

@Component
public class MapperImpl implements Mapper<Task, TaskDto>{
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public TaskDto mapTo(Task task) {
		return modelMapper.map(task, TaskDto.class);
	}

	@Override
	public Task mapFrom(TaskDto task) {
		return modelMapper.map(task, Task.class);
	}
}
