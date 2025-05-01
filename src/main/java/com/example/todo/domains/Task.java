package com.example.todo.domains;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tasks")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tasks_id_seq")
	private Long id;
	private String description;
	private String status;
	private String createdAt;
	private String updatedAt;
}
