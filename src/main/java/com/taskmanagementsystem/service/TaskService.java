package com.taskmanagementsystem.service;


import com.taskmanagementsystem.model.Task;
import com.taskmanagementsystem.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    //create task
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    //get all task
    public Page<Task> getAllTasks(Pageable pageable) {

        return taskRepository.findAll(pageable);
    }

    //get one specific task
    public Optional<Task> getTaskById(Long id) {

        return taskRepository.findById(id);
    }

    //update existing task
    public Task updateTask(Long id, Task taskDetails) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setStatus(taskDetails.getStatus());
        return taskRepository.save(task);
    }

    // delete task
    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        taskRepository.delete(task);
    }
}
