package com.example.todo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.todo.domains.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
	List<Task> findByStatus(String status);
}
