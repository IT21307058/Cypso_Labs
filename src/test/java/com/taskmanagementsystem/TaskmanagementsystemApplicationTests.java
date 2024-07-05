package com.taskmanagementsystem;

import com.taskmanagementsystem.model.Task;
import com.taskmanagementsystem.repository.TaskRepository;
import com.taskmanagementsystem.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class TaskmanagementsystemApplicationTests {

	@Mock
	private TaskRepository taskRepository;

	@InjectMocks
	private TaskService taskService;

	private Task task;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		task = new Task();
		task.setId(1L);
		task.setTitle("Test Task");
		task.setDescription("Test Description");
		task.setStatus("Pending");
	}

	@Test
	void createTask() {
		when(taskRepository.save(any(Task.class))).thenReturn(task);
		Task task1 = taskService.createTask(task);

		assertEquals(task.getTitle(), task1.getTitle());
		assertEquals(task.getDescription(), task1.getDescription());
		assertEquals(task.getStatus(), task1.getStatus());
	}

	@Test
	void getAllTasks(){

	}


}
