package com.example.todo.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.todo.DataUtil;
import com.example.todo.domains.Task;
import com.example.todo.services.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class TaskControllerTests {
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	public void canAddTask() throws Exception{
		Task taskA = DataUtil.createTaskA();
		String taskJson = objectMapper.writeValueAsString(taskA);
		
		mockMvc.perform(
				MockMvcRequestBuilders.post("/tasks")
					.contentType(MediaType.APPLICATION_JSON)
					.content(taskJson)
		).andExpect(
				MockMvcResultMatchers.status().isCreated()
		).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
				MockMvcResultMatchers.jsonPath("$.description").value(taskA.getDescription())
		).andExpect(
        		MockMvcResultMatchers.jsonPath("$.status").value("Todo")
		).andExpect(
        		MockMvcResultMatchers.jsonPath("$.createdAt").exists()
		).andExpect(
        		MockMvcResultMatchers.jsonPath("$.updatedAt").exists()
		);
	}
	
	@Test
	public void canViewATask() throws Exception{
		Task taskA = DataUtil.createTaskA();
		Task savedTask = taskService.addTask(taskA);
		
		mockMvc.perform(
				MockMvcRequestBuilders.get("/tasks/" + savedTask.getId())
				.contentType(MediaType.APPLICATION_JSON)
		).andExpect(
				MockMvcResultMatchers.status().isOk()
		).andExpect(
				MockMvcResultMatchers.jsonPath("$.id").value(savedTask.getId())
		).andExpect(
				MockMvcResultMatchers.jsonPath("$.description").value(savedTask.getDescription())
		).andExpect(
				MockMvcResultMatchers.jsonPath("$.status").value(savedTask.getStatus())
		).andExpect(
        		MockMvcResultMatchers.jsonPath("$.createdAt").exists()
		).andExpect(
        		MockMvcResultMatchers.jsonPath("$.updatedAt").exists()
		);
	}
	
	@Test
	public void canViewTasks() throws Exception{
		Task taskA = DataUtil.createTaskA();
		Task taskB = DataUtil.createTaskB();
		
		Task savedTaskA = taskService.addTask(taskA);
		Task savedTaskB = taskService.addTask(taskB);
		
		mockMvc.perform(
				MockMvcRequestBuilders.get("/tasks")
				.contentType(MediaType.APPLICATION_JSON)
		).andExpect(
				MockMvcResultMatchers.jsonPath("$[0].id").value(savedTaskA.getId())
		).andExpect(
				MockMvcResultMatchers.jsonPath("$[1].id").value(savedTaskB.getId())
		).andExpect(
				MockMvcResultMatchers.jsonPath("$[0].description").value(savedTaskA.getDescription())
		).andExpect(
				MockMvcResultMatchers.jsonPath("$[1].description").value(savedTaskB.getDescription())
		).andExpect(
				MockMvcResultMatchers.jsonPath("$[0].status").value(savedTaskA.getStatus())
		).andExpect(
				MockMvcResultMatchers.jsonPath("$[1].status").value(savedTaskB.getStatus())
		);
	}
	
	@Test
	public void canUpdateTask() throws Exception {
		Task taskA = DataUtil.createTaskA();
		Task savedTask = taskService.addTask(taskA);
		
		Task taskB = DataUtil.createTaskB();
		String taskBJson = objectMapper.writeValueAsString(taskB);
		
		mockMvc.perform(
			MockMvcRequestBuilders.patch("/tasks/" + savedTask.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.content(taskBJson)
		).andExpect(
				MockMvcResultMatchers.status().isOk()
		).andExpect(
				MockMvcResultMatchers.jsonPath("$.id").value(savedTask.getId())
		).andExpect(
				MockMvcResultMatchers.jsonPath("$.description").value(taskB.getDescription())
		).andExpect(
				MockMvcResultMatchers.jsonPath("$.status").value(savedTask.getStatus())
		).andExpect(
        		MockMvcResultMatchers.jsonPath("$.createdAt").exists()
		).andExpect(
        		MockMvcResultMatchers.jsonPath("$.updatedAt").exists()
		);
	}
	
	@Test
	public void canMarkInProgress() throws Exception {
		Task taskA = DataUtil.createTaskA();
		Task savedTask = taskService.addTask(taskA);
		
		mockMvc.perform(
			MockMvcRequestBuilders.patch("/tasks/in-progress/" + savedTask.getId())
				.contentType(MediaType.APPLICATION_JSON)
		).andExpect(
				MockMvcResultMatchers.status().isOk()
		).andExpect(
				MockMvcResultMatchers.jsonPath("$.id").value(savedTask.getId())
		).andExpect(
				MockMvcResultMatchers.jsonPath("$.description").value(savedTask.getDescription())
		).andExpect(
				MockMvcResultMatchers.jsonPath("$.status").value("In-Progress")
		).andExpect(
        		MockMvcResultMatchers.jsonPath("$.createdAt").exists()
		).andExpect(
        		MockMvcResultMatchers.jsonPath("$.updatedAt").exists()
		);
	}
	
	@Test
	public void canMarkDone() throws Exception {
		Task taskA = DataUtil.createTaskA();
		Task savedTask = taskService.addTask(taskA);
		
		mockMvc.perform(
			MockMvcRequestBuilders.patch("/tasks/done/" + savedTask.getId())
				.contentType(MediaType.APPLICATION_JSON)
		).andExpect(
				MockMvcResultMatchers.status().isOk()
		).andExpect(
				MockMvcResultMatchers.jsonPath("$.id").value(savedTask.getId())
		).andExpect(
				MockMvcResultMatchers.jsonPath("$.description").value(savedTask.getDescription())
		).andExpect(
				MockMvcResultMatchers.jsonPath("$.status").value("Done")
		).andExpect(
        		MockMvcResultMatchers.jsonPath("$.createdAt").exists()
		).andExpect(
        		MockMvcResultMatchers.jsonPath("$.updatedAt").exists()
		);
	}
	
	@Test
	public void listDoneTasks() throws Exception {
		Task taskA = DataUtil.createTaskA();
		Task taskB = DataUtil.createTaskB();
		Task taskC = DataUtil.createTaskC();	
		
		taskService.addTask(taskA);
		Task savedTaskB = taskService.addTask(taskB);
		Task savedTaskC = taskService.addTask(taskC);
		
		taskService.markDone(savedTaskB.getId());
		taskService.markDone(savedTaskC.getId());
		
		mockMvc.perform(
				MockMvcRequestBuilders.get("/tasks/done")
					.contentType(MediaType.APPLICATION_JSON)
		).andExpect(
				MockMvcResultMatchers.jsonPath("$[0].id").value(savedTaskB.getId())
		).andExpect(
				MockMvcResultMatchers.jsonPath("$[1].id").value(savedTaskC.getId())
		).andExpect(
				MockMvcResultMatchers.jsonPath("$[0].status").value("Done")
		).andExpect(
				MockMvcResultMatchers.jsonPath("$[1].status").value("Done")
		);
	}
	
	@Test
	public void listTodoTasks() throws Exception {
		Task taskA = DataUtil.createTaskA();
		Task taskB = DataUtil.createTaskB();
		Task taskC = DataUtil.createTaskC();	
		
		Task savedTaskA = taskService.addTask(taskA);
		Task savedTaskB = taskService.addTask(taskB);
		Task savedTaskC = taskService.addTask(taskC);
		
		taskService.markDone(savedTaskA.getId());
		
		mockMvc.perform(
				MockMvcRequestBuilders.get("/tasks/todo")
					.contentType(MediaType.APPLICATION_JSON)
		).andExpect(
				MockMvcResultMatchers.jsonPath("$[0].id").value(savedTaskB.getId())
		).andExpect(
				MockMvcResultMatchers.jsonPath("$[1].id").value(savedTaskC.getId())
		).andExpect(
				MockMvcResultMatchers.jsonPath("$[0].status").value("Todo")
		).andExpect(
				MockMvcResultMatchers.jsonPath("$[1].status").value("Todo")
		);
	}
	
	@Test
	public void listInProgressTasks() throws Exception {
		Task taskA = DataUtil.createTaskA();
		Task taskB = DataUtil.createTaskB();
		Task taskC = DataUtil.createTaskC();	
		
		taskService.addTask(taskA);
		Task savedTaskB = taskService.addTask(taskB);
		Task savedTaskC = taskService.addTask(taskC);
		
		taskService.markInProgress(savedTaskB.getId());
		taskService.markInProgress(savedTaskC.getId());
		
		mockMvc.perform(
				MockMvcRequestBuilders.get("/tasks/in-progress")
					.contentType(MediaType.APPLICATION_JSON)
		).andExpect(
				MockMvcResultMatchers.jsonPath("$[0].id").value(savedTaskB.getId())
		).andExpect(
				MockMvcResultMatchers.jsonPath("$[1].id").value(savedTaskC.getId())
		).andExpect(
				MockMvcResultMatchers.jsonPath("$[0].status").value("In-Progress")
		).andExpect(
				MockMvcResultMatchers.jsonPath("$[1].status").value("In-Progress")
		);
	}
	
	@Test
	public void canDeleteTask() throws Exception {
		Task taskA = DataUtil.createTaskA();
		Task savedTask = taskService.addTask(taskA);
		
		mockMvc.perform(
				MockMvcRequestBuilders.delete("/tasks/" + savedTask.getId())
					.contentType(MediaType.APPLICATION_JSON)
		).andExpect(
				MockMvcResultMatchers.status().isNoContent()
		);
	}
}
