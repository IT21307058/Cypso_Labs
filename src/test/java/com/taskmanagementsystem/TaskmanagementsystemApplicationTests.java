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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
		List<Task> tasks = Arrays.asList(task);

		Page<Task> page = new PageImpl<>(tasks);
		PageRequest pageable = PageRequest.of(0, 10);
		when(taskRepository.findAll(pageable)).thenReturn(page);

		Page<Task> result = taskService.getAllTasks(pageable);

		assertEquals(tasks.size(), result.getContent().size());
		assertEquals(tasks.get(0).getTitle(), result.getContent().get(0).getTitle());
	}

	@Test
	void getTaskById() {
		when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

		Optional<Task> result = taskService.getTaskById(1L);

		assertEquals(task.getId(), result.orElseThrow().getId());
		assertEquals(task.getTitle(), result.orElseThrow().getTitle());
	}

	@Test
	void updateTask() {
		when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

		Task updatedTask = new Task();

		updatedTask.setId(1L);
		updatedTask.setStatus("updated task");
		updatedTask.setDescription("updated description");
		updatedTask.setStatus("InProgress");
		when(taskRepository.save(any(Task.class))).thenReturn(updatedTask);

		Task result = taskService.updateTask(1L, updatedTask);

		assertEquals(updatedTask.getId(), result.getId());
		assertEquals(updatedTask.getTitle(), result.getTitle());
		assertEquals(updatedTask.getDescription(), result.getDescription());
		assertEquals(updatedTask.getStatus(), result.getStatus());


	}

	@Test
	void deleteTask() {
		when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

		taskService.deleteTask(1L);

		verify(taskRepository, times(1)).findById(1L);
	}



}
