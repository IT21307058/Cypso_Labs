package com.taskmanagementsystem;

import com.taskmanagementsystem.model.Task;
import com.taskmanagementsystem.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskService taskService;

    @Test
    public void testCreateTask() throws Exception {
        String taskJson = "{\"title\": \"Test Task\", \"description\": \"Test Description\", \"status\": \"Pending\"}";

        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(taskJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Task"));
    }

    @Test
    public void testGetTaskById() throws Exception {
        Task task = new Task();
        task.setTitle("Sample Task");
        task.setDescription("Sample Description");
        task.setStatus("Pending");
        Task savedTask = taskService.createTask(task);

        mockMvc.perform(get("/tasks/" + savedTask.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Sample Task"));
    }

    @Test
    public void testUpdateTask() throws Exception {
        Task task = new Task();
        task.setTitle("Sample Task");
        task.setDescription("Sample Description");
        task.setStatus("Pending");
        Task savedTask = taskService.createTask(task);

        String updatedTaskJson = "{\"title\": \"Updated Task\", \"description\": \"Updated Description\", \"status\": \"Completed\"}";

        mockMvc.perform(put("/tasks/" + savedTask.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedTaskJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Task"));
    }


}
